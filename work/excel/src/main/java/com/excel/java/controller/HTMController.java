package com.excel.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 向博文 on 2017/10/14.
 */
@Controller
public class HTMController {


    @RequestMapping("/gotoVideoUpload")
    public String gotoVideoUpload(){
        return "videoUpload.html";
    }
}
