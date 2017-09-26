package com.jerry.work.mapper;

import com.jerry.work.bean.ServicePackTask;
import com.jerry.work.bean.Tmp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/30.
 */
@Mapper
public interface TmpMapper {

    Tmp findByEvent(String event);

}
