package com.skch.mapper;

import java.util.Map;

public interface VariableMapper {
	int insertIntot_variable(Map<String , Object> param);
	
	String selectFromt_variable(Map<String , Object> param);

	void updateParam(Map<String, Object> param);
}
