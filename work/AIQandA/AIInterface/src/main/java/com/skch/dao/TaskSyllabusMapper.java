package com.skch.dao;

import com.skch.entity.TaskSyllabus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface TaskSyllabusMapper {

    List<TaskSyllabus> findByTaskStatusAndStartLessThanEqualAndJenny(Map<String, Object> param);


    List<TaskSyllabus> updateByUserIdAndJennyAndTaskStatusAndStartLessThanEquals(Map<String, Object> param);

    List<TaskSyllabus> updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(Map<String, Object> param);

    List<TaskSyllabus> updateStartByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(Map<String, Object> param);

    List<TaskSyllabus> updateStartByUserIdAndJennyAndTaskId(Map<String, Object> param);

    String getGroupIdByUserIdAndJenny(Map<String, Object> param);
}
