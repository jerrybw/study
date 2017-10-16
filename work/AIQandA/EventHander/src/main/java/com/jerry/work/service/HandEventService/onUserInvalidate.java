package com.jerry.work.service.HandEventService;

import com.jerry.work.excrption.EventException;
import com.jerry.work.util.GetUrl;
import com.jerry.work.util.SendWeiXin;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onUserInvalidate implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onUserInvalidate.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String dateTime = "";
        String userId = "";
        String reason = "";
        String first = "您的资料未通过审核";
        String remark = "点击重新提交资料";
        String keyword1 = "审核未通过";
        String userInfoPage = GetUrl.getUrl("userInfoPage");
        try {
            dateTime = dataJson.getString("dateTime");
            userId = dataJson.getString("userId");
            try {
                reason = dataJson.getString("reason");
            }catch (JSONException e){
                reason = "由于您提交的信息不完整，无法确认您的真实身份";
            }
            if("".equals(reason) || "null".equals(reason)){
                reason = "由于您提交的信息不完整，无法确认您的真实身份";
            }
        }catch (JSONException e){
            throw new EventException("403");
        }
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1",keyword1);
        keyWords.put("keyword2", dateTime);
        keyWords.put("keyword3", reason);
        SendWeiXin.sendWeiXin(event,userId,userInfoPage,first,keyWords,remark);
        return "";
    }
}
