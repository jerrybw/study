package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.ServicePackAlert;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.ServicePackAlertMapper;
import com.jerry.work.util.ResultUtil;
import com.jerry.work.util.SendResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jerry.work.util.ResultUtil.*;
import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onCreateServicePackGroup implements EventHandleInterface{

    private ServicePackAlertMapper servicePackAlertMapper;

    private Logger logger = Logger.getLogger(onCreateServicePackGroup.class);

    public String handEvent(String event,String trigger,String data) throws Exception{
        JSONObject dataJson = JSONObject.fromObject(data);
        String toId = dataJson.getString("toId");
        String servicePackName = dataJson.getString("servicePackName");
        String servicePackId = dataJson.getString("servicePackId");
        boolean flag = true;
        servicePackAlertMapper = getBean(ServicePackAlertMapper.class);
        Map<String,String> param = new HashMap<String,String>();
        param.put("event",event);
        param.put("servicePackName",servicePackName);
        param.put("servicePackId",servicePackId);
        List<ServicePackAlert> servicePackAlerts = servicePackAlertMapper.findServicePackAlertByEventAndServicePackId(param);
        if(servicePackAlerts == null || servicePackAlerts.size() <= 0){
            throw new EventException("501");
        }
        for (ServicePackAlert servicePackAlert:servicePackAlerts) {
            String content = servicePackAlert.getContent();
            if(content.contains("#{userName}")){
                content.replace("#{userName}","jerry");
            }
            SendResult.sendWord("888888888",toId,content,"group");
        };
        return "";
    }
}
