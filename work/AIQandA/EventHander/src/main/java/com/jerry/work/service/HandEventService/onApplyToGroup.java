package com.jerry.work.service.HandEventService;

import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.TmpMapper;
import com.jerry.work.util.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onApplyToGroup implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onApplyToGroup.class);

    private TmpMapper tmpMapper;

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String issueId = "";
        String targetId = "";
        String groupId = "";
        String url = "";
        String reason = "";
        try {
            issueId = dataJson.getString("issueId");
            targetId = dataJson.getString("targetId");
            groupId = dataJson.getString("groupId");
        }catch (JSONException e){
            throw new EventException("403");
        }
        boolean isDoctor = IsDoctor.isDoctor(targetId);
        if(isDoctor){
            url = GetUrl.getUrl("dlianxiren");
        }else {
            url = GetUrl.getUrl("plianxiren");
        }
        /**
         * 获取申请人信息
         */
        String userMessage = GetMessageUtil.getUserMessageByUserId(issueId);
        JSONObject userMessageJson = JSONObject.fromObject(userMessage);
        JSONObject userInfoJson = userMessageJson.getJSONObject("info");
        JSONObject userInfoJsonObj = userInfoJson.getJSONObject("info");
        String m_renming = userInfoJsonObj.getString("m_renming");
        /**
         * 获取群信息
         */
        String groupMessage = GetMessageUtil.getGroupMessageByGroupId(groupId);
        JSONObject groupMessageJson = JSONObject.fromObject(groupMessage);
        JSONObject groupInfoJson = groupMessageJson.getJSONObject("info");
        JSONObject groupInfoJsonObj = groupInfoJson.getJSONObject("info");
        String groupname = groupInfoJsonObj.getString("groupname");
        String type = groupInfoJsonObj.getString("type");
        String gid = groupInfoJsonObj.getString("gid");
        String isQun = "";
        if("2".equals(type)){
            isQun = "团队";
        }else if("1".equals(type)){
            isQun = "群";
        }
        try {
            reason = dataJson.getString("reason");
        }catch (JSONException e){
            reason = "申请加入“"+groupname +"”"+ isQun;
        }
        if("".equals(reason) || "null".equals(reason)){
            reason = "申请加入“"+groupname +"”"+ isQun;
        }
        Map<String,String> keyWords = new HashMap<String,String>();
        String nowStr = GetNowStr.getHanZiNowDateStr();
        keyWords.put("keyword1",m_renming);
        keyWords.put("keyword2", reason);
        keyWords.put("keyword3",nowStr);
        SendResult.sendWord("888888888", targetId,m_renming+"申请加入“"+groupname +"”"+isQun, "friend",System.currentTimeMillis());
        SendWeiXin.sendWeiXin(event,targetId,url, URLEncoder.encode("您有一条加"+isQun+"申请消息","utf-8"),keyWords,URLEncoder.encode("点击进入处理","utf-8"));
        logger.info("向"+targetId+"发送模板消息");
        return "";
    }
}
