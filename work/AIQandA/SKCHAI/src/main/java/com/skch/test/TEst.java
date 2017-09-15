package com.skch.test;

import com.skch.service.impl.AIMethodServiceImpl;

public class TEst {
	private static String json = "{\"userId\":\"139\"}";
	private static String Fun = "mainGetUserAddress";
	

	public static void main(String[] args) {
		
		AIMethodServiceImpl t = new AIMethodServiceImpl();
		System.out.println("结果串:"+t.getFunctionResult(Fun, json));
		
	}
}
