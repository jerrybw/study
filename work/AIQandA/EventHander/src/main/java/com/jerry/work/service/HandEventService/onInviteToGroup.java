package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.Tmp;
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
public class onInviteToGroup implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onInviteToGroup.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String issueId = "";
        String targetId = "";
        String groupId = "";
        String url = "";
        String reason = "";
        try {
            try {
                reason = dataJson.getString("reason");
            }catch (JSONException e){
                reason = "（申请人未说明）";
            }
            issueId = dataJson.getString("issueId");
            targetId = dataJson.getString("targetId");
            groupId = dataJson.getString("groupId");
        }catch (JSONException e){
            throw new EventException("403");
        }

        /**
         * 获取申请人信息
         */
        String userMessage = GetMessageService.getUserMessageByUserId(targetId);
        JSONObject userMessageJson = JSONObject.fromObject(userMessage);
        JSONObject userInfoJson = userMessageJson.getJSONObject("info");
        JSONObject userInfoJsonObj = userInfoJson.getJSONObject("info");
        String m_renming = userInfoJsonObj.getString("m_renming");
        /**
         * 获取群信息
         */
        String groupMessage = GetMessageService.getGroupMessageByGroupId(groupId);
        JSONObject groupMessageJson = JSONObject.fromObject(groupMessage);
        JSONObject groupInfoJson = groupMessageJson.getJSONObject("info");
        JSONObject groupInfoJsonObj = groupInfoJson.getJSONObject("info");
        String groupname = groupInfoJsonObj.getString("groupname");
        String gid = groupInfoJsonObj.getString("gid");

        boolean isDoctor = IsDoctor.isDoctor(issueId);
        if(isDoctor){
            url = GetUrl.getUrl("dlianxiren");
        }else {
            url = GetUrl.getUrl("plianxiren");
        }

        Map<String,String> keyWords = new HashMap<String,String>();
        String nowStr = GetNowStr.getNowStr();
        keyWords.put("keyword1",m_renming);
        keyWords.put("keyword2", "邀请您加入：《"+groupname + "》群");
        keyWords.put("keyword3",nowStr);
        SendResult.sendWord("888888888", issueId,m_renming+"邀请您加入：《"+groupname+"》群", "friend",System.currentTimeMillis());
        SendWeiXin.sendWeiXin(event,issueId,url, URLEncoder.encode("您有一个新的群邀请申请","utf-8"),keyWords,URLEncoder.encode("点此查看详情","utf-8"));
        logger.info("向"+issueId+"发送模板消息");
        return "";
    }
}
