package com.jerry.study.dao;

import com.jerry.study.entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by 向博文 on 2017/8/1.
 */
public interface ConversationRepository  extends MongoRepository<Conversation,String>{
}
