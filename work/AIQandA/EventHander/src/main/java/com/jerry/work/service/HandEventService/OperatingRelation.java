package com.jerry.work.service.HandEventService;

import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import com.jerry.work.excrption.EventException;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import static com.jerry.work.util.PushData.pushData;

/**
 * Created by 向博文 on 2017/9/18.
 */
public class OperatingRelation {

    public static String operatingRelation(String event,String trigger,String method,String issueId,String targetId,String issueName,String issueRemark,String targetName,String targetRemark)throws Exception{
        if("onBecomeFriends".equals(event)){
            SendResult.sendWord("888888888", targetId,issueId + "同意了您的好友申请", "friend",System.currentTimeMillis());
//            SendWeiXin.sendWeiXin(JiaoChaOrJiaYi.JIA_YI,targetId,"javaScript:;","同意好友申请",issueId + "同意了您的好友申请","点此查看详情","同意好友申请");
        }
        if("".equals(issueRemark)){
            issueRemark = issueName;
        }
        if("".equals(targetRemark)){
            targetRemark = targetName;
        }
        String result = pushData(method,issueId,issueName,issueRemark,targetId,targetName,targetRemark);
        if(!"0".equals(result)){
            result = pushData(method,issueId,issueName,issueRemark,targetId,targetName,targetRemark);
        }
        if (!"0".equals(result)){
            throw new EventException("600");
        }
        return "";
    }
}
