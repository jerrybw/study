package com.skch.dao;

import com.skch.entity.T_variable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by XX on 2017/7/20.
 */
public interface T_variableRepository extends JpaRepository<T_variable,Integer> {

    T_variable findByUseridAndParamkey(String userid, String paramkey);


    List<T_variable> findByUserid(String userid);

    void deleteByUserid(String userid);
}
