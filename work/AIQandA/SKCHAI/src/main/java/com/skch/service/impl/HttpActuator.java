package com.skch.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import com.skch.service.MethodActuatorService;
import com.skch.util.RequestUtil;

import net.sf.json.JSONObject;

public class HttpActuator implements MethodActuatorService{
	
	private String url;
	private String type;
	
//	private static String questions = AIMethodServiceImpl.class.getClassLoader().getResource("untitled.xml").getPath();
	/**
	 * //请求方法
	 * @param url
	 * @param request
	 * @param json
	 * @return
	 */
	@Override
	public String getResault(String json) {
		url = url+"?";
		JSONObject jb = JSONObject.fromObject(json);
	     Map<String, Object> map =jb;  
	     int i = 1;
	       for (Entry<String, Object> entry : map.entrySet()) {  
	            if(i!=1){
	            	if(entry.getKey().equals("address") || entry.getKey().equals("Question")){
	            		try {
	            			String val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
							url = url+"&"+entry.getKey()+"="+val;
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
	            	}else{
	            		url = url+"&"+entry.getKey()+"="+entry.getValue();
	            	}
	            }else{
	            	if(entry.getKey().equals("Question")){
	            		try {
	            			String val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
	            			url = url+entry.getKey()+"="+val;
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
	            	}else{
	            		url = url+entry.getKey()+"="+entry.getValue();
	            	}
	            	
	            }
	            i++;
	       }     
		//请求
		RequestUtil requestUtil = new RequestUtil();
		String returnString = "";
		try {
			returnString = requestUtil.getReturnData(url, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnString;
	}


}
