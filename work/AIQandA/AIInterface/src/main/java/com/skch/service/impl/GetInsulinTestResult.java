package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetInsulinTestResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = getScore(param);
        String result = "";
        if(score >= 0 && score <= 4){
            result = "你的胰岛素水平可能轻度失衡。“6周健康饮食疗法”：完成本书第三部分所列的6周计划。";
        } else if (score >= 5 && score <= 7){
            result = "你的胰岛素水平可能中度失衡。自我护理：完成本书第三部分所列的6周计划同时根据第15章所推荐的自我护理方法提高胰岛素水平。";
        } else if (score >= 8){
            result = "你的胰岛素水平可能重度失衡。医疗护理：完成以上两项任务的同时，可以向一名内科医生寻求额外的帮助。本书第15章罗列了一些你必须同这名内科医生讨论的问题。";
        }
        return result;
    }
}
