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
public class onDissolution implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onDissolution.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String groupId = "";
        String dateTime = "";
        String reason = "";
        String url = "";
        try {
            dateTime = dataJson.getString("dateTime");
            groupId = dataJson.getString("groupId");
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
        String isQun = "";
        String suffix = "";
        JSONArray users = groupInfoJson.getJSONArray("user");
        if("2".equals(type)){
            isQun = "团队";
            suffix = "团队负责人";
        }else if("1".equals(type)){
            isQun = "群";
            suffix = "群主";
        }
        try {
            reason = dataJson.getString("reason");
        }catch (JSONException e){
            reason = suffix+"解散"+isQun;
        }
        if("".equals(reason) || "null".equals(reason)){
            reason = suffix+"解散"+isQun;
        }
        String first = "您所在的"+isQun+"“"+groupname+"”已经解散";
        String remark = "“"+groupname+"” "+isQun+"已解散";
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1",reason);
        keyWords.put("keyword2", dateTime);
        for (Object jsonObj:users){
            JSONObject object = JSONObject.fromObject(jsonObj);
            String userId = "";
            try {
                userId = object.getString("m_gonghao");
            }catch (Exception e){
                continue;
            }
            if(userId != null && !"".equals(userId)){
                if(IsDoctor.isDoctor(userId)){
                    url = GetUrl.getUrl("dqunLiaoYeMian") + groupId;
                }else {
                    url = GetUrl.getUrl("pqunLiaoYeMian") + groupId;
                }
                SendWeiXin.sendWeiXin(event,userId,url,first,keyWords,remark);
            }
        }
        return "";
    }
}
