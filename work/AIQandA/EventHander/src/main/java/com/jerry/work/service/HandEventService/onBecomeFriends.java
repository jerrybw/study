package com.jerry.work.service.HandEventService;

import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import com.jerry.work.excrption.EventException;
import com.jerry.work.util.HttpRequest;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

import static com.jerry.work.util.PushData.pushData;

/**
 * Created by 向博文 on 2017/9/18.
 */
public class onBecomeFriends implements EventHandleInterface{
    @Override
    public String handEvent(String event, String trigger, String data) throws Exception {
        JSONObject dataJson = JSONObject.fromObject(data);
        String issueId = "";
        String targetId = "";
        String issueName = "";
        String targetName = "";
        String issueRemark = "";
        String targetRemark = "";
        String method = "updateAddressList";
        try {
            issueId = dataJson.getString("issueId");
            targetId = dataJson.getString("targetId");
            issueName = dataJson.getString("issueName");
            targetName = dataJson.getString("targetName");
            issueRemark = dataJson.getString("issueRemark");
            targetRemark = dataJson.getString("targetRemark");
        }catch (JSONException e){
            throw new EventException("403");
        }
        return OperatingRelation.operatingRelation(event,trigger,method,issueId,targetId,issueName,issueRemark,targetName,targetRemark);
    }
}
