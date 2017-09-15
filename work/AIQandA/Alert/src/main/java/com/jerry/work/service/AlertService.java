package com.jerry.work.service;

import com.jerry.work.bean.primary.AlertMessage;
import com.jerry.work.bean.secondary.AlertMsg;
import com.jerry.work.dao.primary.AlertMessageRepository;
import com.jerry.work.dao.secondary.AlertMsgRepository;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by 向博文 on 2017/8/18.
 */
@Service
public class AlertService {

    @Autowired
    private AlertMessageRepository alertMessageRepository;

    @Autowired
    private AlertMsgRepository alertMsgRepository;

    private Logger logger = Logger.getLogger(AlertService.class);

    public void alert(){
        Date date = new Date();
        long time = date.getTime();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        String now = dateFormatter.print(date, Locale.getDefault());
        ClassLoader classLoader = AlertService.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("url.properties");
        Properties urlProperties = new Properties();
        String jiayimuban = "";
        String jiaochaxuekemuban = "";
        try {
            urlProperties.load(in);
            jiayimuban = urlProperties.getProperty("jiayimuban");
            jiaochaxuekemuban = urlProperties.getProperty("jiaochaxuekemuban");
        } catch (IOException e) {
            logger.error("获取模板消息url失败");
            return;
        }

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
                String url = jiaochaxuekemuban;
                String clickUrl = "www.baidu.com";
                SendWeiXin.sendWeiXin(url,toId,clickUrl,"提醒",keyword1,"","");
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
        List<AlertMsg> alertMsgs = alertMsgRepository.findByYyStartLessThanEqualAndYyTypeAndValidOrYyRemindLessThanEqual("1", "2", now, now);//
        for (AlertMsg alertMsg: alertMsgs) {
            Integer id = alertMsg.getId();
            String toId = alertMsg.getYyUid();
            String keyWord2 = alertMsg.getYyStart();
            String keyWord1 = alertMsg.getYyContent();
            String msg = keyWord2 + keyWord1;
            if(msg != null&&!"".equals(msg)) {
                logger.info("php数据库中 向"+toId+"发ims提醒"+msg);
                SendResult.appadd("888888888", toId, msg, "friend");
                String url = jiayimuban;
                String first = "您有一个新的提醒已到时";
                String remark = "点击进行查看";
                String clickUrl = "http://d.china-healthcare.cn/Appointment/appointmentRemind/appointment_id/"+id+"/uid/"+toId;
                SendWeiXin.sendWeiXin(url,toId,clickUrl,first,keyWord1,keyWord2,remark);
            }
            alertMsg.setValid("0");
            alertMsgRepository.save(alertMsg);
        }
    }
}
