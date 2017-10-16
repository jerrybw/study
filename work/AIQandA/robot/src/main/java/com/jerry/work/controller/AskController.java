package com.jerry.work.controller;

import com.jerry.work.bean.Goto;
import com.jerry.work.dao.GotoRepository;
import com.jerry.work.service.NavigationService;
import com.jerry.work.util.HttpRequest;
import com.jerry.work.util.ResultUtil;
import net.sf.json.JSONArray;
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
 * 此项目用来处理用户与机器人对话的所有操作
 * Created by 向博文 on 2017/8/17.
 */
@RestController
public class AskController {

    @Value("${saveAlertMsgUrl}")
    private String saveAlertMsgUrl;//存储提醒接口调用地址

    @Value("${aiUrl}")
    private String aiUrl;//真正的语义分析后台接口调用地址

    @Value("${sendtmpd}")
    private String sendtmpd;//发送医端模板消息接口调用地址

    @Value("${sendtmpd}")
    private String sendtmpp;//发送患端端模板消息接口调用地址

    @Autowired
    private GotoRepository gotoRepository;//操作锚点数据表的数据库层

    private final String USER_TYPE_DOCTOR = "JY_DOCTOR";//家医医端匹配标识

    private final String USER_TYPE_PATIENT = "JY_PATIENT";//家医患端匹配标识

    private final String CLIENT_ID_WEIXIN = "weixin";//微信中聊天匹配标识

    private final String CLIENT_ID_H5 = "h5";//h5页面im中聊天匹配标识

    @Autowired
    private NavigationService navigationService;//处理导航栏的业务逻辑层

    private Logger logger = Logger.getLogger(AskController.class);


    /**
     * 处理请求
     *
     * @param jsonstr 请求参数
     * @return 返回值
     */
    @RequestMapping("/ask")
    public String ask(String jsonstr) {
        logger.info("参数" + jsonstr);
        String result = "";
        String clientId = "";
        String userType = "";
        //解析传入的参数
        try {
            String resultData = "";//返回的结果的字符串
            JSONObject object = JSONObject.fromObject(jsonstr);
            userType = object.getString("userType");
            String deviceId = object.getString("deviceId");
            String userId = object.getString("userId");
            String longitude = object.getString("longitude");
            String latitude = object.getString("latitude");
            String ask = object.getJSONObject("answer").getString("value");//提取出参数中真正的问句
            ask = ask.replaceAll(" |\\||,|，", "");//将问句中 和|和,和，替换为空字符串
            char c = ask.charAt(ask.length() - 1);
            if ('。' == c) {//判断问句最后是否是句号，主要解决语音识别出的问句都带有句号的问题
                ask = ask.substring(0, ask.length() - 1);
            }
            //如果微信中用户发送的是表情图片等时传入的问句就是【收到不支持的消息类型，暂无法显示】，此时直接返回导航栏
            if ("【收到不支持的消息类型，暂无法显示】".equals(ask)) {
                throw new RuntimeException("收到不支持的消息类型");
            }
            String questionId = object.getString("questionId");
            clientId = object.getString("clientId");
            if (USER_TYPE_DOCTOR.equals(userType)) {//判断医端患端，做区别处理
                resultData = getJY_DOCTORResult(ask, deviceId, userId, userType, longitude, latitude, questionId);
            } else if (USER_TYPE_PATIENT.equals(userType)) {
                resultData = getJY_PATIENTResult(ask, deviceId, userId, userType, longitude, latitude, questionId);
                if ("".equals(resultData)) {//现在患者端除了是创建提醒外全都是返回空字符串，此时返回给用户导航栏
                    throw new RuntimeException();
                }
            } else {
                resultData = "非法用户";
            }
            result = ResultUtil.handSuccessResult(resultData);
        } catch (Exception e) {
            logger.info("发生错误" + e.fillInStackTrace());
            //处理导航栏
            if (USER_TYPE_DOCTOR.equals(userType)) {//医患两端导航栏做区别处理
                result = ResultUtil.handSuccessResult(navigationService.getNavigation("1"));
            } else {
                result = ResultUtil.handSuccessResult(navigationService.getNavigation("2"));
            }
        }
        if (CLIENT_ID_H5.equals(clientId)) {//处理im中与微信中换行的区别
            result = result.replaceAll("\\\\n", "<br>");
        }
        logger.info("结果" + result);
        return result;
    }

    /**
     * 处理医生端问答
     * @param ask 问题
     * @param deviceId deviceId
     * @param userId 用户id
     * @param userType 用户类型
     * @param longitude 经度
     * @param latitude 维度
     * @param questionId 问题id
     * @return 返回值
     * @throws Exception
     */
    private String getJY_DOCTORResult(String ask, String deviceId, String userId, String userType, String longitude, String latitude, String questionId) throws Exception {
        String resultData = "";
        long timestamp = System.currentTimeMillis();
        //向语义分析后台接口发请求
        String url = aiUrl;
        ask = URLEncoder.encode(ask, "utf-8");
        String param = "AppID=1001&DeviceID=" + deviceId + "&Timestamp=" + timestamp + "&UserID=" + userId +
                "&Longitude=" + longitude + "&Latitude=" + latitude + "&UserType=" + userType + "&Question=" + ask + "&QuestionID=" + questionId;
        String result = HttpRequest.sendGet(url, param);
        //处理返回值
        JSONObject resultObj = JSONObject.fromObject(result);
        JSONObject answer = resultObj.getJSONObject("answer");
        try {//直接获取Answer
            resultData = answer.getString("Answer");//不报JSONException错，说明返回的答案可以直接返回给用户
        } catch (JSONException e) {//报JSONException错，说明不是直接返回给用户的答案，另做处理
            String s = answer.values().iterator().next().toString();
            if (answer.get("时间") != null) {//判断是否是提醒
                resultData = getAlertResult(answer);
            } else if (s.contains("&")) {//有埋点
                resultData = getBuriedPointResult(s, resultData, userId);
            } else {//以上都不是，直接传给用户
                Set<Map.Entry<String, String>> set = answer.entrySet();
                for (Map.Entry<String, String> entry : set) {
                    resultData = resultData + entry.getKey() + ":" + entry.getValue() + "\\n";
                }
            }
        }
        return resultData;
    }

    /**
     * 处理患者端问答
     * @param ask
     * @param deviceId
     * @param userId
     * @param userType
     * @param longitude
     * @param latitude
     * @param questionId
     * @return
     * @throws Exception
     */
    public String getJY_PATIENTResult(String ask, String deviceId, String userId, String userType, String longitude, String latitude, String questionId) throws Exception {
        String resultData = "";
        long timestamp = System.currentTimeMillis();
        String url = aiUrl;
        ask = URLEncoder.encode(ask, "utf-8");
        String param = "AppID=1001&DeviceID=" + deviceId + "&Timestamp=" + timestamp + "&UserID=" + userId +
                "&Longitude=" + longitude + "&Latitude=" + latitude + "&UserType=" + userType + "&Question=" + ask + "&QuestionID=" + questionId;
        String result = HttpRequest.sendGet(url, param);
        JSONObject resultObj = JSONObject.fromObject(result);
        JSONObject answer = resultObj.getJSONObject("answer");
        try {//直接获取Answer
            resultData = answer.getString("Answer");//不报JSONException错，说明返回的答案可以直接返回给用户
            resultData = "";
        } catch (JSONException e) {//报JSONException错，说明不是直接返回给用户的答案，另做处理
            String s = answer.values().iterator().next().toString();//获取answer下所有属性的属性值中的第一个
            if (answer.get("时间") != null) {//判断是否是提醒
                resultData = getAlertResult(answer);
            } else if (s.contains("&")) {//有埋点
                resultData = getBuriedPointResult(s, resultData, userId);
                resultData = "";
            } else {//以上都不是，直接传给用户
                Set<Map.Entry<String, String>> set = answer.entrySet();
                for (Map.Entry<String, String> entry : set) {
                    resultData = resultData + entry.getKey() + ":" + entry.getValue() + "\\n";
                }
                resultData = "";
            }
        }
        return resultData;
    }

    /**
     * 获取埋点的方法(点此打开)
     * @param s
     * @param resultData
     * @param userId
     * @return
     */
    public String getBuriedPointResult(String s, String resultData, String userId) {
        if (s.contains("&&")) {
            List<String> buriedPointResult = getBuriedPointResult(s, userId);
            for (String res : buriedPointResult) {
                resultData = resultData + res + "\\n";
            }
        } else {
            resultData = getUrl(s, userId);
        }
        return resultData;
    }

    /**
     * 将埋点转为url的方法
     *
     * @param s
     * @param userId
     * @return
     */
    public List<String> getBuriedPointResult(String s, String userId) {
        List<String> results = new ArrayList<String>();
        while (true) {
            if (s.indexOf("&&") < 0) {
                return results;
            }
            s = s.substring(s.indexOf("&&") + 2);
            String subject = s.substring(0, s.indexOf("&&"));
            s = s.substring(s.indexOf("&&") + 2);
            subject = "&" + subject;
            String url = getUrl(subject, userId);
            results.add(url);
        }
    }

    /**
     * 将查询结果转换成地址返回给用户
     *
     * @param subject
     * @param userId
     * @return
     */
    public String getUrl(String subject, String userId) {
        logger.info(subject.trim());
        Goto result = gotoRepository.findBySubject(subject.trim());
        logger.info(result);
        String script = result.getScript();
        String askId = result.getAskId();
        String weixin = result.getWeixin();
        String prompt = result.getPrompt();
        String value = result.getValue();
        String url = "<a href='" + weixin + "?userId=" + userId + "&script=" + script + "&askId=" + askId + "&value=" + value + "'>" + prompt + "</a>";
        return url;
    }


    /**
     * 处理创建提醒的方法
     * @param answer
     * @return
     * @throws Exception
     */
    public String getAlertResult(JSONObject answer) throws Exception {
        String alertUserId = "";
        String remarkName = "";
        String creatorName = "";
        String creatorId = "";
        String date = answer.getString("日期");
        if (date.contains("|")) {//若返回的日期中有多个日期则取第一个日期
            String[] split = date.split("|");
            date = split[0];
        }
        String time = answer.getString("时间");
        String thing = answer.getString("事件").replace("&", "");
        JSONArray receiver = answer.getJSONArray("Receiver");
        for (int i = 0; i < receiver.size(); i++) {
            Object o = receiver.get(i);
            JSONObject object = JSONObject.fromObject(o);
            alertUserId = object.getString("UserID");
            remarkName = object.getString("RemarkName");
            creatorName = object.getString("CreatorName");
            creatorId = object.getString("CreatorID");
        }
        thing = URLEncoder.encode(thing, "utf-8");
        String resultJson = HttpRequest.sendPost(saveAlertMsgUrl, "date=" + date + "&time=" + time + "&thing=" + thing + "&userId=" + alertUserId + "&remarkName=" + remarkName + "&creatorName=" + creatorName + "&creatorId=" + creatorId);
        JSONObject resultObject = JSONObject.fromObject(resultJson);
        String resultData = resultObject.getString("answer");
        resultData = resultData.replaceAll("\n", "\\\\n");
        return resultData;
    }

}
