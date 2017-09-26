package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.ServicePackAlert;
import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.ServicePackAlertMapper;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jerry.work.util.PushData.pushData;
import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onRemoveFriends implements EventHandleInterface{

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String userId = "";
        String removed = "";
        String userName = "";
        String removedName = "";
        String userRemark = "";
        String removedRemark = "";
        String method = "updateAddressList";
        try {
            userId = dataJson.getString("userId");
            removed = dataJson.getString("removed");
            userName = dataJson.getString("userName");
            removedName = dataJson.getString("removedName");
            userRemark = dataJson.getString("userRemark");
            removedRemark = dataJson.getString("removedRemark");
        }catch (JSONException e){
            throw new EventException("403");
        }
        return OperatingRelation.operatingRelation(event,trigger,"deleteAddressList",userId,removed,userName,userRemark,removedName,removedRemark);
    }
}
