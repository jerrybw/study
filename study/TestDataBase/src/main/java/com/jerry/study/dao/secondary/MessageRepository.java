package com.jerry.study.dao.secondary;

import com.jerry.study.bean.secondary.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 向博文 on 2017/8/29.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
