package com.skch.bean;

/***
 * 问句所在标签
 * @author Jerry
 *
 */
public class AskLabel {
	
	private AskLabelPosition askPos;//问句所在标签的位置
	
	public AskLabel() {
		
	}

	public AskLabel(AskLabelPosition askPos) {
		this.askPos = askPos;
	}

	public AskLabelPosition getAskPos() {
		return askPos;
	}

	public void setAskPos(AskLabelPosition askPos) {
		this.askPos = askPos;
	}

	public String disposeQuestion(){
		return null;
	}
	
	public AskLabelPosition disposeAnswer(){
		return null;
	}
}
