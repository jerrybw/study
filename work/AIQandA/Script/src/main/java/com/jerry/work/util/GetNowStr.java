package com.jerry.work.util;

import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Created by 向博文 on 2017/9/20.
 */
public class GetNowStr {

    public static String getNowDateTimeStr(){
        Date createTime = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        String now = dateFormatter.print(createTime, Locale.getDefault());
        return now;
    }

    public static String getNowDateStr(){
        Date createTime = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
        String now = dateFormatter.print(createTime, Locale.getDefault());
        return now;
    }

    public static String getHanZiNowDateTimeStr(){
        Date createTime = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy年MM月dd日 HH点mm分ss秒");
        String now = dateFormatter.print(createTime, Locale.getDefault());
        return now;
    }

    public static String getHanZiNowDateStr(){
        Date createTime = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy年MM月dd日");
        String now = dateFormatter.print(createTime, Locale.getDefault());
        return now;
    }
}
