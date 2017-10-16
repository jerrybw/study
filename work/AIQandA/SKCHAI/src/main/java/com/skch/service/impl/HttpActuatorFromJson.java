package com.skch.service.impl;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import com.skch.service.MethodActuatorService;
import com.skch.util.RequestUtil;

import net.sf.json.JSONObject;

public class HttpActuatorFromJson implements MethodActuatorService{

	private String out;
	private String url;
	private String type;
	

	@Override
	public String getResault(String json) {
		RequestUtil requestUtil = new RequestUtil();
		String returnString = "";
		if("POST".equals(type)){
			try {
				returnString = requestUtil.sendPost(url, "str="+URLEncoder.encode(json,"utf-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			url = url +"?";
			JSONObject jb = JSONObject.fromObject(json);
		    Map<String, Object> map =jb;  
	        for (Entry<String, Object> entry : map.entrySet()) { 
	        	String val = "";
				try {
					val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
					url = url+"&"+entry.getKey()+"="+val;
				} catch (Exception e) {
					e.printStackTrace();
				}
	        } 
			try {
				returnString = requestUtil.getReturnData(url, type);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		returnString = JSONObject.fromObject(returnString).getString(out);
		return returnString;
	}
}
