package com.jerry.work.mapper;

import com.jerry.work.bean.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface TaskMapper {
    Task findById(Integer id);
}
