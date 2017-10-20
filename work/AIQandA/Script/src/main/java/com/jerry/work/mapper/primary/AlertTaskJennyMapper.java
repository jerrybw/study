package com.jerry.work.mapper.primary;

import com.jerry.work.bean.AlertTaskJenny;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/30.
 */
public interface AlertTaskJennyMapper {
    List<AlertTaskJenny> findAlertTaskByAlertTimestampLessThanAndAlertTimesLessThanShouldAlertTimes(long nowTimeStamp);
}
