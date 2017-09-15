package com.skch.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.skch.bean.ScriptsFile;
import com.skch.service.ScriptsService;
import com.skch.util.ReadXmlUtil;

public class AIScriptsServiceImpl implements ScriptsService {
	
	private Map<String , ScriptsFile> scriptsFiles = new HashMap<String, ScriptsFile>();//加载完所有脚本后存储入Map中
	
	public AIScriptsServiceImpl() {
		loadScriptsFiles();
		System.out.println("装载脚本");
	}

	@Override
	public ScriptsFile getScriptsFile(String name) {
		return new ScriptsFile(name);
	}

	@Override
	public void loadScriptsFiles() {
		String path = AIScriptsServiceImpl.class.getResource("/script").getPath();
		File file = new File(path.substring(0,path.length() - 1));
		System.out.println(path.substring(0,path.length() - 1));
		File[] listFiles = file.listFiles();
		for (File childFile : listFiles) {
			String fileName = childFile.getName();
			ScriptsFile scriptsFile = new ScriptsFile(fileName);
			scriptsFiles.put(fileName, scriptsFile);
		}
	}

	@Override
	public void loadScriptsFile(String name) {
		ScriptsFile scriptsFile = new ScriptsFile(name);
		scriptsFiles.put(name, scriptsFile);
	}

}
