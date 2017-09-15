//package com.jerry.study.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * Created by 向博文 on 2017/8/28.
// */
//@RestController
//public class AlertMsgController {
//
//    @Autowired
//    private AlertMsgRepository alertMsgRepository;
//
//    @GetMapping("/")
//    public String getAlertMsg(){
//        List<AlertMsg> byYyStartLessThanEqual = alertMsgRepository.findByYyStartLessThanEqualAndYyType("2017-08-28 16:00","2");
//        for (AlertMsg a: byYyStartLessThanEqual) {
//            System.out.println(a);
//        }
//        return "唱歌";
//    }
//
//}
