package com.skch.service;

import com.skch.entity.T_variable;

import java.util.List;
import java.util.Map;

/**
 * Created by XX on 2017/7/28.
 */
public interface GetResultInter {

    public String getResult(Map<String,List<T_variable>> param) throws Exception;
}
