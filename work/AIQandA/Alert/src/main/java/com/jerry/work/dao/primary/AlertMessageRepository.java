package com.jerry.work.dao.primary;

import com.jerry.work.bean.primary.AlertMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/18.
 */
public interface AlertMessageRepository extends JpaRepository<AlertMessage,Integer> {
    /**
     * 查询出数据库中提醒时间早于当前时间并且有效的提醒
     * @param date
     * @param except
     * @return
     */
    List<AlertMessage> findByFireMomentLessThanEqualAndExcept(long date, int except);
}
