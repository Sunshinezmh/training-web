package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.api.facade.TrainingProgramApi;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.TrainingUserEntity;
import com.dmsdbj.integral.training.entity.ext.IntegralProgramModel;
import com.dmsdbj.integral.training.provider.service.TrainingUserService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * TrainingUserController接口
 * 人员与培养计划关系表
 * 
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Api(tags = {"人员与培养计划关系表接口"})
@RequestMapping(value = "/trainingUser")
@RestController
public class TrainingUserController{

	@Resource
    private TrainingUserService trainingUserService;

    /**
     * 根据用户id查询该用户进行中的项目
     * @param userId String
     * @return
     * @author 李娜
     * @since 2018年9月27日15:54:38
     */
    @ApiOperation(value = "根据用户id查询该用户进行中的项目")
    @GetMapping(value = {"/queryByUserId/{userId}"})
    public IntegralResult queryByUserId(@PathVariable String userId){

        return trainingUserService.queryByUserId(userId);
    }

    /**
     * 假删除用户id查询该用户所完成的项目
     * @param userId String
     * @author 连迎迎
     * @since 2019年1月6日15:54:38
     */
    @ApiOperation(value = "假删除用户id查询该用户所完成的项目")
    @DeleteMapping(value = {"/deleteAllByUserId/{userId}"})
    public void deleteAllByUserId(@PathVariable String userId){
         trainingUserService.deleteAllByUserId(userId);
    }


    /**
     * 用户完成项目,更新完成并加分
     * @param integralProgramModel
     * @return
     * @author 李娜
     * @since 2018年9月27日15:54:38
     */
    @ApiOperation(value="完成项目,加分")
    @PostMapping(value={"/addIntegral"})
    public IntegralResult addIntegral(@RequestBody IntegralProgramModel integralProgramModel){
            return trainingUserService.addIntegral(integralProgramModel);
    }

    /**
     * 添加
     *
     * @param entity TrainingUser
     * @return 添加的结果
	 * @author 智雪艳 
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public TrainingUserEntity create(@RequestBody TrainingUserEntity entity) {
        return trainingUserService.insert(entity);
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
        trainingUserService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity TrainingUser
     * @return 修改后的结果
	 * @author 智雪艳 
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public TrainingUserEntity update(@RequestBody TrainingUserEntity entity) {
        return trainingUserService.update(entity);
    }

    /**
     * 根据id查找TrainingUser
	 *
     * @param id 主键id
	 * @author 智雪艳 
     * @since 0.0.2 2018-09-18 11:04:05
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<TrainingUserEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		TrainingUserEntity trainingUserEntity = trainingUserService.findById(id);
		return IntegralResult.build("0000", "查询成功", trainingUserEntity);
    }


    /**
     * 更新training_user表中字段is_finished为1
     *
     * @param userId
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-12 10:57:56
     */
    @ApiOperation(value = "更新training_user表中字段is_finished为1")
    @GetMapping(value={"/trainingProgram/updateTrainingUser/{userId}/{programId}/{isFinished}"})
    public IntegralResult updateTrainingUser(@PathVariable("userId") String userId,@PathVariable("programId") String programId,@PathVariable("isFinished") int isFinished){
        boolean result=trainingUserService.updateTrainingUser(userId,programId,isFinished);
        return IntegralResult.build("0000", "查询成功",  result);

    }

    /**
     * 根据userId和programId查询信息
     *
     * @param userId
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-12 10:57:56
     */
    @ApiOperation(value = "根据userId和programId查询信息")
    @GetMapping(value={"/findTrainingUser/{userId}/{programId}"})
    public IntegralResult findTrainingUser(@PathVariable("userId") String userId, @PathVariable("programId") String programId){
        return IntegralResult.build("0000", "查询成功",  trainingUserService.findTrainingUser(userId,programId));
    }

}    
