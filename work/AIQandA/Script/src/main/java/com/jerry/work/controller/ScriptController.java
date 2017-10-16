package com.jerry.work.controller;

import com.jerry.work.service.ScriptService;
import com.jerry.work.service.SendResultAndChangeStatusService;
import com.jerry.work.util.UpdateScriptStatusResultUtil;
import exception.ScriptException;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 向博文 on 2017/9/12.
 */
@RestController
public class ScriptController {

    @Autowired
    private ScriptService scriptService;//评估结果处理业务逻辑层

    @Autowired
    private SendResultAndChangeStatusService sendResultAndChangeStatusService;

    /**
     * 获取任务的完成状态
     * @param param 请求参数
     * @return
     */
    @PostMapping("/getScriptStatus")
    public String getScriptStatus(String param){
        return scriptService.getScriptStatus(param);
    }

    /**
     * 将评估结果关联到任务中并修改任务的完成状态
     * @param param
     * @return
     */
    @PostMapping("/updateScriptStatus")
    public String updateScriptStatus(String param){
        return scriptService.updateScriptStatus(param);
    }

    /**
     * 修改任务的完成状态
     * @param param
     * @return
     */
    @PostMapping("/updateTaskStatus")
    public String updateTaskStatus(String param){
        String userId = "";
        String servicePackId = "";
        String issueTime = "";
        String issuerId = "";
        String groupId = "";
        String finishTime = "";
        String code = "1";
        try {
            JSONObject paramJson = JSONObject.fromObject(param);
            userId = paramJson.getString("userId");
            servicePackId = paramJson.getString("servicePackId");
            issueTime = paramJson.getString("issueTime");
            issuerId = paramJson.getString("issuerId");
            groupId = paramJson.getString("groupId");
            finishTime = paramJson.getString("finishTime");
        } catch (JSONException e) {
            code = "502";
        }
        code = sendResultAndChangeStatusService.sendResultAndChangeStatus(userId,servicePackId,issueTime,issuerId,groupId,finishTime);
        return UpdateScriptStatusResultUtil.handResult(code,"");
    }

}
