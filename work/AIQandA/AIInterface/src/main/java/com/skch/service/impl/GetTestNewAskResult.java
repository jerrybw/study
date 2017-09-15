package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class GetTestNewAskResult implements GetResultInter{

    @Override
    public String getResult(Map<String, List<T_variable>> param) {
        Integer score = getScore(param);
        String result = "患者前臂肌肉没有问题。";
        if(score == 1){
            result = "患者前臂肌肉有问题";
        }
        return result;
    }
}
