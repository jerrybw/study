package com.jerry.work.service;


import com.jerry.work.bean.MongoDBResult;
import com.jerry.work.bean.SchemeCondition;
import com.jerry.work.mapper.primary.SchemeConditionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/10/20.
 */
@Service
public class HandAfterUpdateScriptStatusService {

    @Autowired
    private SchemeConditionMapper schemeConditionMapper;

    public void hand(int servicePackId, String script, MongoDBResult mongoDBResult){
        Map<String, Object> items = mongoDBResult.getItems();
        for (Map.Entry<String,Object> entry:items.entrySet()) {
            String formItemName = entry.getKey();
            String formItemValue = entry.getValue().toString();
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("servicePackId",servicePackId);
            param.put("script",script);
            param.put("formItemName",formItemName);
            param.put("formItemValue",formItemValue);
            schemeConditionMapper.findSchemeConditionBySPIAndScriptAndFINAndFIV(param);
        }
    }
}
