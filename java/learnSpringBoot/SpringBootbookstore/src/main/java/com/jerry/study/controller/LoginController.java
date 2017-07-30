package com.jerry.study.controller;

import com.jerry.study.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by acer on 2017/7/29.
 */
@RestController
public class LoginController {

    @RequestMapping("login")
    public String login(@RequestBody User user){
        return "张三".equals(user.getUserName())&&"123456".equals(user.getPassword())?"success":"faild";
    }
}
