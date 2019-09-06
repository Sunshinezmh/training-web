package com.dmsdbj.integral.training.provider.controller.api;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.cloud.tool.enums.IntegralResultEnum;
import com.dmsdbj.integral.training.api.facade.TrainingLevelApi;

import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.dmsdbj.integral.training.provider.service.TrainingLevelService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 外部培养计划等级接口
 *
 * @author 严文文
 * @version 0.0.3
 * @since 0.0.3 2019-5-26 10:40:22
 */



@Controller
@Slf4j
@io.swagger.annotations.Api(tags = {"外部培养计划等级接口"})
@RequestMapping(value = "/api/trainingLevel")
@RestController

public class ApiTrainingLevelController implements TrainingLevelApi {

    @Resource
    private TrainingLevelService trainingLevelService;

    /**
     * 根据等级ID或者等级名称查找Level
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @author 严文文
     * @since 0.0.1 2019-5-25 15:03:52
     */

    @ApiOperation(value = "查询所有等级内容", notes = "请输入每页数量和页码")
    @GetMapping(value = {"/queryAll/{pageNum}/{pageSize}"})
    @Override
    public IntegralResult<PageInfo<TrainingLevelModel>> queryAll(
            @ApiParam(value = "页码", required = true, example = "1") @PathVariable int pageNum,
            @ApiParam(value = "每页数量", required = true, example = "10") @PathVariable int pageSize) {
        return trainingLevelService.queryAll(pageNum, pageSize);
    }

    @ApiOperation(value = "查询所有等级",notes = "查询所有等级")
    @GetMapping(value = {"/queryAllLevel"})
    @Override
    public IntegralResult<List<TrainingLevelModel>> queryAllLevel(){
        List<TrainingLevelModel> TrainingLevelEntity = trainingLevelService.queryAllLevel();
        return IntegralResult.build(IntegralResultEnum.EXECUTE_SUCCESS, TrainingLevelEntity);
    }


    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
    @ApiOperation(value = "查询对应等级的TrainingLevelModel")
    @GetMapping(value = "/findByOrder")
    @Override
    public IntegralResult<TrainingLevelModel> findByOrder(int levelOrder){
        TrainingLevelModel trainingLevelModels=trainingLevelService.findByOrder( levelOrder);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询对应等级的TrainingLevelModel" , trainingLevelModels);
    }

}
