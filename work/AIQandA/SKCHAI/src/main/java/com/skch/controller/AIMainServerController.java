package com.skch.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.mongodb.morphia.annotations.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skch.bean.Answer;
import com.skch.bean.AskLabel;
import com.skch.bean.AskLabelPosition;
import com.skch.bean.ScriptsFile;
import com.skch.bean.UserQuestion;
import com.skch.service.DialogsService;
import com.skch.service.MainService;
import com.skch.service.ScriptsService;
import com.skch.service.StackService;
import com.skch.service.VariableService;
import com.skch.util.ReadXmlUtil;

import net.sf.json.JSONObject;
/***
 * 主程序用于接受客户端的请求及请求参数，并返回json数据
 * @author jerry
 *
 */
@Controller
public class AIMainServerController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private ScriptsService scriptsService;
	

	@Autowired
	private DialogsService dialogsService;
	
	@Autowired
	private StackService stackService;
	
	@Autowired
	private VariableService variableService;
	
	private Logger logger = Logger.getLogger(AIMainServerController.class);
	
	/***
	 * 主程序的入口，用于接受请求与请求参数
	 * @jsonstr jsonstr 客户端传入的json字符串
	 * @return json数据
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="askDr",produces="text/html;charset=utf-8",method=RequestMethod.POST)
	public Object mainServer(String jsonstr,HttpServletResponse response) throws UnsupportedEncodingException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
		logger.info("接受的参数:"+jsonstr);
		//处理传入的数据
		UserQuestion userQuestion = mainService.getUserQuestion(jsonstr);
		Answer answer = userQuestion.getAnswer();
		String userId = userQuestion.getUserId();
		//用户传入的问句
		AskLabel askLabel = null;
		//获取传入的答案
		String value = answer.getValue();
		//要传回给用户的问句
		AskLabelPosition nextAsk = null;
		//要传回给用户的问句所形成的json串
		String result = null;
		//判断对话是否过期或者是否为报到
		if(!mainService.check(jsonstr)){
			//定位到问句所在脚本
			ScriptsFile scriptsFile = scriptsService.getScriptsFile(answer.getScript());
			//定位到问句
			askLabel = new AskLabel(new AskLabelPosition(answer.getScript(),answer.getAskId()));
			//不是，则存储问句
			if(value != null && !"".equals(value) ){
				//判断问句标签是否有variable属性
				Element element = readXmlUtil.getElement(answer.getScript(), answer.getAskId());
				element = mainService.handVariable(element, userQuestion);
				element = mainService.handMethod(element);
				String variable = element.attributeValue("variable");
				if(variable != null && !"".equals(variable)){
					String face = element.attributeValue("face");
					JSONObject fromObject = JSONObject.fromObject(face);
					Object object = fromObject.get("breakReturn");
					if(object == null){
							variableService.setValue(userId, variable, value);
					} else {
						if(!value.equals(object.toString())){
							variableService.setValue(userId, variable, value);
						}
					}
				}
				dialogsService.addSentence(userId, "AI", askLabel.getAskPos().getScriptsFile(), askLabel.getAskPos().getAskId(), value);
			}
			//定位到下一个问句
			nextAsk = scriptsFile.getAskLabelNext(answer.getAskId(),value);
			//判断脚本是否执行完毕
			while (nextAsk == null){
				//出栈
				nextAsk = stackService.pop(userId);
			} ;
			String fileName = nextAsk.getScriptsFile();
			String askId = nextAsk.getAskId();
			if(fileName == null || "".equals(fileName)){
				return scriptBreaked();
			}
			Element nextElement = readXmlUtil.getElement(fileName , askId);
			//获取标签的goto属性
			String QueGoto = nextElement.attributeValue("goto");
			//判断goto属性是否为空
			while (QueGoto != null && !"".equals(QueGoto)) {
				//解析变量
				nextElement = mainService.handVariable(nextElement, userQuestion);
				//解析方法
				nextElement = mainService.handMethod(nextElement);
				//得到标准问句
				askLabel.getAskPos().setAskId(nextElement.getName());
				QueGoto = nextElement.attributeValue("goto");
				String[] split = QueGoto.split(",");
				askId = split[1].substring(1, split[1].length() - 2);
				String isGotoOther = split[0].substring(2, split[0].length() - 1);
				//不为空则判断是否是脚本间跳转
				if(!isGotoOther.equals("")){
					fileName = split[0].substring(2, split[0].length() - 1);
					//是脚本间,压栈
					stackService.push(userId, askLabel.getAskPos());
					askLabel.getAskPos().setScriptsFile(fileName);
					askLabel.getAskPos().setAskId(askId);
					nextElement = readXmlUtil.getElement(fileName, askId);
				}else{
//					不是脚本间
					if(askId.equals("id999")){
						do{
							//出栈
							nextAsk = stackService.pop(userId);
							fileName = nextAsk.getScriptsFile();
							askId = nextAsk.getAskId();
						}while (nextAsk == null);
						if(fileName == null || "".equals(fileName)){
							return scriptBreaked();
						}
						nextElement = readXmlUtil.getElement(nextAsk.getScriptsFile(), nextAsk.getAskId());
					}else{
						nextElement = readXmlUtil.getElement(fileName, askId);
					}
					
				}
				QueGoto = nextElement.attributeValue("goto");
			}
			nextAsk.setScriptsFile(fileName);
			nextAsk.setAskId(askId);
		} else {
			//为报到，返回主脚本中第一个问句
			stackService.flash(userId);
			nextAsk = new AskLabelPosition();
			nextAsk.setScriptsFile("main.xml");
			nextAsk.setAskId("id001");
		}
		String scriptsFile = nextAsk.getScriptsFile();
		String askId = nextAsk.getAskId();
		Element element = readXmlUtil.getElement(scriptsFile, askId);
		element = mainService.handVariable(element, userQuestion);
		element = mainService.handMethod(element);
		//将标签中数据转换成json字符串返回给客户端
		dialogsService.addSentence( "AI",userId, nextAsk.getScriptsFile(), nextAsk.getAskId(), element.attributeValue("ask"));
		result = readXmlUtil.getEleJson(element, scriptsFile);
		JSONObject fromObject = JSONObject.fromObject(result);
		result = fromObject.toString();
		logger.info("返回结果为："+result);
		return result;
	}
	
	
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="login",produces="text/html;charset=utf-8")
	public Object login(){
		String login = "{'code':1,'answer':'','requestion':{'ask':'您还未登录，请登录','askId':'id001','script':'login.xml','face':{'type':'picker','list':['去登录']},'value':''},'questionId':''}";
		login = JSONObject.fromObject(login).toString();
		return(login);
	}
	
	public String scriptBreaked(){
		JSONObject fromObject = JSONObject.fromObject("{'code':1,'answer':'','requestion':{'ask':'','askId':'','script':'','face':{'type':'msg'},'value':''},'questionId':''}");
		return fromObject.toString();
	}
	
}
