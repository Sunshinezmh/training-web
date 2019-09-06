package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.EvaluationEntity;
import com.dmsdbj.integral.training.provider.service.EvaluationService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;

/**
 * EvaluationController接口
 * 评价他人表：
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Api(tags = {"评价他人表：接口"})
@RequestMapping(value = "/evaluation")
@RestController
public class EvaluationController {

	@Resource
    private EvaluationService evaluationService;

	/**
     * 添加
     *
     * @param entity Evaluation
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public EvaluationEntity create(@RequestBody EvaluationEntity entity) {
        return evaluationService.insert(entity);
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
        evaluationService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity Evaluation
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public EvaluationEntity update(@RequestBody EvaluationEntity entity) {
        return evaluationService.update(entity);
    }

    /**
     * 根据id查找Evaluation
	 *
     * @param id 主键id
	 * @author 严文文
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<EvaluationEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		EvaluationEntity evaluationEntity = evaluationService.findById(id);
		return IntegralResult.build("0000", "查询成功", evaluationEntity);
    }
}    
