package com.jerry.work.dao;

import com.jerry.work.bean.MongoDBResult;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by 向博文 on 2017/8/4.
 */
public interface MongoDBResultRepository extends MongoRepository<MongoDBResult,String> {
}
