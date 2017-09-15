package com.skch.bean;

import java.util.List;

import org.dom4j.Element;

import com.skch.util.ReadXmlUtil;

/***
 * 定义 question标签
 * @author Jerry
 *
 */
public class QuestionsLabel {
	
	private AskLabelPosition parant;//此标签的父标签
	
	private String condition;//此标签的condition属性
	
	public QuestionsLabel() {
		
	}

	public QuestionsLabel(AskLabelPosition parant, String condition) {
		this.parant = parant;
		this.condition = condition;
	}

	public AskLabelPosition getParant() {
		return parant;
	}

	public void setParant(AskLabelPosition parant) {
		this.parant = parant;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	/***
	 * 获取此Question标签下的第一个ask标签
	 * @return 若返回值为null则说明没找到 应获取其parant所代表的标签的非子标签的下一个标签
	 */
	public AskLabel getAsklabel(){
		String fileName = parant.getScriptsFile();
		String askId = parant.getAskId();
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		Element element = readXmlUtil.getElement(fileName, askId);
		List<Element> elements = element.elements();
		for (Element queEle : elements) {
			if (queEle.attributeValue("condition").equals(condition)) {
				List<Element> askList = queEle.elements();
				if (!askList.isEmpty()) {
					Element askEle = askList.get(0);
					return new AskLabel(new AskLabelPosition(fileName,askEle.getName()));
				}
				return null;
			}
		}
		return null;
	}
	
	/***
	 * 获取下一个 question标签 预留
	 * @return
	 */
	public QuestionsLabel getNext(){
		return null;
	}
}
