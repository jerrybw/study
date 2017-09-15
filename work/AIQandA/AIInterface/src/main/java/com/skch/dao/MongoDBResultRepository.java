package com.skch.dao;

import com.skch.entity.MongoDBResult;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by 向博文 on 2017/8/4.
 */
public interface MongoDBResultRepository extends MongoRepository<MongoDBResult,String> {
}
