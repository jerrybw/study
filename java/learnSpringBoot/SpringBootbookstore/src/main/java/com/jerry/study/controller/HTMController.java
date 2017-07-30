package com.jerry.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by acer on 2017/7/29.
 */
@Controller
public class HTMController {

    @RequestMapping("/")
    public String gotoIndex(){
        return "index";
    }
    @RequestMapping("gotoLogin")
    public String gotoLogin(){
        return "login";
    }
    @RequestMapping("gotoRegister")
    public String gotoRegister(){
        return "register";
    }
}
