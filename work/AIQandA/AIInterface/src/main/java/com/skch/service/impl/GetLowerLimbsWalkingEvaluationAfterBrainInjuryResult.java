package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetLowerLimbsWalkingEvaluationAfterBrainInjuryResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = 0;
        List<T_variable> t_variables = param.get("t_variables");
        String result = "根据回答，判断病人的下肢步行分级为"+t_variables.get(0).getParamvalue()+"级";
        return result;
    }
}
