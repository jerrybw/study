package com.jerry.work.service.HandEventService;

import com.jerry.work.util.SendResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import static com.jerry.work.util.ResultUtil.*;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onApplyForFriends implements EventHandleInterface{
    private Logger logger = Logger.getLogger(onApplyForFriends.class);

    public String handEvent(String event,String trigger,String data) {
        JSONObject dataJson = JSONObject.fromObject(data);
        boolean flag = true;
        String toId = dataJson.getString("toId");
        String fromId = dataJson.getString("fromId");
        String url = dataJson.getString("url");
        String msg = dataJson.getString("msg");
        try {
            SendResult.sendWord("888888888", toId, "", "group");
        } catch (Exception e){
            flag = false;
            logger.error(e.fillInStackTrace());
        }
        String result = "";
        if(flag){
            result  = handResult("1");
        }else {
            result  = handResult("0");
        }
        return result;
    }
}
