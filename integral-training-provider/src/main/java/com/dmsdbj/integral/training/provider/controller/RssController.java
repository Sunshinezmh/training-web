package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.RssEntity;
import com.dmsdbj.integral.training.provider.service.RssService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;

/**
 * RssController接口
 * RSS订阅地址表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 15:10:16
 */
@Api(tags = {"RSS订阅地址表接口"})
@RequestMapping(value = "/rss")
@RestController
public class RssController {

	@Resource
    private RssService rssService;

	/**
     * 添加
     *
     * @param entity Rss
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 15:10:16
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public RssEntity create(@RequestBody RssEntity entity) {
        return rssService.insert(entity);
    }

    /**
     * 删除
     *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 15:10:16
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public void delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        rssService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity Rss
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 15:10:16
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public RssEntity update(@RequestBody RssEntity entity) {
        return rssService.update(entity);
    }

    /**
     * 根据id查找Rss
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 15:10:16
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<RssEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		RssEntity rssEntity = rssService.findById(id);
		return IntegralResult.build("0000", "查询成功", rssEntity);
    }
}    
