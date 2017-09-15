package com.jerry.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class JennyApplication
		extends SpringBootServletInitializer

{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		SpringApplicationBuilder sources = application.sources(JennyApplication.class);
		return sources;
	}

	public static void main(String[] args) {
		SpringApplication.run(JennyApplication.class, args);
	}
}
