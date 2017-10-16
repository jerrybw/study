package com.jerry.work.controller;

import com.jerry.work.bean.Task;
import com.jerry.work.bean.TaskSyllabus;
import com.jerry.work.mapper.TaskMapper;
import com.jerry.work.mapper.TaskSyllabusMapper;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 向博文 on 2017/9/26.
 */
@Controller
public class TaskSyllabusController {

    @Autowired
    private TaskSyllabusMapper taskSyllabusMapper;

    @ResponseBody
    @RequestMapping(value = "/getTaskSyllabusList",method = RequestMethod.GET)
    public Object getAllTaskSyllabus(){
        List<TaskSyllabus> allTaskSyllabus = taskSyllabusMapper.findAllTaskSyllabus();
        JSONArray object = JSONArray.fromObject(allTaskSyllabus);
        return object.toString();
    }

    @RequestMapping(value = "/taskSyllabus",method = RequestMethod.POST)
    public String saveTaskSyllabus(TaskSyllabus taskSyllabus){
        int i = 0;
        if(taskSyllabus.getId() == null){
            i = taskSyllabusMapper.saveTaskSyllabus(taskSyllabus);
        }else {
            i = taskSyllabusMapper.updateTaskSyllabus(taskSyllabus);
        }
        return "redirect:taskSyllabusList.html";
    }

    @ResponseBody
    @RequestMapping(value = "/taskSyllabus/{id}",method = RequestMethod.GET)
    public Object getTaskSyllabus(@PathVariable String id){
        Integer taskSyllabusId = Integer.parseInt(id);
        TaskSyllabus taskSyllabus = taskSyllabusMapper.findById(taskSyllabusId);
        return taskSyllabus;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTaskSyllabus/{id}",method = RequestMethod.GET)
    public Object deleteTaskSyllabus(@PathVariable String id){
        Integer taskId = Integer.parseInt(id);
        int i = taskSyllabusMapper.deleteTaskSyllabusById(taskId);
        return i;
    }
}
