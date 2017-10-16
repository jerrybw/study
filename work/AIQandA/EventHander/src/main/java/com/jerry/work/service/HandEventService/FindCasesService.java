package com.jerry.work.service.HandEventService;

import com.jerry.work.bean.Result;
import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.mapper.ResultMapper;
import com.jerry.work.mapper.ServicePackTaskMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @Value("${jianceurl}")
    private String jianceurl;

    private Logger logger = Logger.getLogger(FindCasesService.class);

    public List<ServicePackTask> findCases(String groupId) throws Exception{
        List<ServicePackTask> servicePackTasks = servicePackTaskMapper.findServicePackTaskByGroupId(groupId);
        for (ServicePackTask servicePackTask:servicePackTasks){
            String url = servicePackTask.getUrl();
            if(url.contains("script=")) {
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
                //http://p.china-healthcare.cn/DetectionResult/addDr/1507708569166/751/%E5%B0%BF%E5%B8%B8%E8%A7%84%E6%A3%80%E6%B5%8B
            }else if(url.contains("DetectionResult")){
                String issueTime = servicePackTask.getIssueTime();
                String[] split = url.split("=");
                url = jianceurl + "/" + issueTime + "/" + groupId + "/"+split[1];
                servicePackTask.setUrl(url);
            }
        }
        return servicePackTasks;
    }

}
