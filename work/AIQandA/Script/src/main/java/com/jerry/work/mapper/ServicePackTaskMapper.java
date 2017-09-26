package com.jerry.work.mapper;

import com.jerry.work.bean.ServicePackTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/30.
 */
@Mapper
public interface ServicePackTaskMapper {

    int saveServicePackTask(ServicePackTask servicePackTask);

    int updateServicePackTask(ServicePackTask servicePackTask);

    List<ServicePackTask> findServicePackTaskByGroupId(String groupId);

    ServicePackTask findServicePackTaskByGroupIdAndIssueTimeAndUserId(Map<String, Object> groupId);


}
