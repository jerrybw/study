package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.ServicePackTaskMapper;
import com.jerry.work.service.EventHandleService;
import com.jerry.work.util.SendWeiXin;
import com.jerry.work.util.SpringUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/15.
 */
public class onIssueCase implements EventHandleInterface {

    @Override
    @Transactional
    public String handEvent(String event, String trigger, String data) throws Exception {
        ServicePackTaskMapper servicePackTaskMapper = SpringUtil.getBean(ServicePackTaskMapper.class);
        String groupId = "";
        String userId = "";
        String url = "";
        String servicePackId = "";
        String issuerId = "";
        String issueTime = "";
        String caseName = "";
        int type = 0;
        try {
            JSONObject dataJson = JSONObject.fromObject(data);
            groupId = dataJson.getString("groupId");
            userId = dataJson.getString("userId");
            url = dataJson.getString("url");
            type = Integer.parseInt(dataJson.getString("type"));
            servicePackId = dataJson.getString("servicePackId");
            issuerId = dataJson.getString("issuerId");
            issueTime = dataJson.getString("issueTime");
            caseName = dataJson.getString("caseName");
        }catch (JSONException e){
            throw new EventException("403");
        }
        ServicePackTask servicePackTask = new ServicePackTask(groupId, userId, url, type, issuerId, issueTime, servicePackId, caseName);
        int i = servicePackTaskMapper.saveServicePackTask(servicePackTask);
        if (i != 1) {
            throw new EventException("401");
        }
        url += "&userId=" + userId + "&servicePackId=" + servicePackId + "&issueTime=" + issueTime + "&issuerId=" + issuerId + "&groupId=" + groupId;
        url = url.replaceAll("&", "%26");
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1", caseName);
        keyWords.put("keyword2","（此消息不涉及）");
        long l = Long.parseLong(issueTime);
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(l);
        issueTime = dateFormatter.print(date, Locale.getDefault());
        SendWeiXin.sendWeiXin(event,userId,url,"您于"+issueTime+"收到一个新的任务待完成",keyWords,"点此开始任务");
        return "";
    }
}
