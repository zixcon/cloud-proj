package com.cloud.frame.demo.auth.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by wd on 2018/1/25.
 */
@Configuration
@MapperScan(basePackages = {"com.cloud.frame.demo.auth.dao.mapper"})
public class DataSourceConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations = "classpath*:/generator/mapper/**/*Mapper.xml";

    @Value("${mybatis.configLocation}")
    private String configLocation = "classpath*:/generator/mybatis-config.xml";

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage = "com.cloud.frame.demo.dao.**.entity";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // mybatis generator逆向生成xml文件，必须通过这种方式加载，否则org.apache.ibatis.binding.BindingException: Invalid bound statement (not found):错误
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
//        sessionFactory.setConfigLocation(new PathResource(configLocation));
        sessionFactory.setConfigLocation(resolver.getResources(configLocation)[0]);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        return sessionFactory.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
