package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.entity.ext.DelayTaskModel;
import com.dmsdbj.integral.training.provider.service.ZtTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname ZentaoTaskController
 * @Auther ZMH
 * @Date 2019/7/30 00:19
 */
@RestController
@Api(tags = {"禅道任务Bug表接口"})
@RequestMapping(value = "/ZentaoTask")
public class ZentaoTaskController {

    @Resource
    private ZtTaskService ztTaskService;

    /**
     * 功能描述 查询所有延期的人员
     * @return
     * @auther ZMH
     * @date 2019/7/28 11:27
     */
    @ApiOperation(value = "查询所有延期的人员并发送通知成功")
    @GetMapping(value = {"/queryAllDelayTask"})
    public IntegralResult<DelayTaskModel> queryAllDelayTask(){
        ztTaskService.queryAllDelayTask();
        return IntegralResult.build("0000","查询所有延期的人员并发送通知成功");
    }
}
