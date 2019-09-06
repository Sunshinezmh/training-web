package com.dmsdbj.integral.training.api.facade;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.api.exception.TrainingProgramException;
import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * 培养计划等级接口
 * @author 严文文
 * @since  2019-5-26 10:50:32
 */
@FeignClient(value = "INTEGRAL-TRAINING-PROVIDER", path = "/training-web",fallback = TrainingProgramException.class)
public interface TrainingLevelApi {
    /**
     * 根据等级ID或者等级名称查找Level
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @author 严文文
     * @return
     * @since 0.0.1 2019-5-25 15:03:52
     */


    @ApiOperation(value = "查询所有等级内容", notes = "请输入每页数量和页码")
    @GetMapping(value = {"/api/trainingLevel/queryAll/{pageNum}/{pageSize}"})
     IntegralResult<PageInfo<TrainingLevelModel>> queryAll(
            @ApiParam(value = "页码", required = true, example = "1") @PathVariable("pageNum") int pageNum,
            @ApiParam(value = "每页数量", required = true, example = "10") @PathVariable("pageSize") int pageSize) ;




    @ApiOperation(value = "查询所有等级")
    @GetMapping(value = {"/api/trainingLevel/queryAllLevel"})
    IntegralResult<List<TrainingLevelModel>>queryAllLevel();
    //IntegralResult<List<TrainingLevelModel>> queryAllLevel();


    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
    @ApiOperation(value = "查询对应等级的TrainingLevelModel")
    @GetMapping(value = "/api/trainingLevel/findByOrder")
     IntegralResult<TrainingLevelModel> findByOrder(int levelOrder);
}
