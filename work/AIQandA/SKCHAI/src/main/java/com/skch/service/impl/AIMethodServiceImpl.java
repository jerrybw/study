package com.skch.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.skch.service.MethodService;
import com.skch.util.RequestUtil;

import net.sf.json.JSONObject;

public class AIMethodServiceImpl implements MethodService {

	private static String questions = AIMethodServiceImpl.class.getClassLoader().getResource("untitled.xml").getPath();

	/**
	 * 解析方法 获取脚本中方法的url+request
	 * 
	 * @param funName
	 * @param json
	 * @return
	 */
	public String getFunctionResult(String funName, String json) {
		String returnJson = "";
		Element ele = null;
		InputStream in = null;
		Element root = null;
		try {
			SAXReader reader = new SAXReader();
			in = new FileInputStream(new File(questions));
			Document doc = reader.read(in);

			root = doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Element> elements = root.elements();
		// 判断子元素集合是否为空集合
		if (!elements.isEmpty()) {
			// 有下级
			String value = root.attributeValue("name");
			// 遍历子节点
			for (Element childrenEle : elements) {
				// 判断传过来方法名称的elements对象
				if (childrenEle.attributeValue("name").equals(funName)) {
					ele = childrenEle;
				}
			}
		}

		// 两种属性值为httpActuator一个，Beans一个
		HashMap<String, String> map = new HashMap();
		List<Element> elements1 = ele.elements();
		if (!elements1.isEmpty()) {
			for (Element childrenEle : elements1) {
				List<Attribute> list = childrenEle.attributes();
				map.put(list.get(0).getValue(), list.get(1).getValue());
			}
		}

		String actuator = ele.attributeValue("actuator");
		String cal = "com.skch.service.impl." + actuator;
		Class a = null;
		try {
			a = Class.forName(cal);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object newInstance = null;
		try {
			newInstance = a.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (Field b : a.getDeclaredFields()) {
			String retStr = (String) map.get(b.getName().toString());
			if (retStr != null) {
				b.setAccessible(true);
				try {
					b.set(newInstance, retStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		Method m = null;
		Object re = "";
		try {
			m = a.getDeclaredMethod("getResault", String.class);
			m.setAccessible(true);
			re = m.invoke(newInstance, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnJson = re.toString();
		return returnJson;
	}

	/**
	 * //请求方法
	 * 
	 * @param url
	 * @param request
	 * @param json
	 * @return
	 */
	public String getHttpResult(String url, String request, String json) {
		RequestUtil requestUtil = new RequestUtil();
		String returnString = "";
		if ("POST".equals(request)) {
			try {
				returnString = requestUtil.sendPost(url, "str=" + URLEncoder.encode(json, "utf-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			url = url + "?";
			JSONObject jb = JSONObject.fromObject(json);
			Map<String, Object> map = jb;
			for (Entry<String, Object> entry : map.entrySet()) {
				String val = "";
				try {
					val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
					url = url + "&" + entry.getKey() + "=" + val;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				returnString = requestUtil.getReturnData(url, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnString;
	}
}
