package com.jerry.work.mapper;

import com.jerry.work.bean.Task;
import com.jerry.work.bean.TaskSyllabus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface TaskSyllabusMapper {

    List<TaskSyllabus> findByTaskStatusAndStartLessThanEqualAndJenny(Map<String, String> param);


    List<TaskSyllabus> updateByUserIdAndJennyAndTaskStatusAndStartLessThanEquals(Map<String, String> param);

    List<TaskSyllabus> updateByUserIdAndJennyAndTaskStatusAndStartLessThanEqualsAndTaskId(Map<String, Object> param);

    String getGroupIdByUserIdAndJenny(Map<String, Object> param);

    int updateStatusByTaskId(Integer taskId);

    List<TaskSyllabus> findAllTaskSyllabus();

    int saveTaskSyllabus(TaskSyllabus taskSyllabus);

    int updateTaskSyllabus(TaskSyllabus taskSyllabus);

    TaskSyllabus findById(Integer id);

    int deleteTaskSyllabusById(Integer id);
}
