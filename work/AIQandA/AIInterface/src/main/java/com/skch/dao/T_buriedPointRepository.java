package com.skch.dao;

import com.skch.entity.T_buriedPoint;
import com.skch.entity.T_variable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XX on 2017/7/20.
 */
public interface T_buriedPointRepository extends JpaRepository<T_buriedPoint,Integer> {

    T_buriedPoint findByCommand(String command);

}
