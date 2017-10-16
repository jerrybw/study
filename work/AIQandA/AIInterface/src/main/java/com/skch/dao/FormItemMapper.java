package com.skch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.skch.entity.T_form_item;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface FormItemMapper {

    List<T_form_item> findByFormName(String fromName);

}
