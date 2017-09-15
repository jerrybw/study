package com.jerry.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 向博文 on 2017/9/14.
 */
@RestController
public class Test {

    @Value("${a}")
    private String aa;


    @GetMapping("/")
    public String test(){
        ClassLoader classLoader = Test.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("a.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String a = properties.getProperty("a");
        System.out.println(a);
        return a;
    }
}
