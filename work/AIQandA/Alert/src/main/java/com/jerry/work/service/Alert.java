package com.jerry.work.service;

import org.springframework.stereotype.Service;

/**
 * Created by 向博文 on 2017/8/25.
 */
@Service
public class Alert {

    public static String getAlert(String dateTime,String thing,String appointment_id,String uid){
        return "提醒创建成功！\\n" +
                "--------------------\\n"+
                "主题："+thing+"\\n" +
                "时间："+dateTime+"\\n\\n" +
                "<a href='http://d.china-healthcare.cn/Appointment/appointmentRemind/appointment_id/"+appointment_id+"/uid/" + uid + "'>点击这里</a>，可以进一步设置该提醒 ";
    }
}
