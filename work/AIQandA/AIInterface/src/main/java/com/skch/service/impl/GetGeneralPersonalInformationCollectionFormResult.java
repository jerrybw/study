package com.skch.service.impl;

import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetGeneralPersonalInformationCollectionFormResult implements GetResultInter
{
    @Override
    public String getResult(Map<String,List<T_variable>> param) {
        List<T_variable> t_variables = param.get("t_variables");
        String result = "";
        for (T_variable t:t_variables) {
            String paramkey = t.getParamkey();
            String paramvalue = t.getParamvalue();
            if("结束语".equals(paramkey)){
                continue;
            }
            result += paramkey + "：" + paramvalue + "<br>";
        }
        return result;
    }
}
