package com.jerry.work.service.HandEventService;

import com.jerry.work.excrption.EventException;
import com.jerry.work.util.GetUrl;
import com.jerry.work.util.IsDoctor;
import com.jerry.work.util.SendWeiXin;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onUserValidate implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onUserValidate.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String dateTime = "";
        String userId = "";
        String reason = "";
        String first = "您的认证资料已通过审核";
        String remark = "欢迎加入家医助手";
        String keyword1 = "审核通过";
        String url = "";
        try {
            dateTime = dataJson.getString("dateTime");
            userId = dataJson.getString("userId");
            try {
                reason = dataJson.getString("reason");
            }catch (JSONException e){
                reason = "";
            }
        }catch (JSONException e){
            throw new EventException("403");
        }
        if(IsDoctor.isDoctor(userId)){
            url = GetUrl.getUrl("djiaYiShouYe");
        }else {
            url = GetUrl.getUrl("pjiaYiShouYe");
        }
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1",keyword1);
        keyWords.put("keyword2",dateTime);
        SendWeiXin.sendWeiXin(event,userId,url,first,keyWords,remark);
        return "";
    }
}
