package com.dmsdbj.integral.training.api.exception;

/**
 * Created by Eric Liu on 2018/12/12.
 */

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.cloud.tool.json.JsonUtil;
import com.dmsdbj.integral.training.api.facade.TrainingProgramApi;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import feign.Param;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 培养计划服务降级异常处理
 * @author 刘子腾
 * @since 2018/12/12
*/


@Slf4j
@Component
public class TrainingProgramException implements TrainingProgramApi {

    /**
     * 根据等级id查询该等级项目-刘子腾-2018-11-30 10:47:19
     * @param id
     * @return
     */
    @Override
    public IntegralResult<List<TrainingModel>> queryProgramByLevelId(@Param("id") String id){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,id);
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
    @Override
    @GetMapping(value={"/trainingProgram/updateTrainingUser/{userId}/{programId}/{isFinished}"})
    public IntegralResult updateTrainingUser(@PathVariable("userId") String userId,@PathVariable("programId") String programId,@PathVariable("isFinished") int isFinished){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,userId);
    }

    /**
     * 根据primaryId
     * @param programId
     * @return
     * @author:严文文
     * @since:2019-1-20 08:14:13
     */
    @Override
    @GetMapping (value={"/trainingProgram/queryIntegralByProgramId/{programId}"})
    public IntegralResult queryIntegralByProgramId(@PathVariable("programId") String programId){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,programId);
    }



    /**
     * 根据用户id查询该用户进行中的项目
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-3-26 17:13:33
     */
    @Override
    @GetMapping(value = {"/trainingProgram/queryProgramNameByUserId/{userId}"})
    public IntegralResult queryProgramNameByUserId(@PathVariable("userId") String userId ){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,userId);
    }

    /**
     * 批量增加项目
     * @param trainingModels
     * @return
     * @author:严文文
     * @since:2019年5月9日17:09:43
     */
    @Override
    @GetMapping(value={"/trainingProgram/insertBatchPrograms"})
    public IntegralResult insertBatchPrograms(@RequestBody List<TrainingModel> trainingModels){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,trainingModels);
    }

    /**
     * 批量更新用户
     * @author 严文文
     * @param trainingModels
     * @since 2019-5-7 10:02:22
     */

    @Override
    @PostMapping(value={"/trainingProgram/saveTrainingModels"})
    public IntegralResult saveTrainingModels(@RequestBody List<TrainingModel> trainingModels){
        String thisMethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,trainingModels);
    }


    /**
     * 根据用户id查询该用户是否通过软考
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-6-1 09:18:56
     */
    @Override
    @GetMapping(value = {"trainingProgram/querySoftWareByUserId/{userId}"})
    public IntegralResult querySoftWareByUserId(@PathVariable("userId") String userId){
        String thisMethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,userId);
    }

    /**
     * 培养计划服务调用出错回调处理
     */
    public IntegralResult exception (String thisMethodName, Object obj) {
        log.error(thisMethodName + ":***请求接口系统异常停止服务：" + JsonUtil.beanToJson(obj, true));
        IntegralResult result = new IntegralResult();
        result.setCode(IntegralResult.SUCCESS);
        result.setMessage("服务端异常, 请稍后再试");
        return result;
    }


}
