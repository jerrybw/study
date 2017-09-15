package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.ServicePackTaskMapper;
import com.jerry.work.service.EventHandleService;
import com.jerry.work.util.SpringUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 向博文 on 2017/9/15.
 */
public class onIssueCase implements EventHandleInterface {

    @Override
    @Transactional
    public Object handEvent(String event, String trigger, String data) throws Exception {
        ServicePackTaskMapper servicePackTaskMapper = SpringUtil.getBean(ServicePackTaskMapper.class);
        String groupId = "";
        String userId = "";
        String url = "";
        String servicePackId = "";
        String issuerId = "";
        String issueTime = "";
        String caseName = "";
        int type = 0;
        try {
            JSONObject dataJson = JSONObject.fromObject(data);
            groupId = dataJson.getString("groupId");
            userId = dataJson.getString("userId");
            url = dataJson.getString("url");
            type = Integer.parseInt(dataJson.getString("type"));
            servicePackId = dataJson.getString("servicePackId");
            issuerId = dataJson.getString("issuerId");
            issueTime = dataJson.getString("issueTime");
            caseName = dataJson.getString("caseName");
        }catch (JSONException e){
            throw new EventException("403");
        }
        ServicePackTask servicePackTask = new ServicePackTask(groupId, userId, url, type, issuerId, issueTime, servicePackId, caseName);
        int i = servicePackTaskMapper.saveServicePackTask(servicePackTask);
        if (i != 1) {
            throw new EventException("401");
        }
        return null;
    }
}
