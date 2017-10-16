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
public class onCreateAlert implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onCreateAlert.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String dateTime = "";
        String userId = "";
        String thing = "";
        String creatorName = "";
        String url = "";
        try {
            dateTime = dataJson.getString("dateTime");
            userId = dataJson.getString("userId");
            thing = dataJson.getString("thing");
            creatorName = dataJson.getString("creatorName");
            url = dataJson.getString("url");
        }catch (JSONException e){
            throw new EventException("403");
        }
        String first = "您收到一个来自【"+creatorName+"】的提醒";
        String remark = "点击进行查看";
        String keyword1 = thing;
        Map<String,String> keyWords = new HashMap<String,String>();
        keyWords.put("keyword1",keyword1);
        keyWords.put("keyword2",dateTime);
        SendWeiXin.sendWeiXin(event,userId,url,first,keyWords,remark);
        return "";
    }
}
