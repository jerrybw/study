package com.jerry.work.service.handService;

import com.jerry.work.bean.Task;
import com.jerry.work.bean.TaskSyllabus;
import com.jerry.work.mapper.TaskSyllabusMapper;
import com.jerry.work.service.HandServiceInterface;
import com.jerry.work.util.IsDoctor;
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
public class SendMsg implements HandServiceInterface{


    private Logger logger = Logger.getLogger(SendMsg.class);

    private String issuerId = "100000379";

    private TaskSyllabusMapper taskSyllabusMapper;

    @Override
    @Transactional
    public Object hand(Task task,TaskSyllabus taskSyllabus) throws Exception{
        TaskSyllabusMapper bean = SpringUtil.getBean(TaskSyllabusMapper.class);
        String userId = taskSyllabus.getUserId();
        String groupId = taskSyllabus.getGroupId();
        String jenny = taskSyllabus.getJenny();
        Integer tmp = task.getTmp();
        Integer imToP = task.getImToP();
        Integer imToG = task.getImToG();
        String now = System.currentTimeMillis() + "";
        String url = "http://d.china-healthcare.cn/Wx/sendtmpmsg";
        if(tmp == 1){//tmp=1代表要发微信模板消息
            String keyWords = task.getKeyWords();
            if(keyWords == null||!keyWords.contains(";")){
                logger.error("任务id为"+task.getId()+"的任务发模板消息时，keywords格式不正确");
            }else {
                Map<String, String> map = new HashMap<String, String>();
                String[] splits = keyWords.split(";");
                for (int i =0;i<splits.length;i++) {
                    map.put("keyword"+(i+1), splits[i]);
                }
                boolean isD = IsDoctor.isDoctor(userId);
                if (!isD) {
                    url = "http://p.china-healthcare.cn/Wxp/sendtmpmsg";
                }
                String clickUrl = task.getUrl();
                clickUrl += "&userId=" + userId + "&servicePackId=" + task.getServicePackId() + "&issueTime=" + now + "&issuerId=" + issuerId + "&groupId=" + taskSyllabus.getGroupId();
                SendWeiXin.sendWeiXin(url, task.getTmpId(), userId, clickUrl, task.getFirst(), map, task.getRemark());
            }
        }
        if(imToP == 1){
            SendResult.sendWord("888888888",userId,task.getImToPMsg(),"friend",System.currentTimeMillis());
        }
        if(imToG == 1){
            SendResult.sendWord("888888888",groupId,task.getImToGMsg(),"group",System.currentTimeMillis());
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        map.put("taskStatus","未开始");
        map.put("jenny",jenny);
        map.put("afterStatus","已完成");
        map.put("taskId",task.getId());
        Date nowDay = new Date(System.currentTimeMillis() + 1000*60*60*24);
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
        String nowDayStr = dateFormatter.print(nowDay, Locale.getDefault());
        map.put("start",nowDayStr);
        bean.updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(map);
        return null;
    }
}
