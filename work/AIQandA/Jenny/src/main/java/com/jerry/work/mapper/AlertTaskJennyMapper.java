package com.jerry.work.mapper;

import com.jerry.work.bean.AlertTaskJenny;
import com.jerry.work.bean.ServicePackTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/30.
 */
@Mapper
public interface AlertTaskJennyMapper {
    List<AlertTaskJenny> findAlertTaskByAlertTimestampLessThanAndAlertTimesLessThanShouldAlertTimes(long nowTimeStamp);

    void update(AlertTaskJenny alertTaskJenny);
}
