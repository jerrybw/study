package com.skch.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.skch.util.ReadXmlUtil;

public class TestDom4j {

	public static void main(String[] args) {
		InputStream in = null;
		Element root = null;
		try {
			SAXReader reader = new SAXReader(); 
			in = new FileInputStream(new File("d:/脚本/" + "预约脚本样例.xml"));
			Document doc = reader.read(in);
			root = doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		Element element2 = readXmlUtil.getElement("预约脚本样例.xml", "id001010");
		Element element = ((Element)root.element("Questions").elements().get(0)).element("Questions");
		System.out.println(element2.asXML());
		System.out.println(element.elements().size());
	}
	
}
