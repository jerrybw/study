package com.skch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skch.dao.FormItemMapper;
import com.skch.dao.MongoDBResultRepository;
import com.skch.dao.T_resultRepository;
import com.skch.entity.MongoDBResult;
import com.skch.entity.T_form_item;
import com.skch.entity.T_result;

import net.sf.json.JSONObject;

/**
 * Created by 向博文 on 2017/9/21.
 */
@RestController
public class GetDetailResult {

    @Autowired
    private T_resultRepository t_resultRepository;

    @Autowired
    private MongoDBResultRepository mongoDBResultRepository;

    @Autowired
    private FormItemMapper formItemMapper;

    @PostMapping("/getDetailResult")
    public String getDetailResult(String resultId){
        int id = Integer.parseInt(resultId);
        T_result result = null;
        String resultStr = "";
        result = t_resultRepository.getOne(id);
        MongoDBResult mongoDBResult = mongoDBResultRepository.findOne(result.getMongoDB());
        Map<String, Object> items = mongoDBResult.getItems();
        Set<String> keys = items.keySet();
        List<T_form_item> t_form_items = formItemMapper.findByFormName(result.getFormName());
        Map<String, String> resultMap = new HashMap<String, String>();
        for (T_form_item t : t_form_items) {
            if (!keys.contains(t.getVariable())) {
                continue;
            }
            String ask = t.getAsk();
            String answer = items.get(t.getVariable()).toString();
            answer = answer.replaceAll("<span>","");
            answer = answer.replaceAll("</span>","");
            resultMap.put(ask, answer);
        }
        JSONObject object = JSONObject.fromObject(resultMap);
        resultStr = object.toString();
        return resultStr;
    }
}
