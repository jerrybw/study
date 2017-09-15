package com.jerry.work.dao;

import com.jerry.work.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/29.
 */
@Mapper
public interface UserMapper {

//    @Select("select id,user_name userName from user")
    List<User> getUsers();
}
