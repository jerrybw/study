package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.Result;
import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.mapper.ResultMapper;
import com.jerry.work.mapper.ServicePackTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 向博文 on 2017/9/19.
 */
@Service
public class FindCasesService {

    @Autowired
    private ServicePackTaskMapper servicePackTaskMapper;

    @Autowired
    private ResultMapper resultMapper;

    public List<ServicePackTask> findCases(String groupId) throws Exception{
        List<ServicePackTask> servicePackTasks = servicePackTaskMapper.findServicePackTaskByGroupId(groupId);
        for (ServicePackTask servicePackTask:servicePackTasks){
            String url = servicePackTask.getUrl();
            if(url.contains("GuideMedicine")) {
                String userId = servicePackTask.getUserId();
                String issueTime = servicePackTask.getIssueTime().replace(" ","%20");
                Map<String, Object> map = new HashMap<String, Object>();
                String servicePackId = servicePackTask.getServicePackId();
                String issuerId = servicePackTask.getIssuerId();
                map.put("userId", userId);
                map.put("servicePackId", servicePackId);
                map.put("issueTime", issueTime);
                map.put("issuerId", issuerId);
                map.put("groupId", groupId);
                int i = url.indexOf("script=");
                int j = url.indexOf(".xml");
                String script = url.substring(i+"script=".length(),j);
                String method = "Get" + script + "Result";
                map.put("method", method);
                Result result = resultMapper.getResultByUserIdAndMethodAndServicePackIdAndIssueTime(map);
                if(result == null){
                    servicePackTask.setStatus(0);
                }else {
                    servicePackTask.setStatus(1);
                    Date createTime = result.getCreateTime();
                    DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
                    String now = dateFormatter.print(createTime, Locale.getDefault());
                    servicePackTask.setFinishTime(now);
                }
                url += "&userId=" + userId + "&servicePackId=" + servicePackId + "&issueTime=" + issueTime + "&issuerId=" + issuerId + "&groupId=" + groupId;
                servicePackTask.setUrl(url);
            }
        }
        return servicePackTasks;
    }

}
