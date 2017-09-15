package com.jerry.study.dao.primary;

import com.jerry.study.bean.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/29.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user where user_name = ?1",nativeQuery = true)
    User findByUserName(String userName);

    @Query(value = "select * from user where not user_name = ''",nativeQuery = true)
    List<User> findByUserNameNotEmpty();
}