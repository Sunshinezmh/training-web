package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.BlogEntity;
import com.dmsdbj.integral.training.provider.service.BlogService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;

/**
 * BlogController接口
 * 博客链接表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Api(tags = {"博客链接表接口"})
@RequestMapping(value = "/blog")
@RestController
public class BlogController {

	@Resource
    private BlogService blogService;

	/**
     * 添加
     *
     * @param entity Blog
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public BlogEntity create(@RequestBody BlogEntity entity) {
        return blogService.insert(entity);
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
        blogService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity Blog
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public BlogEntity update(@RequestBody BlogEntity entity) {
        return blogService.update(entity);
    }

    /**
     * 根据id查找Blog
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<BlogEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		BlogEntity blogEntity = blogService.findById(id);
		return IntegralResult.build("0000", "查询成功", blogEntity);
    }

    /**
     * 定时获取最新的所有博客
     * @author:严文文
     * @since:2019-6-6 10:25:07
     * @return
     */
    @ApiOperation(value="定时获取最新的所有博客",notes="定时获取最新的所有博客")
    @GetMapping(value={"/updateAllBlog"})
    public IntegralResult<BlogEntity> updateAllBlog(){
        boolean result=false;
        result=blogService.updateAllBlog();
        if(result==true){
            return IntegralResult.build("0000","更新成功",result);
        }else{
            return IntegralResult.build("1111","更新失败",result);
        }
    }

}    
