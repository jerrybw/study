package com.jerry.work.service;

import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.mapper.primary.ServicePackTaskMapper;
import com.jerry.work.util.GetMessageUtil;
import com.jerry.work.util.SendResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/21.
 */
@Service
public class SendResultAndChangeStatusService {

    @Autowired(required = false)
    private ServicePackTaskMapper servicePackTaskMapper;

    @Autowired
    private HandAfterUpdateScriptStatusService handAfterUpdateScriptStatusService;

    @Value("${jianceurl}")
    private String jianceurl;

    private Logger logger = Logger.getLogger(SendResultAndChangeStatusService.class);

    public String sendResultAndChangeStatus(String userId,String servicePackId,String issueTime,String issuerId,String groupId,String finishTime,String script){

        String code = "1";

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("groupId",groupId);
        map.put("issueTime",issueTime);
        map.put("userId",userId);

        ServicePackTask servicePackTask = servicePackTaskMapper.findServicePackTaskByGroupIdAndIssueTimeAndUserId(map);
        if(servicePackTask == null){
            code = "503";
        }else {
            servicePackTask.setStatus(1);
            servicePackTask.setFinishTime(finishTime);
            String caseName = servicePackTask.getCaseName();
            String url = servicePackTask.getUrl();
            if(url.contains("script=")) {
                url += "&userId=" + userId + "&servicePackId=" + servicePackId + "&issueTime=" + issueTime + "&issuerId=" + issuerId + "&groupId=" + groupId;

            }else if(url.contains("DetectionResult")){
                String[] split = url.split("=");
                url = jianceurl + "/" + issueTime + "/" + groupId + "/"+split[1];
            }
            String userMessage = GetMessageUtil.getUserMessageByUserId(userId);
            JSONObject userMessageJson = JSONObject.fromObject(userMessage);
            JSONObject userInfoJson = userMessageJson.getJSONObject("info");
            JSONObject userInfoJsonObj = userInfoJson.getJSONObject("info");
            String m_renming = userInfoJsonObj.getString("m_renming");

            int i = servicePackTaskMapper.updateServicePackTask(servicePackTask);
            if(i == 1){
                try {
                    SendResult.sendWord("888888888",groupId,m_renming+"完成了《"+caseName+"》,<a href='"+url+"'>点此查看结果</a>","group",System.currentTimeMillis());
                }catch (Exception e){
                    logger.info("im中向群中发送消息失败");
                    logger.error(e.fillInStackTrace());
                }
            }else {
                code = "504";
            }
        }
        return code;
    }
}
