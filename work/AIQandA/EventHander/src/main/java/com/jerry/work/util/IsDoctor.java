package com.jerry.work.util;

import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import com.jerry.work.service.HandEventService.GetMessageService;
import net.sf.json.JSONObject;

/**
 * Created by 向博文 on 2017/9/20.
 */
public class IsDoctor {

    /**
     * 判断用户是否是医生
     * false代表不是，true代表是
     * @param userId
     * @return
     */
    public static boolean isDoctor(String userId){
        String userMessage = GetMessageService.getUserMessageByUserId(userId);
        JSONObject userMessageJson = JSONObject.fromObject(userMessage);
        JSONObject infoJson = userMessageJson.getJSONObject("info");
        JSONObject infoJsonObj = infoJson.getJSONObject("info");
        String m_weixin = "";
        m_weixin = infoJsonObj.getString("m_weixin");
        if(m_weixin == null || "".equals(m_weixin) ||"null".equalsIgnoreCase(m_weixin)){
            return false;
        }
        return true;
    }
}
