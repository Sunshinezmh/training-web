package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.UserEntity;
import com.dmsdbj.integral.training.provider.service.UserService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;

/**
 * UserController接口
 * 积分用户表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 14:31:35
 */
@Api(tags = {"积分用户表接口"})
@RequestMapping(value = "/user")
@RestController
public class UserController {

	@Resource
    private UserService userService;

	/**
     * 添加
     *
     * @param entity User
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 14:31:35
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public UserEntity create(@RequestBody UserEntity entity) {
        return userService.insert(entity);
    }

    /**
     * 删除
     *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 14:31:35
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public void delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        userService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity User
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 14:31:35
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public UserEntity update(@RequestBody UserEntity entity) {
        return userService.update(entity);
    }

    /**
     * 根据id查找User
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-06 14:31:35
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<UserEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		UserEntity userEntity = userService.findById(id);
		return IntegralResult.build("0000", "查询成功", userEntity);
    }
}    
