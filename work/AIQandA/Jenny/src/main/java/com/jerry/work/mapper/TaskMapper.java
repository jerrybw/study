package com.jerry.work.mapper;

import com.jerry.work.bean.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface TaskMapper {
    Task findById(Integer id);

    List<Task> findAllTask();

    int saveTask(Task task);

    int updateTaskById(Task task);

    int deleteTaskById(int id);
}
