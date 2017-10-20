package com.jerry.work.service;

import com.jerry.work.bean.AlertTaskJenny;
import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.mapper.AlertTaskJennyMapper;
import com.jerry.work.mapper.ServicePackTaskMapper;
import com.jerry.work.util.GetUrl;
import com.jerry.work.util.SendWeiXin;
import com.jerry.work.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 向博文 on 2017/10/20.
 */
@Service
public class AlertTaskJennyService implements JennyInterface{

    @Autowired
    private ServicePackTaskMapper servicePackTaskMapper;

    @Autowired
    private AlertTaskJennyMapper alertTaskJennyMapper;

    private Logger logger = Logger.getLogger(AlertTaskJennyService.class);

    @Override
    public void run() throws Exception{
        logger.info("AlertTaskJenny 开始工作");
        long nowTimeStamp = System.currentTimeMillis();
        List<AlertTaskJenny> alertTaskJennyList = alertTaskJennyMapper.findAlertTaskByAlertTimestampLessThanAndAlertTimesLessThanShouldAlertTimes(nowTimeStamp);
        for(AlertTaskJenny alertTaskJenny:alertTaskJennyList){
            Integer taskId = alertTaskJenny.getTaskId();
            ServicePackTask servicePackTask = servicePackTaskMapper.findServicePackTaskById(taskId);
            Map<String,String> keyWords = new HashMap<String,String>();
            keyWords.put("keyword1", servicePackTask.getCaseName());
            keyWords.put("keyword2","暂无");
            DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(nowTimeStamp);
            String userId = servicePackTask.getUserId();
            String url = servicePackTask.getUrl();
            String servicePackId = servicePackTask.getServicePackId();
            String issueTime = servicePackTask.getIssueTime();
            String issuerId = servicePackTask.getIssuerId();
            String groupId = servicePackTask.getGroupId();
            if(url.contains("script=")) {
                url += "&userId=" + userId + "&servicePackId=" + servicePackId + "&issueTime=" + issueTime + "&issuerId=" + issuerId + "&groupId=" + groupId;
                url = url.replaceAll("&", "%26");
            }else if(url.contains("DetectionResult")){
                String[] split = url.split("=");
                url = GetUrl.getUrl("jianceurl") + "/" + issueTime + "/" + groupId + "/"+split[1];
                servicePackTask.setUrl(url);
            }
            String AlertTime = dateFormatter.print(date, Locale.getDefault());
            SendWeiXin.sendWeiXin("onIssueCase",userId,url,"您于"+AlertTime+"收到一个新的任务待完成",keyWords,"点此开始任务");
            logger.info("向"+userId+"发送模板消息提醒任务");
            alertTaskJenny.setAlertTimes(alertTaskJenny.getAlertTimes() + 1);
            alertTaskJennyMapper.update(alertTaskJenny);
        }
        logger.info("AlertTaskJenny 结束工作");
    }
}
