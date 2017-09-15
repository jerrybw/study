package com.skch.test;

import org.dom4j.Element;

import com.skch.util.ReadXmlUtil;

public class TestReadXml {
	
	public static void main(String[] args) {
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		Element root = readXmlUtil.getRoot("预约脚本样例.xml");
		System.out.println(root.asXML());
//		Element element = readXmlUtil.getElement("预约脚本样例.xml", "id006002");
//		System.out.println(element.asXML());
	}
	
}
