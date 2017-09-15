package com.skch.test;

import com.skch.bean.ScriptsFile;
import com.skch.service.impl.AIScriptsServiceImpl;

public class TestLoadScript {

	public static void main(String[] args) {
		AIScriptsServiceImpl aiScriptsServiceImpl = new AIScriptsServiceImpl();
		
		aiScriptsServiceImpl.loadScriptsFiles();
		
		ScriptsFile scriptsFile = aiScriptsServiceImpl.getScriptsFile("预约脚本样例.xml");
		
		System.out.println(scriptsFile.getFileName());
		System.out.println(scriptsFile.getRoot().asXML());
	}
	
}
