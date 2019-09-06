package com.dmsdbj.integral.training;

import com.dmsdbj.cloud.tool.tenancy.EnableUserTransmitter;
import com.dmsdbj.cloud.tool.tenancy.LoginUtils;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//本服务启动后会自动注册进eureka服务中
//@EnableEurekaClient
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
//服务发现
@EnableDiscoveryClient
@Import(FdfsClientConfig.class)
//调用其他服务包扫描
@EnableFeignClients(basePackages = {"com.dmsdbj.integral.kernel.api"})
@ImportResource("classpath*:spring-shiro.xml")
@EnableUserTransmitter
public class IntegralTrainingProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegralTrainingProviderApplication.class, args);
    }

    @Bean
    public LoginUtils methodLogin(){
        return new LoginUtils();
    }
}
