package com.jerry.study.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by XX on 2017/7/26.
 */
@Controller
public class TestController {

    @Value("${test.msg}")
    private String msg;

    @RequestMapping("hello")
    public String hello(Map<String,String> map){
        map.put("msg",msg);
        System.out.println(msg);
        return "hello.html";
    }
}
