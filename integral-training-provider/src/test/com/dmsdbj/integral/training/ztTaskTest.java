package com.dmsdbj.integral.training;

import com.dmsdbj.integral.training.provider.service.impl.ZtTaskServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname ztTaskTest
 * @Auther ZMH
 * @Date 2019/8/4 08:32
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ztTaskTest {

    @Autowired
    private ZtTaskServiceImpl ztTaskService;

    @Test
    public void test(){
        ztTaskService.queryAllDelayTask();
    }

    void test1(){
        System.out.println("111");
    }
}
