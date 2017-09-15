package com.skch.service;

import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.skch.bean.UserQuestion;

/***
 * 业务逻辑层 主要接收控制层传入的数据并进行处理（存储，运算等）
 * @author jerry
 *
 */
@Service
public interface MainService {
	
	//用于判断是否是报道
	public boolean check(String jsonStr);
	
	
	/***
	 * 处理标签中的方法
	 * @param eleStr 标签
	 * @return 不带方法的完整标签
	 */
	public Element handMethod(Element element);
	
	/***
	 * 处理标签中的属性
	 * @param eleStr 标签
	 * @return 不带属性的完整标签
	 */
	public Element handVariable(Element element,UserQuestion userQuestion);
	
	/**
	 * 用于将接收到的json转化为数据对象
	 * @param jsonStr
	 * @return
	 */
	public UserQuestion getUserQuestion(String jsonStr);
}
