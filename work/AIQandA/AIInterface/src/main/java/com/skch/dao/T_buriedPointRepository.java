package com.skch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skch.entity.T_buriedPoint;

/**
 * Created by XX on 2017/7/20.
 */
public interface T_buriedPointRepository extends JpaRepository<T_buriedPoint,Integer> {

    T_buriedPoint findByCommand(String command);

}
