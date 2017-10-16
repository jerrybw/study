package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.*;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetFuglMeyerBalanceFunctionEvaluationResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = getScoreByLetters(param,1);
        return "根据答案得到病人的得分为：" + score;
    }
}
