package com.jerry.work.service;

import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.mapper.ServicePackTaskMapper;
import com.jerry.work.util.*;
import exception.ScriptException;
import com.jerry.work.bean.Result;
import com.jerry.work.mapper.ResultMapper;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/12.
 */
@Service
public class ScriptService {

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private ServicePackTaskMapper servicePackTaskMapper;

    @Autowired
    private SendResultAndChangeStatusService sendResultAndChangeStatusService;

    private Logger logger = Logger.getLogger(ScriptService.class);

    /**
     * "userId":事件标识,
     *   "script":事件产生者,
     * "servicePackId",服务包标识,
     *   "issuerId":发布脚本任务的医生id,
     *   "issueTime":发布脚本任务的时间
     *
     * @param param
     * @return
     */
    public String getScriptStatus(String param) {
        logger.info("getScriptStatus 接收参数为：" + param);
        String code = "1";
        String result = "";
        try {
            Map<String, Object> map = getParamMap(param);
            Result resultObj = resultMapper.getResultByUserIdAndMethodAndServicePackIdAndIssueTime(map);
            if (resultObj == null) {
                result = map.get("userId").toString();
                throw new ScriptException("0");
            }
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String format = s.format(resultObj.getCreateTime());
            JSONObject resultJsonObj = JSONObject.fromObject(resultObj);
            resultJsonObj.put("createTime", format);
            result = resultJsonObj.toString();
        } catch (ScriptException e) {
            code = e.getMessage();
        } catch (Exception e) {
            code = "500";
        }
        String returnResult = GetScriptStatusResultUtil.handResult(code, result);
        logger.info("getScriptStatus 返回结果为" + returnResult);
        return returnResult;
    }

    public String updateScriptStatus(String param) {
        logger.info("getScriptStatus 接收参数为：" + param);
        String code = "1";
        String result = "";
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            map = getParamMap(param);
            int number = resultMapper.updateResultByUserIdAndMethod(map);
            if (number != 1) {
                code = "503";
            }else {
                String userId = map.get("userId").toString();
                String servicePackId = map.get("servicePackId").toString();
                String issueTime = map.get("issueTime").toString().replace("%20"," ");
                map.put("issueTime", issueTime);
                String issuerId = map.get("issuerId").toString();
                String groupId = map.get("groupId").toString();
                sendResultAndChangeStatusService.sendResultAndChangeStatusService(userId,servicePackId,issueTime,issuerId,groupId, GetNowStr.getNowStr());
            }
        } catch (ScriptException e) {
            code = e.getMessage();
        } catch (Exception e) {
            code = "500";
        }

        String returnResult = UpdateScriptStatusResultUtil.handResult(code, result);
        logger.info("getScriptStatus 返回结果为" + returnResult);
        return returnResult;
    }

    public Map<String, Object> getParamMap(String param) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String script = "";
        String userId = "";
        String servicePackId = "";
        String issueTime = "";
        String issuerId = "";
        String groupId = "";
        boolean isD = false;
        try {
            JSONObject paramJson = JSONObject.fromObject(param);
            userId = paramJson.getString("userId");
            script = paramJson.getString("script");
            servicePackId = paramJson.getString("servicePackId");
            issueTime = paramJson.getString("issueTime");
            issuerId = paramJson.getString("issuerId");
            groupId = paramJson.getString("groupId");
            map.put("userId", userId);
            map.put("servicePackId", servicePackId);
            map.put("issueTime", issueTime);
            map.put("issuerId", issuerId);
            map.put("groupId", groupId);
            String method = "";
            try {
                String[] split = script.split("\\.");
                String suffix = split[1];
                method = "Get" + split[0] + "Result";
            } catch (IndexOutOfBoundsException e) {
                logger.error(e.fillInStackTrace());
                throw new ScriptException("501");
            }
            map.put("method", method);
            isD = IsDoctor.isDoctor(userId);
        } catch (JSONException e) {
            logger.error(e.fillInStackTrace());
            return map;
        }
        if("".equals(userId) ||"".equals(script) ||"".equals(servicePackId) ||"".equals(issueTime) ||"".equals(issuerId) ||"".equals(groupId)){
            return map;
        }
        if(isD){
            String groupMessage = GetMessageService.getGroupMessageByGroupId(groupId);
            JSONObject groupMessageJson = JSONObject.fromObject(groupMessage);
            JSONObject info = groupMessageJson.getJSONObject("info");
            JSONObject bc = info.getJSONObject("bc");
            JSONObject infoObj = info.getJSONObject("info");
            String creator = infoObj.getString("creator");
            userId = bc.getString("dd_goumai");
            map.put("userId", userId);
        }
        return map;
    }
}
