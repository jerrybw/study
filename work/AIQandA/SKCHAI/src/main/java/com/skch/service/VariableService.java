package com.skch.service;

import org.springframework.stereotype.Service;

/***
 * 
 * 处理标签中variable的属性名和属性值
 * @author JERRY
 */
@Service
public interface VariableService {

	/***
	 * 将标签中variable的属性名属性值按userId存储
	 * @param userId userId
	 * @param key 属性名
	 * @param value 属性值
	 */
	public void setValue(String userId,String key,String value);
	
	/***
	 * 将标签中variable的属性值按userId，属性名提取出来
	 * @param userId userId
	 * @param key 属性名
	 * @return
	 */
	public String getValue(String userId,String key);
	
	/***
	 * 预留，用于清除传入的userId所对应的属性
	 * @param userId
	 */
	public void clear(String userId);
	
	/***
	 * 预留，用于创建userId下的属性
	 * @param userId
	 */
	public void create(String userId);
	
}
