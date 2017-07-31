package com.jerry.study.service.impl;

import com.jerry.study.dao.UserRepository;
import com.jerry.study.entity.User;
import com.jerry.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XX on 2017/7/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
