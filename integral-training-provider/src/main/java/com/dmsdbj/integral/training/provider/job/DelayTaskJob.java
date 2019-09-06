package com.dmsdbj.integral.training.provider.job;

import com.dmsdbj.integral.training.provider.service.ZtTaskService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname DelayTaskJob
 * @Auther ZMH
 * @Date 2019/8/4 08:35
 */
@Slf4j
@Component
@JobHandler(value = "DelayTaskJob")
public class DelayTaskJob extends IJobHandler {


    @Resource
    private ZtTaskService ztTaskService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log(this.getClass().getSimpleName()+"---start");
        try {
            //执行警告的考勤信息
            ztTaskService.queryAllDelayTask();
            XxlJobLogger.log(this.getClass().getSimpleName()+"---end");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }
}
