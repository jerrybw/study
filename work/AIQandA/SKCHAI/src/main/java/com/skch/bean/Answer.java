package com.skch.bean;

/***
 * 
 * 定义用户传入的answer
 * @author Jerry
 *
 */
public class Answer {
	private String askId;
	private String script;
	private String value;
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
