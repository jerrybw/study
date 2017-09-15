package com.skch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.skch.test.TestClassloader;


/***
 * 
 * @author jerry
 *读取xml的工具类
 */
public class ReadXmlUtil {
	
	//将找到的标签存入此属性中
	private Element element = null;
	
	private Map<String , Object> map = new HashMap<String ,Object>();
	
	//控制循环查找的标志
	private boolean flag = true;

	/***
	 * 根据传入的脚本名称获取脚本的根标签
	 * @param filename 带路径的脚本的名称
	 * @return 返回根标签
	 */
	public Element getRoot(String filename) {

		InputStream in = null;
		Element root = null;
		try {
			SAXReader reader = new SAXReader(); 
			in = ReadXmlUtil.class.getClassLoader().getResourceAsStream("script/"+filename);
			Document doc = reader.read(in);
			root = doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
	/***
	 * 根据传入的标签与标签名查找在此节点下的标签名为此的标签，并赋值给element
	 * @param node 标签
	 * @param nodeName 标签名
	 */
	public void getNodes(Element node,String nodeName){  
	      
	    //递归遍历当前节点所有的子节点  
	    List<Element> listElement = node.elements();  
	    for(Element e:listElement){
	    	//当FLAG为true时继续循环查找
	    	if(flag){
		    	if(nodeName.equals(e.getName())){
		    		//当找到时将找到的Element赋值给属性element，并将flag赋值为false跳出循环
		    		element = e;
		    		flag = false;
		    	}
		        getNodes(e,nodeName);//递归  
	    	} else {
	    		//flag为false跳出方法
	    		return;
	    	}
	    }
	}
	
	/***
	 * 根据传入的脚本名称与标签名称获取标签
	 * @param script 脚本名称
	 * @param nodeName 标签名称
	 * @return 标签元素
	 */
	public Element getElement(String script,String nodeName) {
		Element root = getRoot(script);
		getNodes(root, nodeName);
		return flash();
	}
	
	//将element的值返回并将其赋值为null
	public Element flash() {
		Element ele = element;
		flag = true;
		element = null;
		return ele;
	}
	
	//将元素转换成json字符串
	public String getEleJson(Element element,String script){
		String jsonStr = "{";
		jsonStr = jsonStr + "\"code\":1,";
		jsonStr = jsonStr + "\"answer\":\"\",";
		jsonStr = jsonStr + "\"requestion\":{";
		String ask = element.attributeValue("ask");
		if(ask != null ){
			ask = ask.replaceAll("\"", "\\\\\"");
			ask = ask.replaceAll("\'", "\\\\\'");
			ask = ask.replaceAll("\r\n", "\\\\\r\\\\\n");
		}
		jsonStr = jsonStr + "\"ask\":\"" + ask +"\",";
		String askId = element.getName();
		jsonStr = jsonStr + "\"askId\":\"" + askId +"\",";
		jsonStr = jsonStr + "\"script\":\"" + script +"\",";
		String face = element.attributeValue("face");
		jsonStr = jsonStr + "\"face\":" + face +",";
		String value = element.attributeValue("value");
		jsonStr = jsonStr + "\"value\":\""+value + "\"";
		jsonStr = jsonStr + "},";
		jsonStr = jsonStr + "\"questionId\":\"\"";
	    jsonStr = jsonStr + "}";
	    return jsonStr;
	}
	
	/**
	 * 在传入的路径对应的xml中查找传入的元素的弟标签
	 * @param fileName 路径
	 * @param element 标签
	 * @return	弟标签
	 */
	public Element getBroEle(Element root,Element element){
		String name = element.getName();
		String prefix = name.substring(0,name.length() - 3);
		String suffix = name.substring(name.length() - 3);
		int parseInt = Integer.parseInt(suffix);
		suffix = ""+(parseInt + 1);
		switch (suffix.length()) {
		case 1:
			suffix = "00"+suffix;
			break;
		case 2:
			suffix = "0"+suffix;
			break;
		case 3:
			break;
		default:
			break;
		}
		String nodeName = prefix + suffix;
		getNodes(root,nodeName);
		return flash();
	}
	
	/**
	 * 在传入的路径对应的xml中查找传入的标签的父标签
	 * @param fileName 路径
	 * @param element 标签
	 * @return 父标签
	 */
	public Element getFatherEle(Element root,Element element){
		String name = element.getName();
		String fatherName = name.substring(0,name.length() - 3);
		getNodes(root,fatherName);
		return flash();
	}
	
	/***
	 * 在传入的路径对应的xml中查找传入的标签的非子标签的下一个标签
	 * @param element 标签
	 * @param script 路径
	 * @return 下一个标签
	 */
	public Element getNext(Element element,Element root){
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		Element broEle = readXmlUtil.getBroEle(root,element);
		Element fatherEle = readXmlUtil.getFatherEle(root, element);
		//判断弟元素是否为空
		if(!(broEle == null)){
			//判断父元素是否为空
			if(fatherEle != null){
				//不为空，判断弟元素是否是与此元素在同一个questions标签中
				List<Element> queElements = fatherEle.elements();
				Element queElement = null;
				boolean flag = true;
				for (int i = 0; flag && i < queElements.size(); i++) {
					queElement = queElements.get(i);
					List<Element> childElements = queElement.elements();
					if (childElements != null) {
						for (int j = 0;flag && j < childElements.size(); j++) {
							String childName = childElements.get(j).getName();
							if (childName.equals(element.getName())) {
								flag = false;
							} 
						}
					} 
				}
				List<Element> elements = queElement.elements();
				boolean isBro = false;
				for (Element element2 : elements) {
					if (broEle.getName().equals(element2.getName())) {
						isBro = true;
						break;
					}
				}
				if(isBro){
					return broEle;
				} else {
					return getNext(fatherEle,root);
				}
			} else{
				return broEle;
			}
		} else {
			//为空，判断是否为最上层问题
			if(element.getName().length() == 5){
				//是,说明问题全问完
				return null;
			}else{
				//递归调运，查找父元素的下一个标签
				return getNext(fatherEle,root);
			}
		}
	}
	
	/***
	 * 在传入的路径对应的xml中查找传入的标签的子标签中的第一个标签
	 * @param element 标签
	 * @param script 路径
	 * @return 第一个子标签
	 */
	public Element getChild(Element element,String script){
		//判断子元素集合是否为空
		Element queElement = null;
		if(!element.elements().isEmpty()){
			List<Element> elements = element.elements();
			//不为空，获取他的value属性与condition属性对应的Questions标签
			String value = element.attributeValue("value");
			for (Element childrenEle : elements) {
				if(childrenEle.getName().equals("Questions") && childrenEle.attributeValue("condition").equals(value)){
					//获取对应的Questions标签下的ask标签集合
					List<Element> queElements = childrenEle.elements();
					//判断集合是否为空
					if (!queElements.isEmpty()) {
						//不为空，获取第一个ask标签赋值给questionEle
						queElement = queElements.get(0);
						break;
					}
				}
			}
		}
		return queElement;
	}
	
	public void addParam(Element element){
		Element parentEle = element.element("Questions");
		if(parentEle != null){
			List<Element> elements = parentEle.elements();
			if(!elements.isEmpty()){
				for (Element childEle : elements) {
					map.put(childEle.attributeValue("field"), childEle.attributeValue("value"));
					addParam(childEle);
				}
			}
		}	
	}
	
	public Map<String , Object> getParam(){
		return map;
	}
}
