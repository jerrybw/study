package com.jerry.study.repository;

import com.jerry.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by acer on 2017/7/30.
 */
public interface UserRepository extends JpaRepository<User,Integer>{


    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);
}
