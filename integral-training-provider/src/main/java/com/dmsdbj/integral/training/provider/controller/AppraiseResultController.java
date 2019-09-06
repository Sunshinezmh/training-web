package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.AppraiseResultEntity;
import com.dmsdbj.integral.training.entity.ext.ScoreModel;
import com.dmsdbj.integral.training.provider.service.AppraiseResultService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * AppraiseResultController接口
 * 评价记录汇总表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Api(tags = {"评价记录汇总表接口"})
@RequestMapping(value = "/appraiseResult")
@RestController
public class AppraiseResultController {

	@Resource
    private AppraiseResultService appraiseResultService;

	/**
     * 添加
     *
     * @param entity AppraiseResult
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public AppraiseResultEntity create(@RequestBody AppraiseResultEntity entity) {
        return appraiseResultService.insert(entity);
    }

    /**
     * 删除
     *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public void delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        appraiseResultService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity AppraiseResult
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public AppraiseResultEntity update(@RequestBody AppraiseResultEntity entity) {
        return appraiseResultService.update(entity);
    }

    /**
     * 根据id查找AppraiseResult
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<AppraiseResultEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		AppraiseResultEntity appraiseResultEntity = appraiseResultService.findById(id);
		return IntegralResult.build("0000", "查询成功", appraiseResultEntity);
    }

    /**
     * 插入评分详情，包括详情表和总分记录表
     * @author 王婷婷
     * @since 2019年6月9日10:52:59
     */
    @ApiOperation(value = "插入评分详情，包括详情表和总分记录表",notes = "插入评分详情，包括详情表和总分记录表")
    @PostMapping(value = {"/updateScore"})
    public IntegralResult<ScoreModel> updateScore(@RequestBody List<ScoreModel> scoreModel){
        List<ScoreModel> scoreModels=appraiseResultService.updateScore(scoreModel);
        return  IntegralResult.build(IntegralResult.SUCCESS,"更新成功",scoreModels);




    }


}    
