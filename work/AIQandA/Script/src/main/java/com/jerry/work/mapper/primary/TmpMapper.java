package com.jerry.work.mapper.primary;

import com.jerry.work.bean.Tmp;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 向博文 on 2017/8/30.
 */
public interface TmpMapper {

    Tmp findByEvent(String event);

}
