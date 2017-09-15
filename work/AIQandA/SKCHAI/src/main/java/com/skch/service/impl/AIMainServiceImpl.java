package com.skch.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.skch.bean.UserQuestion;
import com.skch.service.MainService;
import com.skch.service.MethodService;
import com.skch.service.VariableService;
import com.skch.util.ReadXmlUtil;

public class AIMainServiceImpl implements MainService {

	
	@Autowired
	private MethodService aiMethodService;
	
	@Autowired
	private VariableService aiVariableService;

	@Override
	public boolean check(String jsonStr) {
		UserQuestion userQuestion = getUserQuestion(jsonStr);
		String script = userQuestion.getAnswer().getScript();
		if("".equals(script) || script == null ){
			return true;
		}
		return false;
	}


	//用于将json字符串转换成UserQuestion对象
	public UserQuestion getUserQuestion(String jsonStr){
		Gson gson = new Gson();
		return gson.fromJson(jsonStr	,UserQuestion.class);
	}
	
	//用于处理goto标签
	public Element handGoto(String QueGoto){
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		String[] split = QueGoto.split(",");
		String script = split[0].substring(2, split[0].length() - 1);
		String askId = split[1].substring(1, split[1].length() - 2);
		return readXmlUtil.getElement(script,askId);
	}

	@Override
	public Element handMethod(Element element) {
		List<Attribute> attributes = element.attributes();
		for (Attribute attribute : attributes) {
			String value = attribute.getValue();
			if(value.contains("$GET$")){
				int findex = value.indexOf("$GET$");
				int lindex = value.lastIndexOf("$GET$");
				String urlAndParam= value.substring(findex + 5, lindex);
				String[] split = urlAndParam.split("\\?");
				String url = split[0];
				String json = null;
				if (split.length > 1) {
					json = split[1];
				}
				value = aiMethodService.getHttpResult(url, "GET", json);
			} else if (value.contains("$POST$")) {
				String urlAndParam= value.substring(5, value.length() - 5);
				String[] split = urlAndParam.split("\\?");
				String url = split[0];
				String json = null;
				if (split.length > 1) {
					json = split[1];
				}
				value = aiMethodService.getHttpResult(url, "POST", json);
			} else if(value.contains("$FUN$")) {
				String urlAndParam= value.substring(5, value.length() - 5);
				String[] split = urlAndParam.split("\\?");
				String funName = split[0];
				String json = null;
				if (split.length > 1) {
					json = split[1];
				}
				value = aiMethodService.getFunctionResult(funName, json);
			}
			element.setAttributeValue(attribute.getName(), value);
		}
		return element;
	}

	@Override
	public Element handVariable(Element element,UserQuestion userQuestion) {
		List<Attribute> attributes = element.attributes();
		String userId = userQuestion.getUserId();
		for (Attribute attribute : attributes) {
			String value = attribute.getValue();
			String param = null;
			while (value.contains("#_")){
				int prefix = value.indexOf("#_");
				int suffix = value.indexOf("_#");
				String key = value.substring(prefix, suffix + 2);
				String paramkey = value.substring(prefix + 2, suffix);
				param = aiVariableService.getValue(userId, paramkey);
				value = value.replace(key, param);
			}
			while (value.contains("$_")){
				int prefix = value.indexOf("$_");
				int suffix = value.indexOf("_$");
				String key = value.substring(prefix, suffix + 2);
				String paramkey = value.substring(prefix + 2, suffix);
				String[] split = paramkey.split("\\.");
				Object instance = userQuestion;
				Class<?> type = userQuestion.getClass();
				for (String filedName : split) {
					Field filed = null;
					try {
						filed = type.getDeclaredField(filedName);
						filed.setAccessible(true);
						instance = filed.get(instance);
						type = filed.getType();
					} catch (Exception e) {
						throw new RuntimeException();
					}
				}
				if(type.equals(String.class)){
					param = (String)instance;
				} else {
					throw new RuntimeException();
				}
				value = value.replace(key, param);
			}
			element.setAttributeValue(attribute.getName(), value);
		}
		return element;
	}
	
}
