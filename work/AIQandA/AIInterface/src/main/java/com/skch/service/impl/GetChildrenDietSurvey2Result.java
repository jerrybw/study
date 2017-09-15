package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetChildrenDietSurvey2Result implements GetResultInter
{
    @Override
    public String getResult(Map<String, List<T_variable>> param) {
        return "评估完成";
    }
}
