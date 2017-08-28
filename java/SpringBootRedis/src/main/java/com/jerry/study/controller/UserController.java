package com.jerry.study.controller;

import com.jerry.study.bean.User;
import com.jerry.study.bean.UserTags;
import com.jerry.study.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/28.
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "user",method = RequestMethod.GET)
    public String getAllUser(Map<String,Object> map){
        User userInfo = userMapper.findUserInfoById(1);
        List<UserTags> userTagsByUserId = userMapper.findUserTagsByUserId(1);
        for (UserTags userTags:userTagsByUserId){
            System.out.print(userTags.getUserName() + "****" + userTags.getTagName());
        }
        map.put("user",userInfo);
        return "users";
    }
}
