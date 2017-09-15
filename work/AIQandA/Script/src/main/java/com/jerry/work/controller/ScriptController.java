package com.jerry.work.controller;

import com.jerry.work.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 向博文 on 2017/9/12.
 */
@RestController
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    @PostMapping("/getScriptStatus")
    public String getScriptStatus(String param){
        return scriptService.getScriptStatus(param);
    }

    @PostMapping("/updateScriptStatus")
    public String updateScriptStatus(String param){
        return scriptService.updateScriptStatus(param);
    }


}
