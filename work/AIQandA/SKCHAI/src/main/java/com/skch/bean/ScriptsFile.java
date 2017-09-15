package com.skch.bean;

import java.util.List;

import org.dom4j.Element;

import com.skch.util.ReadXmlUtil;

/***
 * 定义脚本
 * @author Jerry
 *
 */
public class ScriptsFile {
	
	private String fileName;//脚本名

	private Element root;//脚本根节点标签
	
	public ScriptsFile() {
		
	}

	public ScriptsFile(String fileName) {
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		this.fileName = fileName;
		this.root = readXmlUtil.getRoot(fileName);
	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/***
	 * 根据传入的标签名获取标签
	 * @param askId 
	 * @return 
	 */
	public AskLabelPosition getAskLabel(String askId){
		
		return new AskLabelPosition(fileName, askId);
	}
	
	/***
	 * 根据传入的标签名获取下一个标签
	 * @param askId 标签名
	 * @return 若有下一个标签则返回值不为null 若无下一个标签则返回null 表示此脚本运行完毕，应当进行出栈操作
	 */
	public AskLabelPosition getAskLabelNext(String askId,String value){
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		readXmlUtil.getNodes(root, askId);
		Element askEle = readXmlUtil.flash();
		List<Element> queElements = askEle.elements();
		if(!queElements.isEmpty()){
			for (Element element : queElements) {
				String condition = element.attributeValue("condition");
				if (condition.equals(value)) {
					String name = ((Element)element.elements().get(0)).getName();
					return new AskLabelPosition(fileName,name);
				}
			}
		}
		Element next = readXmlUtil.getNext(readXmlUtil.getElement(fileName, askId), root);
		if(next != null){
			return new AskLabelPosition(fileName,next.getName());
		}
		return null;
	}
	
	/***
	 * 获取传入的标签下的condition对应的question标签
	 * @param askId 标签名
	 * @param condition condition（在ai中对应用户传入的answer中的value属性）
	 * @return
	 */
	public QuestionsLabel getQuestionsLabel(String askId,String condition){
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		Element element = readXmlUtil.getElement(fileName, askId);
		List<Element> elements = element.elements();
		for (Element queEle : elements) {
			if (queEle.attributeValue("condition").equals(condition)) {
				return new QuestionsLabel(new AskLabelPosition(fileName,askId),condition);
			}
		}
		return null;
	}
}
