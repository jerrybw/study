package com.jerry.work.util;

import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Created by 向博文 on 2017/9/20.
 */
public class GetNowStr {

    public static String getNowStr(){
        Date createTime = new Date();
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        String now = dateFormatter.print(createTime, Locale.getDefault());
        return now;
    }
}
