package com.jerry.work.dao;

import com.jerry.work.bean.Goto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 向博文 on 2017/8/21.
 */
public interface GotoRepository extends JpaRepository<Goto,Integer> {

    Goto findBySubject(String subject);
}
