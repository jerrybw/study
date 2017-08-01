package com.jerry.study.controller;

import com.jerry.study.dao.ConversationRepository;
import com.jerry.study.entity.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 向博文 on 2017/8/1.
 */
@RestController
public class ConversationController {

    @Autowired
    ConversationRepository conversationRepository;

    @PostMapping("conversation")
    public String save(){
        Conversation conversation = new Conversation();
        conversation.setFromId("1");
        conversation.setToId("2");
        conversation.setType("friend");
        conversation.setTimestamp(1231323);
        Conversation save = conversationRepository.save(conversation);
        return save.toString();
    }
}
