package com.skch.dao;

import com.skch.entity.T_form_item;
import com.skch.entity.TaskSyllabus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface FormItemMapper {

    List<T_form_item> findByFormName(String fromName);

}
