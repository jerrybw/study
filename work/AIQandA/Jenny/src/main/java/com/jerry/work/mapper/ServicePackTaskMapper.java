package com.jerry.work.mapper;

import com.jerry.work.bean.ServicePackTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/30.
 */
@Mapper
public interface ServicePackTaskMapper {

    int saveServicePackTask(ServicePackTask servicePackTask);

    ServicePackTask findServicePackTaskById(int id);

    List<ServicePackTask> findServicePackTaskByGroupId(String groupId);

}
