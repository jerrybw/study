package com.jerry.work.service.handService;

import com.jerry.work.bean.TaskSyllabus;
import com.jerry.work.mapper.TaskSyllabusMapper;
import com.jerry.work.service.HandServiceInterface;
import com.jerry.work.util.SendResult;
import com.jerry.work.util.SendWeiXin;
import com.jerry.work.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/4.
 */
public class Start implements HandServiceInterface{


    private Logger logger = Logger.getLogger(Start.class);
    @Override
    @Transactional
    public Object hand(TaskSyllabus taskSyllabus, String param) throws Exception{
        String toId = taskSyllabus.getUserId();
        String start = taskSyllabus.getStart();
        String url = "http://d.china-healthcare.cn/Wx/sendtmpmsg";
        String remark = "点击开始锻炼";
        String clickUrl = "http://jishu.china-healthcare.cn/m.php/GuideMedicine/index?script=ExerciseTemporaryScheme1.xml&askId=id001&value=%E5%BC%80%E5%A7%8B%E8%AF%84%E4%BC%B0&title=%E6%A0%87%E9%A2%98&userId="+toId;
        clickUrl = clickUrl.replaceAll("&", "%26");
        SendWeiXin.sendWeiXin(url,toId,clickUrl,"开始锻炼",param,start,remark);
        TaskSyllabusMapper bean = SpringUtil.getBean(TaskSyllabusMapper.class);
        Date date = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        String now = dateFormatter.print(date, Locale.getDefault());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", toId);
        map.put("jenny", "白教练");
        map.put("taskStatus", "未开始");
        map.put("afterStatus", "已完成");
        map.put("start",now);
        map.put("taskId",1);
        bean.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
        String groupId = bean.getGroupIdByUserIdAndJenny(map);
        SendResult.sendWord("888888888",groupId,"jenny已经按照白教练的运动方案对"+toId+"进行了提醒","group");
        return null;
    }
}
