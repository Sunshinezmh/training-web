package com.dmsdbj.integral.training.provider.controller.api;


import com.dmsdbj.cloud.tool.uuid.IdWorker;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.cloud.tool.uuid.IdWorker;
import com.dmsdbj.integral.training.api.facade.TrainingProgramApi;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.TrainingProgramEntity;
import com.dmsdbj.integral.training.provider.service.TrainingProgramService;
import com.dmsdbj.integral.training.provider.service.TrainingUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 外部培养计划项目接口
 *
 * @author 冯浩月
 * @version 0.0.3
 * @since 0.0.3 2019年3月20日15:34:39
 */
@Controller
@Slf4j
@Api(tags = {"外部培养计划项目接口"})
@RequestMapping(value = "/api/trainingProgram")
@RestController
public class ApiTrainingProgramController  implements TrainingProgramApi {
    @Resource
    private TrainingUserService trainingUserService;
    @Resource
    private TrainingProgramService trainingProgramService;


    /**
     * 根据等级id查询该等级项目-刘子腾-2018-11-30 10:47:19
     * @param id
     * @return
     */
    @ApiOperation(value = "根据等级id查询该等级项目")
    @GetMapping(value={"/queryProgramByLevelId/{id}"})
    @Override
    public IntegralResult<List<TrainingModel>> queryProgramByLevelId(@PathVariable("id") String id){
            List<TrainingModel> programList= trainingProgramService.queryProgramByLevelId(id);
            return IntegralResult.build(IntegralResult.SUCCESS, "调用成功!",programList);
 }

    /**
     * 根据用户id查询该用户进行中的项目
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-3-26 17:13:33
     */
    @ApiOperation(value = "根据用户id查询该用户进行中的项目")
    @GetMapping(value = {"/queryProgramNameByUserId/{userId}"})
    @Override
    public IntegralResult queryProgramNameByUserId(@PathVariable("userId") String userId ){
        return trainingUserService.queryProgramNameByUserId(userId);
    }

    /**
     * 根据用户id查询该用户是否通过软考
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-6-1 09:18:56
     */
    @ApiOperation(value = "根据用户id查询该用户是否通过软考")
    @GetMapping(value = {"querySoftWareByUserId/{userId}"})
    @Override
    public IntegralResult querySoftWareByUserId(@PathVariable("userId") String userId){
        return trainingProgramService.querySoftWareByUserId(userId);
    }
    /**
     *
     * 批量更新用户
     * @author 严文文
     * @since 2019-5-7 10:02:22
     */
    @ApiOperation(value="批量更新用户")
    @PostMapping(value="/saveTrainingModels")
    @Override
    public IntegralResult saveTrainingModels(@RequestBody List<TrainingModel> trainingModels){
        return trainingProgramService.saveTrainingModels(trainingModels);
    }
    /**
     * 更新training_user表中字段is_finished为1
     *
     * @param userId
     * @param programId
     * @return
     * @author:严文文
     * @since:2019-1-12 10:48:25
     */
    @ApiOperation(value = "更新training_user表中字段is_finished")
    @GetMapping (value={"/updateTrainingUser/{userId}/{programId}/{isFinished}"})
    @Override
    public IntegralResult updateTrainingUser(@PathVariable("userId") String userId,@PathVariable("programId") String programId,@PathVariable("isFinished") int isFinished){
        boolean result=trainingUserService.updateTrainingUser(userId,programId,isFinished);
        return IntegralResult.build("0000", "查询成功",  result);
    }


    /**
     * 根据primaryId
     * @param programId
     * @return
     * @author:严文文
     * @since:2019-1-20 08:14:13
     */
    @ApiOperation(value = "根据programId 查询标准分")
    @GetMapping (value={"/queryIntegralByProgramId/{programId}"})
    @Override
    public IntegralResult queryIntegralByProgramId(@PathVariable("programId") String programId){
        Integer Integral=trainingProgramService.queryIntegralByProgramId(programId);
        return IntegralResult.build("0000", "查询成功", Integral);
    }

    /**
     * 批量增加项目
     * @param trainingModels
     * @return
     * @author:严文文
     * @since:2019年5月9日17:09:43
     */
    @ApiOperation(value="批量增加项目")
    @PostMapping(value={"/insertBatchPrograms"})
    @Override
    @Transactional(rollbackFor=Exception.class)
    public IntegralResult insertBatchPrograms(@RequestBody List<TrainingModel> trainingModels){
        TrainingProgramEntity trainingProgramEntity=new TrainingProgramEntity();

        for (TrainingModel trainingModel:trainingModels) {
            trainingProgramEntity.setType(trainingModel.getType());
            trainingProgramEntity.setRate(Double.parseDouble(trainingModel.getRate()));
            trainingProgramEntity.setName(trainingModel.getName());
            trainingProgramEntity.setLevelOrder(trainingModel.getLevelOrder());
            trainingProgramEntity.setLevelId(trainingModel.getLevelId());
            trainingProgramEntity.setIntegral(trainingModel.getIntegral());
            trainingProgramEntity.setDescription(trainingModel.getDescription());
            trainingProgramEntity.setOperator(trainingModel.getOperator());
            trainingProgramEntity.setCreator(trainingModel.getCreator());
            trainingProgramEntity.setId(IdWorker.nextStringId());
            TrainingProgramEntity trainingProgramEntity1=trainingProgramService.insert(trainingProgramEntity);
            if(!trainingProgramEntity1.getId().isEmpty()){
                continue;
            }else {
                return IntegralResult.build("1111","添加失败",trainingProgramEntity1.getName());
            }

        }
        return IntegralResult.build("0000","增加成功");
    }


}
