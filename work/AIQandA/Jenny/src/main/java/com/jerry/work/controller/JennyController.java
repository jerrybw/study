package com.jerry.work.controller;

import com.jerry.work.bean.Task;
import com.jerry.work.mapper.TaskMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 向博文 on 2017/9/26.
 */
@RestController
public class JennyController {

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/tasks")
    public Object getAllTasks(){
        List<Task> allTask = taskMapper.findAllTask();
        JSONArray object = JSONArray.fromObject(allTask);
        return object.toString();
    }
}
