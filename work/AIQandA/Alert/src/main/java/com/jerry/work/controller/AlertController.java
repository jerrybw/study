package com.jerry.work.controller;

import com.jerry.work.dao.primary.AlertMessageRepository;
import com.jerry.work.service.Alert;
import com.jerry.work.util.HttpRequest;
import com.jerry.work.util.ResultUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * 用来处理存储提醒的接口
 * Created by 向博文 on 2017/8/24.
 */
@RestController
public class AlertController {

    @Autowired
    private AlertMessageRepository alertMessageRepository;

    private Logger logger = Logger.getLogger(AlertController.class);

    /**
     *存储提醒
     * @param date 日期
     * @param time 时间
     * @param thing 事件
     * @return
     */
    @PostMapping("/alertMsg")
    public Object saveAlertMsg(String date,String time,String thing,String userId){
        logger.info("日期："+date+"  时间："+time+"  事件：" +thing + "userId:" + userId);
        boolean flag = false;
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
        String s = HttpRequest.sendPost("http://d.china-healthcare.cn/App/jk/id/JC124", "type=2&start_time=" + resultDate + "&content=" + thing + "&uid="+userId);
        JSONObject object = JSONObject.fromObject(s);
        JSONObject info = object.getJSONObject("info");
        String appointment_id = info.getString("appointment_id");
        String result = "";
        if (flag){
            result  = ResultUtil.handSuccessResult("您设置的提醒时间已过,已为您自动匹配了一个时间\\n"+ Alert.getAlert(resultDate,thing,appointment_id,userId));
        }else {
            result = ResultUtil.handSuccessResult(Alert.getAlert(resultDate,thing,appointment_id,userId));
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
