package com.skch.dao;

import com.skch.entity.T_user_form;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XX on 2017/7/27.
 */
public interface T_user_formRepository  extends JpaRepository<T_user_form,Integer>{
    T_user_form findByUserId(String userId);
    T_user_form deleteByUserId(String userId);
}
