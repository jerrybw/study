package com.jerry.work;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
//@MapperScan("com.jerry.work.dao")
public class RobotApplication

	extends SpringBootServletInitializer

	{
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			SpringApplicationBuilder sources = application.sources(RobotApplication.class);
		return sources;
	}

	public static void main(String[] args) {

		SpringApplication.run(RobotApplication.class, args);
	}
}
