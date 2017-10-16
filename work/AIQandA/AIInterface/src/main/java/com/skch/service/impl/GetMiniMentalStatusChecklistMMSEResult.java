package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;
import com.skch.util.CountScore;

import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.*;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetMiniMentalStatusChecklistMMSEResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        Integer score = 0;
        String result = "";
        List<T_variable> t_variables = param.get("t_variables");
        for (T_variable t:t_variables) {
            if("对".equals(t.getParamvalue())){
                score++;
            }else if ("错".equals(t.getParamvalue())){

            }else {
                score += Integer.parseInt(t.getParamvalue());
            }
        }
        String status = "";
        if(score <= 17){
            status = "文盲";
        } else if (score <= 20){
            status = "小学";
        } else if (score <= 22){
            status = "中学(包括中专)";
        } else {
            status = "大学(包括大专)";
        }
        result = "根据答案判断，病人的精神状态处于"+status + "水平";
        return result;
    }
}
