package com.jerry.study.controller;

import com.jerry.study.entity.Book;
import com.jerry.study.repository.BookManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by acer on 2017/7/29.
 */
@Controller
public class HTMController {

    @Autowired
    private BookManagerRepository bookManagerRepository;

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
    @RequestMapping("gotoBookManager")
    public String gotoBookManager(Map<String,Object> param){
        List<Book> books = bookManagerRepository.findAll();
        param.put("books",books);
        return "manager/bookManager";
    }

    @RequestMapping("gotoBookEdit")
    public String gotoBookEdit(Map<String,Object> param){
        return "manager/bookEdit";
    }

    @RequestMapping("a")
    public String gotoA(){
        return "a";
    }
}
