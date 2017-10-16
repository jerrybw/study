package com.jerry.work.service.HandEventService;

import com.jerry.work.excrption.EventException;
import com.jerry.work.util.GetMessageUtil;
import com.jerry.work.util.GetUrl;
import com.jerry.work.util.IsDoctor;
import com.jerry.work.util.SendWeiXin;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onJoinInGroup implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onJoinInGroup.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String groupId = "";
        String userId = "";
        String validate = "";
        String dateTime = "";
        String reason = "";
        String url = "";
        try {
            dateTime = dataJson.getString("dateTime");
            groupId = dataJson.getString("groupId");
            userId = dataJson.getString("userId");
            reason = dataJson.getString("reason");
            validate = dataJson.getString("validate");
        }catch (JSONException e){
            throw new EventException("403");
        }
        /**
         * 获取群信息
         */
        String groupMessage = GetMessageUtil.getGroupMessageByGroupId(groupId);
        JSONObject groupMessageJson = JSONObject.fromObject(groupMessage);
        JSONObject groupInfoJson = groupMessageJson.getJSONObject("info");
        JSONObject groupInfoJsonObj = groupInfoJson.getJSONObject("info");
        String groupname = groupInfoJsonObj.getString("groupname");
        String type = groupInfoJsonObj.getString("type");
        /**
         * 获取申请人信息
         */
        String userMessage = GetMessageUtil.getUserMessageByUserId(userId);
        JSONObject userMessageJson = JSONObject.fromObject(userMessage);
        JSONObject userInfoJson = userMessageJson.getJSONObject("info");
        JSONObject userInfoJsonObj = userInfoJson.getJSONObject("info");
        String isQun = "";
        if("2".equals(type)){
            isQun = "团队";
        }else if("1".equals(type)){
            isQun = "群";
        }
        String first = "您申请加入“"+groupname+"”"+isQun+"请求已通过审核";
        String remark = "请点击进入“"+groupname+"”团队";
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1","审核通过");
        keyWords.put("keyword2", dateTime);
        if(IsDoctor.isDoctor(userId)){
            url = GetUrl.getUrl("dqunLiaoYeMian") + groupId;
        }else {
            url = GetUrl.getUrl("pqunLiaoYeMian") + groupId;
        }
        SendWeiXin.sendWeiXin(event,userId,url,first,keyWords,remark);
        return "";
    }
}
