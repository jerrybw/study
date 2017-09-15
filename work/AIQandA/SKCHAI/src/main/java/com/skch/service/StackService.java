package com.skch.service;

import org.springframework.stereotype.Service;

import com.skch.bean.AskLabelPosition;

/***
 * 用于将当前所要问的问题的脚本进行栈式存储
 * @author Jerry
 *
 */
@Service
public interface StackService {

	
	/**
	 * 当遇到脚本间的goto语句时，按照userId将goto所在的脚本及对应的标签名形成的AskLabelPosition压入栈中
	 * @param userId
	 * @param askPos
	 */
	public void push(String userId,AskLabelPosition askPos);
	
	/***
	 * 当脚本运行结束时，按照userId出栈,返回下一个问题
	 * @param userId
	 * @return
	 */
	public AskLabelPosition pop(String userId);
	
	
	public void flash(String... keys);
}
