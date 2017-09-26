package com.jerry.work.mapper;

import com.jerry.work.bean.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/9/1.
 */
@Mapper
public interface ResultMapper {
    int updateResultByUserIdAndMethod(Map<String, Object> param);

    Result findByResultByUserIdAndMethod(Map<String, Object> param);

    Result getResultByUserIdAndMethodAndServicePackIdAndIssueTime(Map<String, Object> param);
}
