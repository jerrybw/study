package com.jerry.work.service;

import com.jerry.work.bean.Task;
import com.jerry.work.bean.TaskSyllabus;
import com.jerry.work.mapper.TaskMapper;
import com.jerry.work.mapper.TaskSyllabusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Service
public class MrBaiSportJenny {

    @Autowired
    private TaskSyllabusMapper taskSyllabusMapper;

    @Autowired
    private TaskMapper taskMapper;

    private static Logger logger = Logger.getLogger(MrBaiSportJenny.class);

    public void run(String taskStatus,String start ,String JennyName){
        logger.info("MrBaiSportJenny 开始工作");
        Map<String,String> param = new HashMap<String,String>();
        param.put("taskStatus",taskStatus);
        param.put("start",start);
        param.put("jenny",JennyName);
        List<TaskSyllabus> listTaskSyllabus = taskSyllabusMapper.findByTaskStatusAndStartLessThanEqualAndJenny(param);
        for (TaskSyllabus taskSyllabus:listTaskSyllabus) {
            Integer taskId = taskSyllabus.getTaskId();
            Task task = taskMapper.findById(taskId);
            String handMethod = task.getHandMethod();
            String data = task.getParam();
            Class clazz = null;
            Object result = null;
            try {
                clazz = Class.forName("com.jerry.work.service.handService." + handMethod);
                Object instance = clazz.newInstance();
                Method getResult = clazz.getDeclaredMethod("hand", TaskSyllabus.class, String.class);
                getResult.setAccessible(true);
                result = getResult.invoke(instance, taskSyllabus,data);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MrBaiSportJenny工作发生错误"+e.fillInStackTrace());
            }
        }

        logger.info("MrBaiSportJenny 结束工作");
    }

}
