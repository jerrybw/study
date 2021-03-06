package com.jerry.work.controller;

import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.ServicePackTaskMapper;
import com.jerry.work.service.HandEventService.FindCasesService;
import com.jerry.work.util.ResultUtil;
import com.jerry.work.util.SpringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 向博文 on 2017/9/15.
 */
@RestController
public class FindCaseController {

    @Autowired
    private FindCasesService findCasesService;

    /**
     * 通过群id获取任务列表
     * @param groupId 群id
     * @return 任务列表
     * @throws Exception
     */
    @PostMapping("/findCases")
    public Object findCases(String groupId) throws Exception {
        String code = "1";
        List<ServicePackTask> servicePackTasks = findCasesService.findCases(groupId);
        if(servicePackTasks == null || servicePackTasks.size() <= 0){
            code = "402";
            return ResultUtil.handResult(code);
        }
        JSONArray object = null;
        try {
            object = JSONArray.fromObject(servicePackTasks);
        }catch (Exception e){
            code = "500";
            return ResultUtil.handResult(code);
        }
        return ResultUtil.handResultWithReturn(code,object.toString());
    }
}
