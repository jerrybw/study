package com.jerry.work.service.HandEventService;

import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import com.jerry.work.excrption.EventException;
import com.jerry.work.util.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jerry.work.util.PushData.pushData;

/**
 * Created by 向博文 on 2017/9/18.
 */
public class onOperatingRelation implements EventHandleInterface {

    public String handEvent(String event,String trigger,String data)throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        map.put("0","addAddressList");
        map.put("1","updateAddressList");
        map.put("2","deleteAddressList");
        JSONObject dataJson = JSONObject.fromObject(data);
        String issueId = "";
        String targetId = "";
        String issueName = "";
        String targetName = "";
        String issueRemark = "";
        String targetRemark = "";
        String method = "";
        String relationType = "";
        try {
            method = dataJson.getString("method");
            issueId = dataJson.getString("issueId");
            targetId = dataJson.getString("targetId");
            issueName = dataJson.getString("issueName");
            targetName = dataJson.getString("targetName");
            issueRemark = dataJson.getString("issueRemark");
            targetRemark = dataJson.getString("targetRemark");
            try {
                relationType = dataJson.getString("relationType");
            }catch (JSONException e){

            }
        }catch (JSONException e){
            throw new EventException("403");
        }
        if("".equals(issueRemark)){
            issueRemark = issueName;
        }
        if("".equals(targetRemark)){
            targetRemark = targetName;
        }
        String result = pushData(map.get(method),issueId,issueName,issueRemark,targetId,targetName,targetRemark);
        if(!"0".equals(result)){
            result = pushData(map.get(method),issueId,issueName,issueRemark,targetId,targetName,targetRemark);
        }
        if (!"0".equals(result)){
            throw new EventException("600");
        }
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1","审核通过");
        keyWords.put("keyword2", GetNowStr.getHanZiNowDateTimeStr());
        if("0".equals(method)){
            String url = "";
            if(IsDoctor.isDoctor(issueId)){
                url = GetUrl.getUrl("dchatUrl") + targetId;
            }else {
                url = GetUrl.getUrl("pchatUrl") + targetId;
            }

            String first = "您添加联系人的申请已经通过，【"+targetRemark+"】同意添加您为联系人，现在你们可以聊天啦";
            String remark = "点击进入聊天";
            SendWeiXin.sendWeiXin("onBecomeFriend",issueId,url,first,keyWords,remark);
        }
        return "";
    }
}
