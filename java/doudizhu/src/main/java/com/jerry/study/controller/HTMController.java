package com.jerry.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 向博文 on 2017/8/24.
 */
@Controller
public class HTMController {

    @RequestMapping("/")
    public String index(){
        System.out.println("aaaaa");
        return "index";
    }
}
