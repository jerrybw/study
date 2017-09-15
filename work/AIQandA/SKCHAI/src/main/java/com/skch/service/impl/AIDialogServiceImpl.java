package com.skch.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.skch.mapper.DialogMapper;
import com.skch.service.DialogsService;

public class AIDialogServiceImpl implements DialogsService{

	@Autowired
	private DialogMapper dialogMapper;

	@Override
	public int addSentence(String sender, String accepter, String scripts, String askId ,String dialog) {
		Map<String , Object> param = new HashMap<String , Object>();
		param.put("sender", sender);
		param.put("accepter", accepter);
		param.put("scripts", scripts);
		param.put("askId", askId);
		param.put("dialog", dialog);
		int result = dialogMapper.insertIntot_dialog(param);
		return result;
	}
	
}
