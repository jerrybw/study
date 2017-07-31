package com.jerry.study;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XX on 2017/7/25.
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
