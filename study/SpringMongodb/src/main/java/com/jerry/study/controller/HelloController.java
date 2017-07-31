package com.jerry.study.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jerry.study.dao.UserRepository;
import com.jerry.study.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by XX on 2017/7/26.
 */
@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String gotoLogin(Map<String,Object> map){
        System.out.println("接收到请求");
        map.put("msg","欢迎登陆");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Map<String,Object> map){
        List<User> users = userRepository.findAll();
        map.put("users",users);
        return "user";
    }

    @RequestMapping("/gotoRegister")
    public String gotoRegister(Map<String,Object> map){
        map.put("msg","欢迎注册");
        return "register";
    }

    @ResponseBody
    @RequestMapping("/register")
    public String register(User user,Map<String,String> map){
        User s = userRepository.save(user);
        map.put("msg","注册成功，您现在就<a href='/'>登陆</a>吗？");
        return s == null? "注册失败":"注册成功";
    }

}
