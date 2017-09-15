package com.skch.test;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.skch.service.MethodService;
import com.skch.service.impl.AIMethodServiceImpl;
import com.skch.util.ReadXmlUtil;

public class TestHandMethod {

	
	public static void main(String[] args) {
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		MethodService aiMethodServiceImpl = new AIMethodServiceImpl();
		Element element = readXmlUtil.getElement("daoyi.xml", "id002");
		List<Attribute> attributes = element.attributes();
		for (Attribute attribute : attributes) {
			String value = attribute.getValue();
			if(value.contains("$GET$")){
				String urlAndParam= value.substring(5, value.length() - 5);
				String[] split = urlAndParam.split("\\?");
				String url = split[0];
				String json = null;
				if (split.length > 1) {
					json = split[1];
				}
				String result = aiMethodServiceImpl.getHttpResult(url, "GET", json);
				element.setAttributeValue(attribute.getName(), result);
			} else if (value.contains("$POST$")) {
				String urlAndParam= value.substring(5, value.length() - 5);
				String[] split = urlAndParam.split("\\?");
				String url = split[0];
				String json = null;
				if (split.length > 1) {
					json = split[1];
				}
				String result = aiMethodServiceImpl.getHttpResult(url, "POST", json);
				element.setAttributeValue(attribute.getName(), result);
			} else {
//				aiMethodServiceImpl.getFunctionResult(funName, json);
			}
		}
		String asXML = element.asXML();
		System.out.println(asXML);
	}
}
