package com.jerry.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestApplication
		extends SpringBootServletInitializer

{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		SpringApplicationBuilder sources = application.sources(TestApplication.class);
		return sources;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
