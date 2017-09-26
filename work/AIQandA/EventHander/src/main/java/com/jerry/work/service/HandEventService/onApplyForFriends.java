package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.Result;
import com.jerry.work.bean.Tmp;
import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.TmpMapper;
import com.jerry.work.util.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.format.datetime.DateFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

import static com.jerry.work.util.ResultUtil.*;
import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onApplyForFriends implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onApplyForFriends.class);

    private TmpMapper tmpMapper;

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String issueId = "";
        String targetId = "";
        String url = "";
        String reason = "";
        try {
            issueId = dataJson.getString("issueId");
            targetId = dataJson.getString("targetId");
            try {
                reason = dataJson.getString("reason");
            }catch (JSONException e){
                reason = "（申请人未说明）";
            }
        }catch (JSONException e){
            throw new EventException("403");
        }
        boolean isDoctor = IsDoctor.isDoctor(targetId);
        if(isDoctor){
            url = GetUrl.getUrl("dlianxiren");
        }else {
            url = GetUrl.getUrl("plianxiren");
        }

        /**
         * 获取申请人信息
         */
        String userMessage = GetMessageService.getUserMessageByUserId(issueId);
        JSONObject userMessageJson = JSONObject.fromObject(userMessage);
        JSONObject userInfoJson = userMessageJson.getJSONObject("info");
        JSONObject userInfoJsonObj = userInfoJson.getJSONObject("info");
        String m_renming = userInfoJsonObj.getString("m_renming");

        SendResult.sendWord("888888888", targetId,"您有一个加好友请求", "friend",System.currentTimeMillis());
        Map<String,String> keyWords = new HashMap<String,String>();
        String nowStr = GetNowStr.getNowStr();
        keyWords.put("keyword1",m_renming);
        keyWords.put("keyword2", reason);
        keyWords.put("keyword3",nowStr);
        SendWeiXin.sendWeiXin(event,targetId,url, URLEncoder.encode("您有一个加好友请求","utf-8"),keyWords,URLEncoder.encode("点此查看详情","utf-8"));
        logger.info("向"+targetId+"发送模板消息");
        return "";
    }
}
