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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname DataSourceMasterConfig
 * @Auther ZMH
 * @Date 2019/8/3 10:21
 */
@Configuration
@MapperScan(basePackages = "com.dmsdbj.integral.training.provider.dao.train", sqlSessionTemplateRef = "trainSqlSessionTemplate")
public class DataSourceTrainConfig {

   /**
     * 是application.yml中的spring.datasource.train配置生效
     * @return
     */
    @Bean(name = "trainDataSource")
    @Qualifier("trainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.traindb")
    @Primary
    public DataSource trainDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 将配置信息注入到SqlSessionFactoryBean中
     * @param dataSource    数据库连接信息
     * @return
     * @throws Exception
     */
    @Bean(name = "trainSqlSessionFactory")
    @Primary
    public SqlSessionFactory trainSqlSessionFactory(@Qualifier("trainDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 使配置信息加载到类中，再注入到SqlSessionFactoryBean
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/train/*.xml"));
        return bean.getObject();
    }

    /**
     * 事务管理器，在实例化时注入主库train
     * @param dataSource
     * @return
     */
    @Bean(name = "trainTransactionManager")
    @Primary
    public DataSourceTransactionManager trainTransactionManager(@Qualifier("trainDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionTemplate具有线程安全性
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "trainSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate trainSqlSessionTemplate(@Qualifier("trainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
