package com.skch.service;

import org.springframework.stereotype.Service;

import com.skch.bean.ScriptsFile;

/***
 * 用于加载xml文件
 * @author Jerry
 *
 */
@Service
public interface ScriptsService {

	public ScriptsFile getScriptsFile(String name);//从Map中根据脚本名获取脚本
	
	public void loadScriptsFiles() ;//加载所有的脚本
	
	public void loadScriptsFile(String name);//根据脚本名加载某一个脚本
}
