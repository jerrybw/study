package com.jerry.work.mapper;

import com.jerry.work.bean.ServicePackAlert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/30.
 */
@Mapper
public interface ServicePackAlertMapper {

    List<ServicePackAlert> findServicePackAlertByEventAndServicePackId(Map<String, String> param);
}
