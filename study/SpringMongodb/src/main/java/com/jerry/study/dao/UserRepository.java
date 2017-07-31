package com.jerry.study.dao;

import com.jerry.study.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by XX on 2017/7/26.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String name);
}
