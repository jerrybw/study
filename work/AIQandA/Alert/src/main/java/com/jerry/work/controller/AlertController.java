package com.jerry.work.controller;

import com.jerry.work.dao.primary.AlertMessageRepository;
import com.jerry.work.service.Alert;
import com.jerry.work.util.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

/**
 * 用来处理存储提醒的接口
 * Created by 向博文 on 2017/8/24.
 */
@RestController
public class AlertController {

//    @Autowired
//    private AlertMessageRepository alertMessageRepository;//操作提醒数据库的数据库层

    @Value("${saveAlert}")
    private String saveAlert;//php提供的存储提醒的接口

    private Logger logger = Logger.getLogger(AlertController.class);


    /**
     *存储提醒
     * @param date 日期
     * @param time 时间
     * @param thing 事件
     * @param userId 被创建人id
     * @param remarkName 被创建人备注
     * @param creatorName 创建人名字
     * @param creatorId 创建人id
     * @return 返回创建成功标识 json code为1表示成功
     */
    @PostMapping("/alertMsg")
    public Object saveAlertMsg(String date,String time,String thing,String userId,String remarkName,String creatorName,String creatorId){
        logger.info("日期："+date+"  时间："+time+"  事件：" +thing + "userId:" + userId + "remarkName:" + remarkName + "creatorName:" + creatorName+ "creatorId:" + creatorId );
        boolean createBySelf = true;//判断最终是否是只给自己创建提醒
        boolean wantCreateToOther = false;//判断是否想给他人创建提醒
        String objectId = "";
        String objectName = "";
        if(!"".equals(creatorId) && !"".equals(creatorName)){//识别出想给谁创建提醒
            wantCreateToOther = true;
            createBySelf = false;
            objectId = userId;
            objectName = remarkName;
            userId = creatorId;
        }else if(!"".equals(creatorId) && "".equals(creatorName)) {//未识别出想给谁创建提醒
            wantCreateToOther = true;
            /**
             * 获取申请人信息
             */
            String userMessage = GetMessageUtil.getUserMessageByUserId(creatorId);
            JSONObject userMessageJson = JSONObject.fromObject(userMessage);
            JSONObject userInfoJson = userMessageJson.getJSONObject("info");
            JSONObject userInfoJsonObj = userInfoJson.getJSONObject("info");
            creatorName = userInfoJsonObj.getString("m_renming");
            if(creatorId.equals(userId)){//没有单向好友的情况
                userId = creatorId;
                createBySelf = true;
            }else {//存在单向好友情况，
                createBySelf = false;
                objectId = userId;
                objectName = remarkName;
                userId = creatorId;
            }
        }else {//识别出是只想给自己创建提醒
            createBySelf = true;
            wantCreateToOther = false;
        }
        boolean flag = false;//判断提醒设置的时间是否过时
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = dateFormatter.parse(date + " " + time + ":00" , Locale.getDefault());
        } catch (ParseException e) {
            logger.error("日期时间格式不正确");
            return ResultUtil.handErrorResult("日期时间格式不正确");
        }
        long parseTime = parse.getTime();
        long restTime = parseTime - (new Date()).getTime();//距离提醒剩余时间
        if(restTime <= 0){//如果剩余时间小于等于0
            flag = true;
            long days = -restTime / (1000 * 60 * 60 * 24 );//设置的提醒日期早于今天的天数
            parseTime += days * 1000 * 60 * 60 * 24 + 1000 * 60 * 60 * 12;
            restTime = parseTime - (new Date()).getTime();
            if(restTime <= 0){
                parseTime += 1000 * 60 * 60 * 12;
            }
        }
        Date dateTime = new Date(parseTime);
        String resultDate = dateFormatter.print(dateTime, Locale.getDefault());
        //向存储提醒接口发请求将提醒存入数据库中
        String s = HttpRequest.sendPost(saveAlert, "type=2&start_time=" + resultDate + "&content=" + thing + "&uid="+userId + "&creatorName="+creatorName+ "&objectId="+objectId+ "&objectName="+objectName );
        JSONObject object = JSONObject.fromObject(s);
        JSONObject info = object.getJSONObject("info");
        String appointment_id = info.getString("appointment_id");
        if(!createBySelf){
            HttpRequest.sendPost(saveAlert, "type=2&start_time=" + resultDate + "&content=" + thing + "&uid="+objectId + "&creatorName="+creatorName+ "&objectId=&objectName=");
            try {
                String clickUrl = "";
                ClassLoader classLoader = AlertController.class.getClassLoader();
                InputStream in = classLoader.getResourceAsStream("url.properties");
                Properties urlProperties = new Properties();
                String dAlertUrl = "";
                String pAlertUrl = "";
                try {
                    urlProperties.load(in);
                    dAlertUrl = urlProperties.getProperty("dAlertUrl");
                    pAlertUrl = urlProperties.getProperty("pAlertUrl");
                } catch (IOException e) {
                    logger.error("获取模板消息url失败");
                }
                if(IsDoctor.isDoctor(objectId)){
                    clickUrl = dAlertUrl + "/appointment_id/"+appointment_id+"/uid/"+objectId;
                }else {
                    clickUrl = pAlertUrl + "/appointment_id/"+appointment_id+"/uid/"+objectId;
                }
                String first = "您收到一个来自【"+creatorName+"】的提醒";
                String keyWord1 = thing;
                String keyWord2 = GetNowStr.getNowStr();
                Map<String,String> keyWords = new HashMap<String,String>();
                keyWords.put("keyword1",keyWord1);
                keyWords.put("keyword2",keyWord2);
                SendWeiXin.sendWeiXin("onCreateAlert",objectId,clickUrl,first,keyWords,"点击进行查看");
            }catch (Exception e){
                logger.error("发送模板消息失败");
            }
        }
        String result = "";
        if (flag){
            result  = ResultUtil.handSuccessResult("您设置的提醒时间已过,已为您自动匹配了一个时间\\n"+ Alert.getAlert(resultDate,thing,appointment_id,userId, remarkName,createBySelf,wantCreateToOther));
        }else {
            result = ResultUtil.handSuccessResult(Alert.getAlert(resultDate,thing,appointment_id,userId,remarkName,createBySelf,wantCreateToOther));
        }
        return result;
    }

//    @PostMapping("/alertMsg")
//    public Object saveAlertMsg(String date,String time,String thing,String userId){
//        logger.info("日期："+date+"  时间："+time+"  事件：" +thing+"    userId:" +userId);
//        boolean flag = false;
//        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
//        Date parse = null;
//        try {
//            parse = dateFormatter.parse(date + " " + time + ":00" , Locale.getDefault());
//        } catch (ParseException e) {
//            logger.error("日期时间格式不正确");
//            return ResultUtil.handErrorResult("日期时间格式不正确");
//        }
//        long parseTime = parse.getTime();
//        long restTime = parseTime - (new Date()).getTime();//距离提醒剩余时间
//        if(restTime <= 0){//如果剩余时间小于等于0
//            flag = true;
//            long days = -restTime / (1000 * 60 * 60 * 24 );//设置的提醒日期早于今天的天数
//            parseTime += days * 1000 * 60 * 60 * 24 + 1000 * 60 * 60 * 12;
//            restTime = parseTime - (new Date()).getTime();
//            if(restTime <= 0){
//                parseTime += 1000 * 60 * 60 * 12;
//            }
//        }
//        AlertMessage alertMessage = new AlertMessage();
//        alertMessage.setFireMoment(parseTime);
//        alertMessage.setMsg(thing);
//        alertMessage.setToId(userId);
//        alertMessage.setBegin(parseTime);
//        AlertMessage save = alertMessageRepository.save(alertMessage);
//        if(save == null){
//            logger.error("提醒存储失败");
//            return ResultUtil.handErrorResult("提醒存储失败");
//        }
//        Date dateTime = new Date(parseTime);
//        String resultDate = dateFormatter.print(dateTime, Locale.getDefault());
//        String[] split = resultDate.split(" ");
//        String result = "";
//        if (flag){
//            result  = ResultUtil.handSuccessResult("您设置的提醒时间已过,已为您自动匹配了一个时间\\n"+ Alert.getAlert(resultDate,thing));
//        }else {
//            result = ResultUtil.handSuccessResult(Alert.getAlert(resultDate,thing));
//        }
//        return result;
//    }
}
