package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.SnapshotEntity;
import com.dmsdbj.integral.training.provider.service.SnapshotService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;

/**
 * SnapshotController接口
 * 培养计划英语快照表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Api(tags = {"培养计划英语快照表接口"})
@RequestMapping(value = "/snapshot")
@RestController
public class SnapshotController {

	@Resource
    private SnapshotService snapshotService;

	/**
     * 添加
     *
     * @param entity Snapshot
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public SnapshotEntity create(@RequestBody SnapshotEntity entity) {
        return snapshotService.insert(entity);
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
        snapshotService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity Snapshot
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public SnapshotEntity update(@RequestBody SnapshotEntity entity) {
        return snapshotService.update(entity);
    }

    /**
     * 根据id查找Snapshot
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<SnapshotEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		SnapshotEntity snapshotEntity = snapshotService.findById(id);
		return IntegralResult.build("0000", "查询成功", snapshotEntity);
    }
}    
