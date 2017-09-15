package com.skch.service;

import org.springframework.stereotype.Service;

/***
 * 用于存储对话
 * @author Jerry
 *
 */
@Service
public interface DialogsService {

	/***
	 * 将对话的数据存储
	 * @param sender 发送者
	 * @param accepter 接受者
	 * @param scripts 对话所在的脚本
	 * @param askId 对话所对应的标签名
	 */
	public int addSentence(String sender, String accepter,  String scripts , String askId ,String dialog);
	
}
