package com.dmsdbj.integral.training.api.exception;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.cloud.tool.json.JsonUtil;
import com.dmsdbj.integral.training.api.facade.TrainingLevelApi;
import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 培养计划服务等级异常处理
 * @author 严文文
 * @since 2019-5-26 11:24:50
 */


@Slf4j
@Component
public class TrainingLevelException implements TrainingLevelApi {

    @Override
    @GetMapping(value = {"/trainingLevel/queryAll/{pageNum}/{pageSize}"})
    public IntegralResult<PageInfo<TrainingLevelModel>> queryAll(
            @ApiParam(value = "页码", required = true, example = "1") @PathVariable int pageNum,
            @ApiParam(value = "每页数量", required = true, example = "10") @PathVariable int pageSize) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,pageNum);
    }

    /**
     * 培养计划服务项目调用出错回调处理
     */
    public IntegralResult exception (String thisMethodName, Object obj) {
        log.error(thisMethodName + ":***请求接口系统异常停止服务：" + JsonUtil.beanToJson(obj, true));
        IntegralResult result = new IntegralResult();
        result.setCode(IntegralResult.SUCCESS);
        result.setMessage("服务端异常, 请稍后再试");
        return result;
    }

    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
    @Override
    @GetMapping(value = "/trainingLevel/findByOrder")
    public IntegralResult<TrainingLevelModel> findByOrder(int levelOrder){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,levelOrder);
    }

    @Override
    @GetMapping(value = {"/trainingLevel/queryAllLevel"})
  public  IntegralResult<List<TrainingLevelModel>>  queryAllLevel(){
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return exception(thisMethodName,null);

        //IntegralResult<List<TrainingLevelModel>>
    }
}
