package com.jerry.work.service;

import com.jerry.work.bean.primary.AlertMessage;
import com.jerry.work.bean.secondary.AlertMsg;
import com.jerry.work.dao.primary.AlertMessageRepository;
import com.jerry.work.dao.secondary.AlertMsgRepository;
import com.jerry.work.util.IsDoctor;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 向博文 on 2017/8/18.
 */
@Service
public class AlertService {

    @Autowired
    private AlertMessageRepository alertMessageRepository;//操作subscribe中提醒的数据库层

    @Autowired
    private AlertMsgRepository alertMsgRepository;//操作family中提醒的数据库层

    private Logger logger = Logger.getLogger(AlertService.class);

    public void alert(){
        //获取当前时间
        Date date = new Date();
        long time = date.getTime();
        time += 1000*60*2;
        date = new Date(time);
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        String now = dateFormatter.print(date, Locale.getDefault());
        ClassLoader classLoader = AlertService.class.getClassLoader();
        //获取提醒展示页面地址
        InputStream in = classLoader.getResourceAsStream("url.properties");
        Properties urlProperties = new Properties();
        String dAlertUrl = "";
        String pAlertUrl = "";
        try {
            urlProperties.load(in);
            dAlertUrl = urlProperties.getProperty("dAlertUrl");
            pAlertUrl = urlProperties.getProperty("pAlertUrl");
        } catch (IOException e) {
            logger.error("获取提醒展示页面url失败");
            return;
        }
        //查找早于或等于当前时间的有效提醒
        List<AlertMsg> alertMsgs = alertMsgRepository.findByYyStartLessThanEqualAndYyTypeAndValidOrYyRemindLessThanEqualAndYyState("1", "2", now, now,"1");//
        for (AlertMsg alertMsg: alertMsgs) {//遍历发送模板消息
            Integer id = alertMsg.getId();
            String toId = alertMsg.getYyUid();
            String keyWord2 = alertMsg.getYyStart();
            String keyWord1 = alertMsg.getYyContent();
            String msg = keyWord2 + keyWord1;
            if(msg != null&&!"".equals(msg)) {
                logger.info("php数据库中 向"+toId+"发ims提醒"+msg);
                SendResult.appadd("888888888", toId, msg, "friend");
                String url = "";
                String clickUrl = "";
                if(IsDoctor.isDoctor(toId)){
                    clickUrl = dAlertUrl + "/appointment_id/"+id+"/uid/"+toId;
                }else {
                    clickUrl = pAlertUrl + "/appointment_id/"+id+"/uid/"+toId;
                }
                String first = "您有一个新的提醒已到时";
                String remark = "点击进行查看";
                logger.info("php数据库中 向"+toId+"发模板消息提醒"+msg);
                Map<String,String> keyWords = new HashMap<String,String>();
                keyWords.put("keyword1",keyWord1);
                keyWords.put("keyword2",keyWord2);
                try {
                    SendWeiXin.sendWeiXin("onAlert",toId,clickUrl,first,keyWords,remark);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.info(e.fillInStackTrace());
                }
//                SendWeiXin.sendWeiXin(url,toId,clickUrl,first,keyWord1,keyWord2,remark);
            }
            //将数据库中提醒设置为无效
            alertMsg.setValid("0");
            alertMsgRepository.save(alertMsg);
        }


        /**
         * 之前存在subscribe中的提醒，现在不用了暂时保留
         */
        List<AlertMessage> alertMessages = alertMessageRepository.findByFireMomentLessThanEqualAndExcept(time,0);
        for (AlertMessage alertMessage:alertMessages){
            Integer weixin = alertMessage.getWeixin();
            Integer sms = alertMessage.getSms();
            Integer ims = alertMessage.getIms();
            Integer relay = alertMessage.getRelay();
            String keyword1 = alertMessage.getMsg();
            Integer leftTimes = alertMessage.getLeftTimes();
            Long fireMoment = alertMessage.getFireMoment();
            String toId = alertMessage.getToId();
            if(weixin == 1){//如果微信提醒为1，则进入判断发微信提醒
                logger.info("向"+toId+"发微信提醒"+keyword1);
                String clickUrl = "www.baidu.com";
//                SendWeiXin.sendWeiXin(url,toId,clickUrl,"提醒",keyword1,"","");
            }
            if (ims == 1){//如果ims提醒为1，则发ims提醒
                if(keyword1 != null&&!"".equals(keyword1)) {
                    Date alertTime = new Date(fireMoment);
                    String alertTimeStr = dateFormatter.print(alertTime, Locale.getDefault());
                    keyword1 = alertTimeStr + keyword1;
                    logger.info("向"+toId+"发ims提醒"+keyword1);
                    SendResult.appadd("888888888", toId, keyword1, "friend");
                }
            }
            if (sms == 1){//如果sms提醒为1，则发ims提醒
                logger.info("向"+toId+"发sms提醒"+keyword1);
            }
            if((leftTimes -= 1) <= 0){//将leftTimes减去一次，然后判断是否小于等于0，若为0则使alertMessage的except变为1
                alertMessage.setExcept(1);
            }
            fireMoment += relay * 60 * 1000;
            alertMessage.setLeftTimes(leftTimes);
            alertMessage.setFireMoment(fireMoment);
            alertMessageRepository.save(alertMessage);
        }
    }
}
