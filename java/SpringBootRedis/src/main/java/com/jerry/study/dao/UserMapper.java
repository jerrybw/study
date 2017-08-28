package com.jerry.study.dao;

import com.jerry.study.bean.User;
import com.jerry.study.bean.UserTags;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/28.
 */
public interface UserMapper {
    User findUserInfoById(int id);

    List<UserTags> findUserTagsByUserId(Integer userId);
}
