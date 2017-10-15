package com.excel.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExcelApplication
		extends SpringBootServletInitializer

{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		SpringApplicationBuilder sources = application.sources(ExcelApplication.class);
		return sources;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExcelApplication.class, args);
	}
}
