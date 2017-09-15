package com.skch.service;

import org.springframework.stereotype.Service;

/***
 * 用于调用脚本中的方法
 * @author Jerry
 *
 */
@Service
public interface MethodService {

	/***
	 * 调用脚本中的方法
	 * @param funName 方法名
	 * @param json 携带参数的json字符串
	 * @return
	 */
	public String getFunctionResult(String funName,String json);
	
	
	/***
	 * 调用http接口方法
	 * @param method 请求类型 'GET' 或 'POST'
	 * @param json 请求参数
	 * @param url 请求地址
	 * @return
	 */
	public String getHttpResult(String url,String request,String json);
}
