package com.jerry.work.service;

import com.jerry.work.bean.Task;
import com.jerry.work.bean.TaskSyllabus;

import java.util.Map;

/**
 * Created by 向博文 on 2017/9/4.
 */
public interface HandServiceInterface {

    public Object hand(Task task,TaskSyllabus taskSyllabus) throws Exception;
}
