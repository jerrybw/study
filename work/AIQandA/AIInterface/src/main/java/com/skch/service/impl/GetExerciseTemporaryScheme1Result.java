package com.skch.service.impl;

import com.skch.dao.TaskSyllabusMapper;
import com.skch.entity.T_variable;
import com.skch.entity.TaskSyllabus;
import com.skch.service.GetResultInter;
import com.skch.util.SendWeiXin;
import com.skch.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.format.datetime.DateFormatter;
import com.skch.util.SendResult;

import java.text.ParseException;
import java.util.*;

import static com.skch.util.CountScore.getScore;

/**
 * Created by 向博文 on 2017/8/1.
 */
public class GetExerciseTemporaryScheme1Result implements GetResultInter
{
    private Logger logger = Logger.getLogger(GetExerciseTemporaryScheme1Result.class);

    @Override
    public String getResult(Map<String,List<T_variable>> param) throws Exception{
        logger.info("收到锻炼一处理结果的请求");
        TaskSyllabusMapper taskSyllabusMapper = SpringUtil.getBean(TaskSyllabusMapper.class);
        List<T_variable> t_variables = param.get("t_variables");
        boolean sendPlant2 = true;
        boolean neverAlertFlag = false;
        String userid = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jenny", "白教练");
        map.put("taskStatus", "未开始");
        map.put("afterStatus", "已完成");
        Date date = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
        String now = dateFormatter.print(date, Locale.getDefault());
        Date toDay = dateFormatter.parse(now, Locale.getDefault());
        long tomorrow = toDay.getTime() + 1000 * 60 * 60 * 24;
        Date tomorrowDate = new Date(tomorrow);
        String tomorrowStr = dateFormatter.print(tomorrowDate, Locale.getDefault());
        map.put("start",tomorrowStr);
        String groupId = "";
        for (T_variable t_variable : t_variables) {
            if(userid == null || userid == "") {
                userid = t_variable.getUserid();
                map.put("userId", userid);
                groupId = taskSyllabusMapper.getGroupIdByUserIdAndJenny(map);
            }
            if (t_variable.getParamkey().equals("锻炼提醒") && t_variable.getParamvalue().equals("不用了")) {
                sendPlant2 = false;
                SendResult.sendWord("888888888",groupId,userid+"上午未执行运动方案","group");
                neverAlertFlag = true;
            }
            if(t_variable.getParamkey().equals("锻炼提醒") && t_variable.getParamvalue().equals("提醒")){
                sendPlant2 = false;
            }
            if ("空腹有氧".equals(t_variable.getParamkey()) && "先做室内的".equals(t_variable.getParamvalue())){
                map.put("taskId", 1);
                taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
                map.put("taskId", 2);
                taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
                map.put("taskId", 3);
                taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
                sendPlant2=false;
                Date date1 = new Date();
                DateFormatter dateFormatter1 = new DateFormatter("yyyy-MM-dd HH:mm:ss");
                String now1 = dateFormatter1.print(date, Locale.getDefault());
                String url = "http://d.china-healthcare.cn/Wx/sendtmpmsg";
                String remark = "点击提交数据";
                String clickUrl = "http://jishu.china-healthcare.cn/m.php/GuideMedicine/index?script=ExerciseProgramDataCollection.xml&askId=id001&value=%E5%BC%80%E5%A7%8B%E8%AF%84%E4%BC%B0&title=%E6%A0%87%E9%A2%98&userId="+userid;
                clickUrl = clickUrl.replaceAll("&", "%26");
                SendWeiXin.sendWeiXin(url,userid,clickUrl,"提交数据","提交今天的运动数据吧",now1,remark);
                SendResult.sendWord("888888888",groupId,userid+"完成了方案二的锻炼","group");
            }
        }
        if(neverAlertFlag){
            taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEquals(map);
        }
        if(sendPlant2) {
            map.put("taskId", 1);
            taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
            map.put("taskId", 2);
            taskSyllabusMapper.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
            Date date1 = new Date();
            DateFormatter dateFormatter1 = new DateFormatter("yyyy-MM-dd HH:mm:ss");
            String now1 = dateFormatter1.print(date1, Locale.getDefault());
            String url = "http://d.china-healthcare.cn/Wx/sendtmpmsg";
            String remark = "点击开始锻炼方案2";
            String clickUrl = "http://jishu.china-healthcare.cn/m.php/GuideMedicine/index?script=ExerciseTemporaryScheme2.xml&askId=id001&value=%E5%BC%80%E5%A7%8B%E8%AF%84%E4%BC%B0&title=%E6%A0%87%E9%A2%98&userId=" + userid;
            clickUrl = clickUrl.replaceAll("&", "%26");
            SendWeiXin.sendWeiXin(url, userid, clickUrl, "开始锻炼方案2", "开始第二套锻炼方案吧", now1, remark);
            SendResult.sendWord("888888888",groupId,userid+"完成了方案一的锻炼","group");
        }
        return "";
    }
}
