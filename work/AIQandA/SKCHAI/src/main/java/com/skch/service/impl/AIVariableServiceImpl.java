package com.skch.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.skch.mapper.VariableMapper;
import com.skch.service.VariableService;

public class AIVariableServiceImpl implements VariableService {

	@Autowired
	private VariableMapper variableMapper;
	
	@Override
	public void setValue(String userId, String paramkey, String paramvalue) {
		Map<String , Object> param = new HashMap<String ,Object>();
		param.put("userId", userId);
		param.put("paramkey", paramkey);
		param.put("paramvalue", paramvalue);
		String result = variableMapper.selectFromt_variable(param);
		if("".equals(result) || result == null){
			variableMapper.insertIntot_variable(param);
		} else {
			variableMapper.updateParam(param);
		}
	}

	@Override
	public String getValue(String userId, String paramkey) {
		Map<String , Object> param = new HashMap<String ,Object>();
		param.put("userId", userId);
		param.put("paramkey", paramkey);
		String paramvalue = variableMapper.selectFromt_variable(param);
		return paramvalue.trim();
	}

	@Override
	public void clear(String userId) {

	}

	@Override
	public void create(String userId) {

	}

}
