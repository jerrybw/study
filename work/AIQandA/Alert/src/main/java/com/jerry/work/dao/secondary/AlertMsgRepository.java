package com.jerry.work.dao.secondary;

import com.jerry.work.bean.secondary.AlertMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/29.
 */
public interface AlertMsgRepository extends JpaRepository<AlertMsg,Integer>{
    //
    @Query(value = "SELECT * FROM dao_sk_appointment_book WHERE valid = ?1 AND yy_type = ?2 AND (yy_start <= ?3 or (yy_remind <= ?4 and not yy_remind = ''))",nativeQuery = true)
    List<AlertMsg> findByYyStartLessThanEqualAndYyTypeAndValidOrYyRemindLessThanEqual(String valid, String yyType,String yyStart,String yyRemind);//
}
