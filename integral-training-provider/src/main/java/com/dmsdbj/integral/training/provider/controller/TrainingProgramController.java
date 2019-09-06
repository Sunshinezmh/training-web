package com.dmsdbj.integral.training.provider.controller;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.TrainingProgramEntity;
import com.dmsdbj.integral.training.entity.ext.DeployModel;
import com.dmsdbj.integral.training.entity.ext.currentProjectModel;
import com.dmsdbj.integral.training.entity.ext.finishTaskModel;
import com.dmsdbj.integral.training.provider.dao.train.TrainingProgramDao;
import com.dmsdbj.integral.training.provider.service.TrainingProgramService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TrainingProgramController接口
 * 培养计划项目表
 *
 * @author 李娜
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Api(tags = {"培养计划项目表接口"})
@RequestMapping(value = "/trainingProgram")
@RestController
@Slf4j
public class TrainingProgramController {
    @Resource
    private TrainingProgramService trainingProgramService;

    @Resource
    private TrainingProgramDao  trainingProgramDao;

    @ApiOperation(value = "根据等级id查询该项目手机端")
    @GetMapping(value={"/queryProgramByLevelId/{id}"})
    public IntegralResult<List<TrainingModel>> queryProgramByLevelId(@PathVariable String id){
        try{
            List<TrainingModel> programList= trainingProgramService.queryProgramByLevelId(id);
            return IntegralResult.build(IntegralResult.SUCCESS, "调用成功!",programList);

        }catch(Exception e){
            log.error("查询项目失败:",  e);
            return IntegralResult.build(IntegralResult.FAIL, "查询失败!", e);
        }
    }

    @ApiOperation(value = "根据等级id查询该等级项目PC端")
    @GetMapping(value={"/queryProgramByLevel/{id}/{pageNum}/{pageSize}"})
    public IntegralResult<TrainingProgramEntity> queryProgramByLevel(@ApiParam(value = "第几页pageNum", required = true, example = "1") @PathVariable int pageNum,
                                                                     @ApiParam(value = "每页几条记录pageSize", required = true, example = "10") @PathVariable int pageSize,
                                                                     @PathVariable String id){
        PageHelper.startPage(pageNum, pageSize, true);

        try{
            List<TrainingProgramEntity> programList= trainingProgramService.queryProgramByLevel(id);
            PageInfo<TrainingProgramEntity> info = new PageInfo<>(programList);
            return IntegralResult.build(IntegralResult.SUCCESS, "调用成功!",info);

        }catch(Exception e){
            log.error("查询项目失败:",  e);
            return IntegralResult.build(IntegralResult.FAIL, "查询失败!", e);
        }
    }

    @ApiOperation(value = "查询所有的培养计划项目")
    @GetMapping("/queryProgram")
    public IntegralResult<TrainingProgramEntity>queryProgram(){
        List<TrainingProgramEntity> programList= trainingProgramService.queryProgram();
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功！",programList);
    }


    /**
     * 项目名称模糊查询
     * @param pageNum
     * @param pageSize
     * @param programName
     * @param id
     * @return
     * @author:严文文
     * @since :2019-5-8 16:32:34
     */
    @ApiOperation(value="项目名称模糊查询")
    @GetMapping(value={"/queryByLikeName/{programName}/{id}/{pageNum}/{pageSize}"})
    public IntegralResult<TrainingProgramEntity> queryByLikeName(@ApiParam(value = "第几页pageNum", required = true, example = "1") @PathVariable int pageNum,
                                            @ApiParam(value = "每页几条记录pageSize", required = true, example = "10") @PathVariable int pageSize,
                                            @PathVariable String programName, @PathVariable String id){
        List list = new ArrayList();
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(programName,Pattern.CASE_INSENSITIVE);
        List<TrainingProgramEntity> trainingProgramEntities = trainingProgramDao.queryProgramByLevel(id);
        PageHelper.startPage(pageNum, pageSize, true);
        list.addAll(trainingProgramEntities) ;
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher(((TrainingProgramEntity)list.get(i)).getName());
            if(matcher.find()){
                results.add(list.get(i));
            }
        }
        PageInfo<TrainingProgramEntity> info = new PageInfo<>(results);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询成功",info);
    }

    /**
     * 根据等级集合查询出培养计划所有项目.
     * @return
     */
    @ApiOperation(value="查询培养计划所有项目")
    @GetMapping(value={"/queryPrograms/{userId}"})
    public Map<Integer,List<TrainingModel>> queryPrograms(@PathVariable String userId){
        try{
            Map<Integer,List<TrainingModel>> trainingMap=trainingProgramService.queryPrograms(userId);
            String json= JSON.toJSONString(trainingMap,true);
            System.out.println(json);
            return trainingMap;
        }catch(Exception e){
            log.error("查询项目失败:",e);
            return null;
        }
    }

    /**
     * 添加
     *
     * @param entity TrainingProgram
     * @return 添加的结果
     * @author 智雪艳
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public TrainingProgramEntity create(@RequestBody TrainingProgramEntity entity) {
        return trainingProgramService.insert(entity);
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @author 智雪艳
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public void delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        trainingProgramService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity TrainingProgram
     * @return 修改后的结果
     * @author 智雪艳
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public TrainingProgramEntity update(@RequestBody TrainingProgramEntity entity) {
        return trainingProgramService.update(entity);
    }

    /**
     * 批量删除
     *
     * @param  trainingProgramEntities
     * @return 删除后的结果
     * @author 严文文
     * @since 0.0.2 2019-4-27 19:34:07
     */
    @ApiOperation(value = "批量删除")
    @PutMapping(value = {"/deleteAll"})
    @Transactional
    public IntegralResult<TrainingProgramEntity> deleteAll(@RequestBody List<TrainingProgramEntity> trainingProgramEntities) {
        IntegralResult<TrainingProgramEntity> trainingProgramEntity=trainingProgramService.deleteAll(trainingProgramEntities);
        return IntegralResult.build("0000", "批量删除成功", trainingProgramEntity);
    }


    /**
     * 根据id查找TrainingProgram
     *
     * @param id 主键id
     * @author 智雪艳
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<TrainingProgramEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        TrainingProgramEntity trainingProgramEntity = trainingProgramService.findById(id);
        return IntegralResult.build("0000", "查询成功", trainingProgramEntity);
    }

    /**
     * 根据programId查询标准分
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-20 08:21:55
     */

    @ApiOperation(value = "根据programId查询标准分")
    @GetMapping (value={"/queryIntegralByProgramId/{programId}"})
    public IntegralResult queryIntegralByProgramId(@PathVariable("programId") String programId){
        Integer Integral=trainingProgramService.queryIntegralByProgramId(programId);
        return IntegralResult.build("0000", "查询成功", Integral);
    }

    /**
     * 根据remarks查询培养计划加分时间间隔
     * @param remark
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-29 21:27:19
     */
    @ApiOperation(value = "根据remarks查询培养计划加分时间间隔")
    @GetMapping (value={"/queryInfoByRemarks/{remark}"})
    public IntegralResult queryInfoByRemarks(@PathVariable("remark") String remark){
        DeployModel deployModel=trainingProgramService.queryInfoByRemarks(remark);
        return IntegralResult.build("0000", "查询成功", deployModel);
    }

    /**
     * 根据项目名查询培养计划
     * @param programName
     * @return
     * @author:严文文
     * @since；2019-6-1 10:37:07
     */

    @ApiOperation(value = "根据项目名查询培养计划")
    @GetMapping (value={"/queryByName/{programName}"})
    public IntegralResult queryByName(@PathVariable("programName") String programName){
        TrainingProgramEntity trainingProgramEntity=trainingProgramService.queryByName(programName);
        return IntegralResult.build("0000", "查询成功", trainingProgramEntity);

    }

    /**
     * 根据用户id查询用户当前等级的所有项目
     * @param id
     * @return
     */
    @ApiOperation(value = "根据用户id查询用户当前等级的所有项目")
    @GetMapping(value={"/queryByName/{id}"})
    public IntegralResult queryUserProgramByID(@ApiParam(value = "主键id", required = true) @PathVariable String id){
        List<TrainingProgramEntity> trainingProgramEntityList = trainingProgramService.queryUserProgramByID(id);
        return IntegralResult.build("0000","查询成功",trainingProgramEntityList);
    }

    /**
     * 查询用户当前等级，所有创建的项目
     * @param
     * @return
     * @auther:刘兵
     * @since：15:40 2019/6/11
     */
    @ApiOperation(value = "查询用户当前等级，所有创建的项目")
    @GetMapping(value={"/queryUserProject/{userName}"})
    public IntegralResult queryUserProjectByUserName(@PathVariable("userName") String userName){
        List<currentProjectModel> currentProjectModelList = trainingProgramService.queryUserProjectByUserName(userName);
       return IntegralResult.build("0000","查询成功",currentProjectModelList);
    }

    /**
     * 根据项目名称查询项目信息（模糊查询）
     * @param: projectName
     * @return
     * @auther:刘兵
     * @since：15:52 2019/6/12
     */
    @ApiOperation(value = "根据项目名称查询项目信息（模糊查询）")
    @GetMapping(value = {"/queryIntroductionByProjectName/{projectName}"})
    public TrainingProgramEntity queryIntroductionByProjectName(@PathVariable("projectName") String projectName){
        TrainingProgramEntity trainingProgramEntity =
                trainingProgramService.queryIntrouctionByProjectName(projectName);
        return trainingProgramEntity;
    }

    /**
     * 查询用户完成的任务
     * @param: userName
     * @return： List<finishTaskModel>
     * @auther:刘兵
     * @since：20:41 2019/6/18
     */
    @ApiOperation(value = "根据用户名查询用户完成的任务")
    @GetMapping(value = {"/queryFinishTask/{userName}"})
    public List<finishTaskModel> queryFinishTask(@PathVariable("userName") String userName){
        List<finishTaskModel> finishTaskModelList = trainingProgramService.queryFinishTask(userName);
        return finishTaskModelList;
    }
}    
