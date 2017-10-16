package com.skch.service.impl;

import com.skch.dao.TaskSyllabusMapper;
import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;
import com.skch.util.SendResult;
import com.skch.util.SpringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetExerciseProgramDataCollectionResult implements GetResultInter
{

    private static TaskSyllabusMapper taskSyllabusMapper = SpringUtil.getBean(TaskSyllabusMapper.class);

    @Override
    public String getResult(Map<String,List<T_variable>> param) throws Exception{
        List<T_variable> t_variables = param.get("t_variables");
//        TaskSyllabusMapper taskSyllabusMapper = SpringUtil.getBean(TaskSyllabusMapper.class);
        String userid = "";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("jenny","白教练");
        String groupId = getGroupId(t_variables,userid,map);
        userid = map.get("userId").toString();
        SendResult.sendWord("888888888",groupId,userid+"完成了上午的锻炼，并上传了数据","group");
        return "数据采集完成";
    }

    public static String getGroupId(List<T_variable> t_variables,String userid,Map<String,Object> map){
        for (T_variable t_variable : t_variables) {
            if(userid == null || userid == ""){
                userid = t_variable.getUserid();
                map.put("userId",userid);
                return taskSyllabusMapper.getGroupIdByUserIdAndJenny(map);
            }
        }
        return "";
    }
}
