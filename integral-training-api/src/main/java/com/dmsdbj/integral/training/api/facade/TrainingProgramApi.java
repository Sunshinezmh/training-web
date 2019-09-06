package com.dmsdbj.integral.training.api.facade;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.api.exception.TrainingProgramException;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import feign.Param;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 培养计划接口
 * @author liuziteng
 * @since  2018年12月12日15:35:46
 */
@FeignClient(value = "INTEGRAL-TRAINING-PROVIDER", path = "/training-web",fallback = TrainingProgramException.class)
public interface TrainingProgramApi {

    /**
     * 根据等级id查询该等级项目-刘子腾-2018-11-30 10:47:19
     * @param id
     * @return
     */
    @ApiOperation(value = "根据等级id查询该等级项目")
    @GetMapping(value={"/api/trainingProgram/queryProgramByLevelId/{id}"})
    IntegralResult<List<TrainingModel>> queryProgramByLevelId(@PathVariable("id") String id);


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
    @GetMapping (value={"/api/trainingProgram/updateTrainingUser/{userId}/{programId}/{isFinished}"})
    IntegralResult updateTrainingUser(@PathVariable("userId") String userId,@PathVariable("programId") String programId,@PathVariable("isFinished") int isFinished);


    /**
     * 根据primaryId
     * @param programId
     * @return
     * @author:严文文
     * @since:2019-1-20 08:14:13
     */
    @ApiOperation(value = "根据programId 查询标准分")
    @GetMapping (value={"/api/trainingProgram/queryIntegralByProgramId/{programId}"})
    IntegralResult queryIntegralByProgramId(@PathVariable("programId") String programId);

    /**
     * 批量增加项目
     * @param trainingModels
     * @return
     * @author:严文文
     * @since:2019年5月9日17:09:43
     */
    @ApiOperation(value="批量增加项目")
    @GetMapping(value={"/api/trainingProgram/insertBatchPrograms"})
     IntegralResult insertBatchPrograms(@RequestBody List<TrainingModel> trainingModels);
    /**
     * 根据用户id查询该用户进行的项目
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-3-26 17:13:33
     */
    @ApiOperation(value = "根据用户id查询该用户进行中的项目")
    @GetMapping(value = {"/api/trainingProgram/queryProgramNameByUserId/{userId}"})
    IntegralResult queryProgramNameByUserId(@PathVariable("userId") String userId);

    /**
     * 根据用户id查询该用户是否通过软考
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-6-1 09:18:56
     */
    @ApiOperation(value = "根据用户id查询该用户是否通过软考")
    @GetMapping(value = {"/api/trainingProgram/querySoftWareByUserId/{userId}"})
    IntegralResult querySoftWareByUserId(@PathVariable("userId") String userId);

    /**
     * 批量更新用户
     * @author 严文文
     * @since 2019-5-7 10:02:22
     */
    @ApiOperation(value="批量更新用户")
    @PostMapping(value="/api/trainingProgram/saveTrainingModels")
    IntegralResult saveTrainingModels(@RequestBody List<TrainingModel> trainingModels);



}
