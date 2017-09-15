package com.skch.test;

import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.skch.bean.Answer;
import com.skch.bean.UserQuestion;
import com.skch.util.ReadXmlUtil;

public class TestHandVariable {

	
	public static void main(String[] args) {
		UserQuestion userQuestion = new UserQuestion();
		Answer answer = new Answer();
		answer.setAskId("001");
		answer.setScript("daoyi.xml");
		answer.setValue("aaa");
		userQuestion.setAnswer(answer);
		userQuestion.setClientId("010");
		userQuestion.setDeviceId("002");
		userQuestion.setLatitude("003");
		userQuestion.setLongitude("004");
		userQuestion.setQuestion("005");
		userQuestion.setQuestionId("006");
		userQuestion.setUserId("007");
		userQuestion.setUserType("008");
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		Element element = readXmlUtil.getElement("daoyi.xml", "id002");
		List<Attribute> attributes = element.attributes();
//		String userId = userQuestion.getUserId();
		for (Attribute attribute : attributes) {
			String value = attribute.getValue();
			String param = null;
			while (value.contains("#_")){
				int prefix = value.indexOf("#_");
				int suffix = value.indexOf("_#");
				String key = value.substring(prefix, suffix + 2);
//				param = aiVariableServiceImpl.getValue(userId, key);
				value = value.replace(key, param);
			}
			while (value.contains("$_")){
				int prefix = value.indexOf("$_");
				int suffix = value.indexOf("_$");
				String key = value.substring(prefix, suffix + 2);
				String params = value.substring(prefix + 2, suffix);
				String[] split = params.split("\\.");
				Object instance = userQuestion;
				Class<?> type = userQuestion.getClass();
				for (String filedName : split) {
					Field filed = null;
					try {
						filed = type.getDeclaredField(filedName);
						filed.setAccessible(true);
						instance = filed.get(userQuestion);
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
		System.out.println(element.asXML());
	}
}
