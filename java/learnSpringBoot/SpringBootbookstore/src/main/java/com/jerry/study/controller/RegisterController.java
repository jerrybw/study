package com.jerry.study.controller;

import com.jerry.study.entity.User;
import com.jerry.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by acer on 2017/7/30.
 */
@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("register")
    public String register(@RequestBody User user){
        User canRegister = userRepository.findByUserName(user.getUserName());
        if(canRegister == null){
            User save = userRepository.save(user);
            return "注册成功，去<a href='/gotoLogin'>登陆</a>";
        } else {
            return "注册失败，用户名已被占用";
        }
    }
}
