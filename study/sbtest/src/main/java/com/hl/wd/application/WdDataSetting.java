package com.hl.wd.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations = "classpath:application.properties")
public class WdDataSetting {

	private String name;

	private String email;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
