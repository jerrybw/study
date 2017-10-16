package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.Result;
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
public class onRemoveFromGroup implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onRemoveFromGroup.class);
    private final String resonSelf = "个人申请退出";
    private final String resonOther = "被管理员移除";

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String groupId = "";
        String userId = "";
        String userName = "";
        String dateTime = "";
        String reason = "";
        String url = "";
        try {
            dateTime = dataJson.getString("dateTime");
            groupId = dataJson.getString("groupId");
            userId = dataJson.getString("userId");
            reason = dataJson.getString("reason");
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
        userName = userInfoJsonObj.getString("m_renming");
        String isQun = "";
        if("2".equals(type)){
            isQun = "团队";
        }else if("1".equals(type)){
            isQun = "群";
        }
        String first = "";
        String remark = "";
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1",reason);
        keyWords.put("keyword2", dateTime);
        if(resonSelf.equals(reason)){
            first = userName + "退出了“"+groupname+"”"+isQun;
            JSONArray users = groupInfoJson.getJSONArray("user");
            int numbers = users.size();
            for (Object jsonObj:users){
                JSONObject object = JSONObject.fromObject(jsonObj);
                String user = "";
                try {
                    user = object.getString("m_gonghao");
                }catch (Exception e){
                    numbers--;
                    continue;
                }
                if(user == null || "".equals(user) || "null".equals(user)){
                    numbers--;
                }
            }
            remark = "当前成员数为"+numbers+"人，点击查看当前"+isQun+"详情";
            if("1".equals(type)){
                String creator = groupInfoJsonObj.getString("creator");
                if(IsDoctor.isDoctor(creator)){
                    url = GetUrl.getUrl("dqunLiaoYeMian");
                }else {
                    url = GetUrl.getUrl("pqunLiaoYeMian");
                }
                SendWeiXin.sendWeiXin(event,creator,url,first,keyWords,remark);
            }else if("2".equals(type)){
                String creator = groupInfoJsonObj.getString("creator");
                if(IsDoctor.isDoctor(creator)){
                    url = GetUrl.getUrl("dqunLiaoYeMian") + groupId;
                }else {
                    url = GetUrl.getUrl("pqunLiaoYeMian") + groupId;
                }
                SendWeiXin.sendWeiXin(event,creator,url,first,keyWords,remark);
//                String kemi = groupInfoJsonObj.getString("kemi");//等宣哥加参数
//                SendWeiXin.sendWeiXin(event,kemi,url,first,keyWords,remark);
            }
        }else {
            first = "您已退出“"+groupname+"”"+isQun;
            remark = "您已经移出“"+groupname+"”"+isQun;
            SendWeiXin.sendWeiXin(event,userId,url,first,keyWords,remark);
        }
        return "";
    }
}
