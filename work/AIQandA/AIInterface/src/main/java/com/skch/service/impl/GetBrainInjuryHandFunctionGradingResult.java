package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import static com.skch.util.CountScore.*;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetBrainInjuryHandFunctionGradingResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = getScore(param);
        String result = "";
        switch (score){
            case 0:
                result = "废用手：五个动作均不能完成";
                break;
            case 1:
                result = "辅助手C：五个动作能完成1个";
                break;
            case 2:
                result = "辅助手B：五个动作能完成2个";
                break;
            case 3:
                result = "辅助手A：五个动作能完成3个";
                break;
            case 4:
                result = "实用手B：五个动作能完成4个";
                break;
            case 5:
                result = "实用手A：五个动作均能完成";
                break;
        }
        return result;
    }
}
