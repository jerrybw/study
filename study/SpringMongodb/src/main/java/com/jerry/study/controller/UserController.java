package com.jerry.study.controller;

import com.jerry.study.entity.User;
import com.jerry.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by XX on 2017/7/26.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{name}")
    public ModelAndView getUser(@PathVariable String name){
        User user = userService.findByName(name);
        ModelAndView view = new ModelAndView("user");
        view.addObject("user",user);
        return view;
    }

    @PostMapping("/user")
    public String saveUser(User user){
        User save = userService.save(user);
        return "保存成功";
    }
}
