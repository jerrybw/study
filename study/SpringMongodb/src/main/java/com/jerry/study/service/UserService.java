package com.jerry.study.service;

import com.jerry.study.entity.User;

/**
 * Created by XX on 2017/7/26.
 */
public interface UserService {
    User save(User user);

    User findByName(String name);

}