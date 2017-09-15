package com.skch.service.impl;

import com.skch.service.MethodActuatorService;

public class Beans implements MethodActuatorService{

	private String url;
	private String request;
	@Override
	public String getResault(String json) {
		System.out.println(url);
		System.out.println(request);
		return null;
	}

}
