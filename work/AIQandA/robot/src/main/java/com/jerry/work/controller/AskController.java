package com.jerry.work.controller;

import com.jerry.work.bean.Goto;
import com.jerry.work.dao.GotoRepository;
import com.jerry.work.dao.UserMapper;
import com.jerry.work.service.NavigationService;
import com.jerry.work.util.HttpRequest;
import com.jerry.work.util.ResultUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 向博文 on 2017/8/17.
 */
@RestController
public class AskController {

    @Value("${saveAlertMsgUrl}")
    private String saveAlertMsgUrl;

    @Autowired
    private GotoRepository gotoRepository;

    private final String USER_TYPE_DOCTOR = "JY_DOCTOR";

    private final String USER_TYPE_PATIENT = "JY_PATIENT";

    private final String CLIENT_ID_WEIXIN = "weixin";

    private final String CLIENT_ID_H5 = "h5";

    @Autowired
    private NavigationService navigationService;

    private Logger logger = Logger.getLogger(AskController.class);


    /**
     * 处理请求
     * @param jsonstr
     * @return
     */
    @RequestMapping("/ask")
    public String ask(String jsonstr){
        logger.info("参数"+jsonstr);
        String result = "";
        String clientId = "";
        try {
            String resultData = "";//返回的结果的字符串
            JSONObject object = JSONObject.fromObject(jsonstr);
            String deviceId = object.getString("deviceId");
            String userId = object.getString("userId");
            String longitude = object.getString("longitude");
            String latitude = object.getString("latitude");
            String userType = object.getString("userType");
            String ask = object.getJSONObject("answer").getString("value");
            if("【收到不支持的消息类型，暂无法显示】".equals(ask)){
                throw new RuntimeException("收到不支持的消息类型");
            }
            String questionId = object.getString("questionId");
            clientId = object.getString("clientId");
            if(USER_TYPE_DOCTOR.equals(userType)){
                resultData = getJY_DOCTORResult(ask,deviceId,userId,userType,longitude,latitude,questionId);
            }else if(USER_TYPE_PATIENT.equals(userType)){
                resultData = "患者端后台建设中";
            }else {
                resultData = "非法用户";
            }
            result =  ResultUtil.handSuccessResult(resultData);
        }catch (Exception e){
            logger.info("发生错误"+e.fillInStackTrace());
            //处理导航栏
            result = ResultUtil.handSuccessResult(navigationService.getNavigation("1"));
        }
        if(CLIENT_ID_H5.equals(clientId)){
            result = result.replaceAll("\\\\n","<br>");
        }
        logger.info("结果"+result);
        return result;
    }


    /**
     * 处理埋点的方法
     * @param s
     * @param userId
     * @return
     */
    public List<String> getBuriedPointResult(String s,String userId){
        List<String> results = new ArrayList<String>();
        while (true) {
            if (s.indexOf("&&") < 0){
                return results;
            }
            s = s.substring(s.indexOf("&&") + 2);
            String subject = s.substring(0, s.indexOf("&&"));
            s = s.substring(s.indexOf("&&") + 2);
            subject = "&" +subject;
            String url = getUrl(subject, userId);
            results.add(url);
        }
    }

    /**
     * 将查询结果转换成地址返回给用户
     * @param subject
     * @param userId
     * @return
     */
    public String getUrl(String subject,String userId){
        logger.info(subject.trim());
        Goto result = gotoRepository.findBySubject(subject.trim());
        logger.info(result);
        String script = result.getScript();
        String askId = result.getAskId();
        String weixin = result.getWeixin();
        String prompt = result.getPrompt();
        String value = result.getValue();
        String url = "<a href=\\'"+weixin +"?userId="+userId+"&script="+script+"&askId="+askId+"&value="+value+"\\'>"+prompt+"</a>";
        return url;
    }

    public String getJY_DOCTORResult(String ask,String deviceId,String userId,String userType,String longitude,String latitude,String questionId) throws Exception{
        String resultData = "";
        long timestamp = System.currentTimeMillis();
        String url = "http://47.93.160.47:8868/nlu";
        char c = ask.charAt(ask.length() - 1);
        if ('。' == c){
            ask = ask.substring(0,ask.length()-1);
        }
        ask = URLEncoder.encode(ask, "utf-8");
        String param = "AppID=1001&DeviceID=" + deviceId + "&Timestamp=" + timestamp + "&UserID=" + userId +
                "&Longitude=" + longitude + "&Latitude=" + latitude + "&UserType=" + userType + "&Question=" + ask + "&QuestionID=" + questionId;
        String result = HttpRequest.sendGet(url, param);
        JSONObject resultObj = JSONObject.fromObject(result);
        JSONObject answer = resultObj.getJSONObject("answer");
        try {//直接获取Answer
            resultData = answer.getString("Answer");//不报JSONException错，说明返回的答案可以直接返回给用户
        } catch (JSONException e) {//报JSONException错，说明不是直接返回给用户的答案，另做处理
            String s = answer.values().iterator().next().toString();
            if (answer.get("时间") != null) {//判断是否是提醒
                String date = answer.getString("日期");
                String time = answer.getString("时间");
                String thing = answer.getString("事件").replace("&", "");
                thing = URLEncoder.encode(thing,"utf-8");
                String resultJson = HttpRequest.sendPost(saveAlertMsgUrl, "date=" + date + "&time=" + time + "&thing=" + thing + "&userId=" + userId);
                JSONObject resultObject = JSONObject.fromObject(resultJson);
                resultData = resultObject.getString("answer");
                resultData = resultData.replaceAll("\n","\\\\n");
            } else if(s.contains("&")){//有埋点
                if(s.contains("&&")){
                    List<String> buriedPointResult = getBuriedPointResult(s,userId);
                    for (String res:buriedPointResult) {
                        resultData = resultData + res + "\\n";
                    }
                }else {
                    resultData = getUrl(s, userId);
                }
            } else {//以上都不是，直接传给用户
                Set<Map.Entry<String,String>> set = answer.entrySet();
                for (Map.Entry<String,String> entry: set) {
                    resultData = resultData + entry.getKey() + ":" + entry.getValue()+"\\n";
                }
            }
        }
        return resultData;
    }

}
