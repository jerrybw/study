package com.jerry.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by XX on 2017/7/26.
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    public String hello(Map<String,String> map){
        map.put("msg","hello springboot");
        return "hello";
    }
}
