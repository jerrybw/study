package com.skch.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by 向博文 on 2017/9/22.
 */

// Imports ...

@Configuration
@ComponentScan
@PropertySource(value = {"file:/application/tomcat/webapps2/conf/datasource.properties","classpath:/application.properties"},
        ignoreResourceNotFound = true)
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }
}