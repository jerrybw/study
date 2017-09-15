package com.skch.bean;

/***
 * 定义某个问题的位置
 * @author Jerry
 *
 */
public class AskLabelPosition {
	
	private String scriptsFile;//问题所在的脚本
	
	private String askId;//问题在脚本中所对应的标签名
	
	public AskLabelPosition() {
		
	}

	public AskLabelPosition(String scriptsFile, String askId) {
		this.scriptsFile = scriptsFile;
		this.askId = askId;
	}

	public String getScriptsFile() {
		return scriptsFile;
	}

	public void setScriptsFile(String scriptsFile) {
		this.scriptsFile = scriptsFile;
	}

	public String getAskId() {
		return askId;
	}

	public void setAskId(String askId) {
		this.askId = askId;
	}
	
}
