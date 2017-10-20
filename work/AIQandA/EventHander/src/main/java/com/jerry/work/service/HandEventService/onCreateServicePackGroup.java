package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.ServicePackAlert;
import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.mapper.ServicePackAlertMapper;
import com.jerry.work.mapper.ServicePackTaskMapper;
import com.jerry.work.util.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onCreateServicePackGroup implements EventHandleInterface{

    private ServicePackAlertMapper servicePackAlertMapper;

    private ServicePackTaskMapper servicePackTaskMapper;

    private Logger logger = Logger.getLogger(onCreateServicePackGroup.class);

    private String issuerId = "100000379";

    public String handEvent(String event,String trigger,String data) throws Exception{
        servicePackAlertMapper = getBean(ServicePackAlertMapper.class);
        servicePackTaskMapper = getBean(ServicePackTaskMapper.class);
        JSONObject dataJson = JSONObject.fromObject(data);
        String toId = dataJson.getString("toId");

        String groupMessage = GetMessageUtil.getGroupMessageByGroupId(toId);
        JSONObject groupMessageJson = JSONObject.fromObject(groupMessage);
        JSONObject info = groupMessageJson.getJSONObject("info");
        JSONObject bc = info.getJSONObject("bc");
        JSONObject infoObj = info.getJSONObject("info");
        String creator = infoObj.getString("creator");
        String dd_uren = bc.getString("dd_uren");

        String servicePackName = dataJson.getString("servicePackName");
        String servicePackId = dataJson.getString("servicePackId");
        Map<String,String> param = new HashMap<String,String>();
        param.put("event",event);
        param.put("servicePackName",servicePackName);
        param.put("servicePackId",servicePackId);
        List<ServicePackAlert> servicePackAlerts = servicePackAlertMapper.findServicePackAlertByEventAndServicePackId(param);
        ServicePackAlert alert = servicePackAlertMapper.findById(1);
        if(alert.getContent().contains("#{userName}")){
            alert.setContent(alert.getContent().replace("#{userName}",""));
        }
        SendResult.sendWord("888888888", toId, alert.getContent(), "group",System.currentTimeMillis());
        logger.info("向群"+toId+"发送消息"+alert.getContent());
        for (ServicePackAlert servicePackAlert : servicePackAlerts) {
            String content = servicePackAlert.getContent();
            if(content.contains("#{userName}")){
                content = content.replace("#{userName}","");
            }
            SendResult.sendWord("888888888", toId, content, "group",System.currentTimeMillis());
            logger.info("向群"+toId+"发送消息"+content);
        }
        logger.info("群id:"+toId+"医生id:"+creator+"患者id:"+dd_uren);
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1", "创建群");
        keyWords.put("keyword2","创建群");
        SendWeiXin.sendWeiXin(event,issuerId,"www.baidu.com","创建群",keyWords,"创建群");
        onIssueCase onIssueCase = new onIssueCase();
        String url = "http://jishu.china-healthcare.cn/m.php/GuideMedicine/index?script=PersonalInformationCollectionForm-ProgrammeCustomization.xml&askId=id001&value=%E5%BC%80%E5%A7%8B%E8%AF%84%E4%BC%B0&title=%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF%E9%87%87%E9%9B%86";
        long now = System.currentTimeMillis();
        String onIssueCaseData = "{\"userId\":\""+dd_uren+"\"," +
                "\"type\":\"1\"," +
                "\"groupId\":\""+toId+"\"," +
                "\"url\":\""+url+"\"," +
                "\"servicePackId\":\""+servicePackId+"\"," +
                "\"issuerId\":\""+issuerId+"\"," +
                "\"issueTime\":\""+now +"\"," +
                "\"caseName\":\"个人信息表\"}";
        onIssueCase.handEvent("onIssueCase",trigger,onIssueCaseData);
        url += "&userId=" + dd_uren + "&servicePackId=" + servicePackId + "&issueTime=" + now + "&issuerId=" + issuerId + "&groupId=" + toId;
        SendResult.sendWord("888888888", toId, "请您尽快完善<a href = '"+url+"'>个人信息表</a>，以便医生提供全方位指导。", "group",System.currentTimeMillis());
        logger.info("向群"+toId+"发送消息:"+"请您尽快完善<a href = '"+url+"'>个人信息表</a>，以便医生提供全方位指导。");
        boolean sendFlag = true;
        try {
            String userId = dataJson.getString("userId");
        }catch (JSONException e){
            sendFlag = false;
        }
        if(sendFlag){

        }
        if("105".equals(servicePackId)){
            saveServicePackTask(dd_uren,servicePackId,toId,"生活能力评估","Lifestyle_diet-Exercise_Questionnaire","%E9%A5%AE%E9%A3%9F%E8%BF%90%E5%8A%A8%E9%97%AE%E5%8D%B7");
            saveServicePackTask(dd_uren,servicePackId,toId,"疾病康复评估","StrokeEvaluation_hand1","%e6%89%8b%ef%bc%88%e2%85%a0%e6%9c%9f%ef%bc%89");
            saveServicePackTask(dd_uren,servicePackId,toId,"运动康复指导","StrokeEvaluation_hand5a","%e6%89%8b%ef%bc%88%e2%85%a4%e6%9c%9f%ef%bc%89A");
            saveServicePackTask(dd_uren,servicePackId,toId,"饮食指导","ExerciseTemporaryScheme3","%E5%8D%88%E9%A4%90%E8%90%A5%E5%85%BB%E8%AF%BE%E5%A0%82");
            saveServicePackTask(dd_uren,servicePackId,toId,"健康状况评估","StrokeRiskScoreCard","%e5%8d%92%e4%b8%ad%e5%8d%b1%e9%99%a9%e8%af%84%e5%88%86%e5%8d%a1");
            saveServicePackTask(dd_uren,servicePackId,toId,"运动康复指导","StrokeEvaluation_hand5b","%e6%89%8b%ef%bc%88%e2%85%a4%e6%9c%9f%ef%bc%89B");
            saveServicePackTask(dd_uren,servicePackId,toId,"实用性评估","PracticalEvaluationForm","%E5%AE%9E%E7%94%A8%E6%80%A7%E8%AF%84%E4%BC%B0%E8%A1%A8");
        }
        return "";
    }

    public void saveServicePackTask(String userId,String servicePackId,String groupId,String caseName,String script,String tiltle){
        String url = "http://jishu.china-healthcare.cn/m.php/PictureDesc/index?script="+script+".xml&askId=id001&value=%E5%BC%80%E5%A7%8B%E8%AF%84%E4%BC%B0&title="+tiltle;
        String now = System.currentTimeMillis()+"";
        url += "&userId=" + userId + "&servicePackId=" + servicePackId  + "&issuerId=" + issuerId + "&groupId=" + groupId + "&issueTime=" + now;
        ServicePackTask servicePackTask = new ServicePackTask(groupId,userId,url,1,issuerId,""+now,servicePackId,caseName);
        servicePackTaskMapper.saveServicePackTask(servicePackTask);
    }

    public void operatingRelation(String trigger,String data) throws Exception{
        onOperatingRelation onOperatingRelation = new onOperatingRelation();
        onOperatingRelation.handEvent("onOperatingRelation",trigger,data);
    }
}
