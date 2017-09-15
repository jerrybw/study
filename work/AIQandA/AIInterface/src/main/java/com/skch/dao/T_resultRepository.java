package com.skch.dao;

import com.skch.entity.T_result;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XX on 2017/7/28.
 */
public interface T_resultRepository extends JpaRepository<T_result,Integer>{

    T_result findByUserIdAndFormName(String userId, String formName);
}
