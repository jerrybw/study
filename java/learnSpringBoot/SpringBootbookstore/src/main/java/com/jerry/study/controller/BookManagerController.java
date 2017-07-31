package com.jerry.study.controller;

import com.jerry.study.entity.Book;
import com.jerry.study.repository.BookManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XX on 2017/7/31.
 */
@RestController
public class BookManagerController {

    @Autowired
    private BookManagerRepository bookManagerRepository;

    @PutMapping("manager/book")
    public String saveBook(Book book){
        Book save = bookManagerRepository.save(book);
        return save==null?"success":"failed";
    }
}
