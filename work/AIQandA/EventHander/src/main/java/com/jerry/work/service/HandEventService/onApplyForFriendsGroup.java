package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.Tmp;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.TmpMapper;
import com.jerry.work.util.IsDoctor;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import net.sf.ezmorph.array.BooleanObjectArrayMorpher;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.net.URLEncoder;

import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onApplyForFriendsGroup implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onApplyForFriendsGroup.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String groupId = "";
        String flag = "";
        boolean isSendForFriend = false;
        try {
            groupId = dataJson.getString("groupId");
            flag = dataJson.getString("flag");
        }catch (JSONException e){
            isSendForFriend = true;
        }
        if(isSendForFriend || groupId == null || "".equals(groupId) || flag == null || "".equals(flag) || "null".equalsIgnoreCase(groupId) || "null".equalsIgnoreCase(flag)){
            onApplyForFriends onApplyForFriends = new onApplyForFriends();
            onApplyForFriends.handEvent("onApplyForFriends",trigger,data);
        }else if("0".equals(flag)){
            onApplyToGroup onApplyToGroup = new onApplyToGroup();
            onApplyToGroup.handEvent("onApplyToGroup",trigger,data);
        }else {
            onInviteToGroup onInviteToGroup = new onInviteToGroup();
            onInviteToGroup.handEvent("onInviteToGroup",trigger,data);
        }
        return "";
    }
}
