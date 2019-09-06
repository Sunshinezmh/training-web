package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.AppraiseDetailEntity;
import com.dmsdbj.integral.training.provider.service.AppraiseDetailService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;

/**
 * AppraiseDetailController接口
 * 评价记录详细表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Api(tags = {"评价记录详细表接口"})
@RequestMapping(value = "/appraiseDetail")
@RestController
public class AppraiseDetailController {

	@Resource
    private AppraiseDetailService appraiseDetailService;

	/**
     * 添加
     *
     * @param entity AppraiseDetail
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public AppraiseDetailEntity create(@RequestBody AppraiseDetailEntity entity) {
        return appraiseDetailService.insert(entity);
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
        appraiseDetailService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity AppraiseDetail
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public AppraiseDetailEntity update(@RequestBody AppraiseDetailEntity entity) {
        return appraiseDetailService.update(entity);
    }

    /**
     * 根据id查找AppraiseDetail
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<AppraiseDetailEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		AppraiseDetailEntity appraiseDetailEntity = appraiseDetailService.findById(id);
		return IntegralResult.build("0000", "查询成功", appraiseDetailEntity);
    }
}    
