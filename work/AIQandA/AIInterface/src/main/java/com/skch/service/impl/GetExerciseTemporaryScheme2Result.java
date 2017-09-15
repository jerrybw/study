package com.skch.service.impl;

import com.skch.dao.TaskSyllabusMapper;
import com.skch.entity.T_variable;
import com.skch.service.GetResultInter;
import com.skch.util.SendResult;
import com.skch.util.SendWeiXin;
import com.skch.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.format.datetime.DateFormatter;

import java.util.*;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetExerciseTemporaryScheme2Result implements GetResultInter
{

    private Logger logger = Logger.getLogger(GetExerciseTemporaryScheme2Result.class);


    private static TaskSyllabusMapper taskSyllabusMapper = SpringUtil.getBean(TaskSyllabusMapper.class);

    @Override
    public String getResult(Map<String,List<T_variable>> param) throws Exception{
        logger.info("收到锻炼二处理结果的请求");
        boolean saveDataFlag = true;
        List<T_variable> t_variables = param.get("t_variables");
        String userid = "";
        Map<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
        String now = dateFormatter.print(date, Locale.getDefault());
        Date toDay = dateFormatter.parse(now, Locale.getDefault());
        long tomorrow = toDay.getTime() + 1000 * 60 * 60 * 24;
        Date tomorrowDate = new Date(tomorrow);
        String tomorrowStr = dateFormatter.print(tomorrowDate, Locale.getDefault());
        map.put("jenny","白教练");
        map.put("start",tomorrowStr);
        String groupId = "";
        map.put("taskStatus","未开始");
        map.put("afterStatus", "已完成");
        map.put("jenny", "白教练");
        for (T_variable t_variable : t_variables) {
            if (userid == null || userid == "") {
                userid = t_variable.getUserid();
                map.put("userId", userid);
                groupId = taskSyllabusMapper.getGroupIdByUserIdAndJenny(map);
            }
            if (t_variable.getParamkey().equals("起始锻炼") && t_variable.getParamvalue().equals("不锻炼了")) {
                saveDataFlag = false;
            }
            if (t_variable.getParamkey().equals("锻炼提醒") && t_variable.getParamvalue().equals("不用了")) {
                taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEquals(map);
            }
        }
        map.put("taskId",1);
        map.put("start",tomorrowStr);
        taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
        map.put("taskId",2);
        taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
        logger.info(tomorrowStr);
        logger.info(userid);
        if(saveDataFlag){
            map.put("taskId",3);
            taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
            Date date1 = new Date();
            DateFormatter dateFormatter1 = new DateFormatter("yyyy-MM-dd HH:mm:ss");
            String now1 = dateFormatter1.print(date1, Locale.getDefault());
            String url = "http://d.china-healthcare.cn/Wx/sendtmpmsg";
            String remark = "点击提交数据";
            String clickUrl = "http://jishu.china-healthcare.cn/m.php/GuideMedicine/index?script=ExerciseProgramDataCollection.xml&askId=id001&value=%E5%BC%80%E5%A7%8B%E8%AF%84%E4%BC%B0&title=%E6%A0%87%E9%A2%98&userId="+userid;
            clickUrl = clickUrl.replaceAll("&", "%26");
            SendWeiXin.sendWeiXin(url,userid,clickUrl,"提交数据","提交今天的运动数据吧",now1,remark);
            SendResult.sendWord("888888888",groupId,userid+"完成了方案二的锻炼","group");
        }
        return "";
    }


}
