package com.dmsdbj.integral.training.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname DataSourceSlaveConfig
 * @Auther ZMH
 * @Date 2019/8/3 10:25
 */
@Configuration
@MapperScan(basePackages = "com.dmsdbj.integral.training.provider.dao.zentao", sqlSessionTemplateRef = "zentaoSqlSessionTemplate")
public class DataSourceZentaoConfig {
    /**
     * 是application-test.yml中的spring.datasource.master配置生效
     * @return
     */
    @Bean(name = "zentaoDataSource")
    @Qualifier("zentaoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.zentaodb")
    public DataSource zentaoDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 将配置信息注入到SqlSessionFactoryBean中
     * @param dataSource    数据库连接信息
     * @return
     * @throws Exception
     */
    @Bean(name = "zentaoSqlSessionFactory")
    public SqlSessionFactory zentaoSqlSessionFactory(@Qualifier("zentaoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 使配置信息加载到类中，再注入到SqlSessionFactoryBean
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/zentao/*.xml"));
        return bean.getObject();
    }

    /**
     * 事务管理器，在实例化时注入主库master
     * @param dataSource
     * @return
     */
    @Bean(name = "zentaoTransactionManager")
    public DataSourceTransactionManager zentaoTransactionManager(@Qualifier("zentaoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionTemplate具有线程安全性
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "zentaoSqlSessionTemplate")
    public SqlSessionTemplate zentaoSqlSessionTemplate(@Qualifier("zentaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
