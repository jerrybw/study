package com.jerry.study.repository;

import com.jerry.study.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XX on 2017/7/31.
 */
public interface BookManagerRepository extends JpaRepository<Book,Integer>{

}
