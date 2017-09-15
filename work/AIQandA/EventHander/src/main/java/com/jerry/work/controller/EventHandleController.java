package com.jerry.work.controller;

import com.jerry.work.service.EventHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 向博文 on 2017/8/30.
 */
@RestController
public class EventHandleController {

    @Autowired
    private EventHandleService eventHandleService;

    @PostMapping("/handEvent")
    public String sendServicePackAlert(String event,String trigger,String param){
        String result = eventHandleService.handEvent(event,trigger,param);
        return result;
    }
}
