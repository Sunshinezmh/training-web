package com.dmsdbj.integral.training.config;

import com.dmsdbj.cloud.tool.tenancy.DruidMycatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Class_NAME DruidMycatConfig
 * @Auther 刘子腾
 * @Date 2019/2/15 20:38
 */
@Configuration
public class DruidMycatConfig {
    private  static final String FILTER_MYCAT_PREFIX = "spring.datasource.druid.filter.mycat";

    @Bean
    @ConfigurationProperties(FILTER_MYCAT_PREFIX)
    @ConditionalOnProperty(prefix = FILTER_MYCAT_PREFIX, name = "enabled", matchIfMissing = true)
    @ConditionalOnMissingBean
    public DruidMycatFilter mycatFilter(){
        return new DruidMycatFilter();
    }
}
