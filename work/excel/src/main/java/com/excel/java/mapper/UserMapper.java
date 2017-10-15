package com.excel.java.mapper;

import com.excel.java.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/10/13.
 */
@Mapper
public interface UserMapper {

    public List<User> getAllUser();
}
