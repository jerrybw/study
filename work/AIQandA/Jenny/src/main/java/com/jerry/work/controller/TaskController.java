package com.jerry.work.controller;

import com.jerry.work.bean.Task;
import com.jerry.work.mapper.TaskMapper;
import com.jerry.work.mapper.TaskSyllabusMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/26.
 */
@Controller
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskSyllabusMapper taskSyllabusMapper;

    @ResponseBody
    @RequestMapping(value = "/tasks" ,method = RequestMethod.GET)
    public Object getAllTasks(){
        List<Task> allTask = taskMapper.findAllTask();
        JSONArray object = JSONArray.fromObject(allTask);
        return object.toString();
    }

    @RequestMapping(value = "/task",method = RequestMethod.POST)
    public String saveTask(Task task){
        int i = 0;
        if(task.getId() == null){
            i = taskMapper.saveTask(task);
        }else {
            i = taskMapper.updateTaskById(task);
        }
        return "redirect:taskList.html";
    }

    @ResponseBody
    @RequestMapping(value = "/task/{id}" ,method = RequestMethod.GET)
    public Object getTask(@PathVariable String id){
        Integer taskId = Integer.parseInt(id);
        Task task = taskMapper.findById(taskId);
        return task;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTask/{id}",method = RequestMethod.GET)
    public Object deleteTask(@PathVariable String id){
        Integer taskId = Integer.parseInt(id);
        int i = taskMapper.deleteTaskById(taskId);
        int tasks = taskSyllabusMapper.updateStatusByTaskId(taskId);
        Map<String,String> map = new HashMap<String,String>();
        map.put("success","success");
        if(i != 1){
            map.put("success","failed");
        }
        return map;
    }
}
