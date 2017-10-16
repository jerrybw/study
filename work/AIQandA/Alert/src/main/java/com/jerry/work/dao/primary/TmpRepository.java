package com.jerry.work.dao.primary;

import com.jerry.work.bean.primary.AlertMessage;
import com.jerry.work.bean.primary.Tmp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/18.
 */
public interface TmpRepository extends JpaRepository<Tmp,Integer> {

    Tmp findByEvent(String event);
}
