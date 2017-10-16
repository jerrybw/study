package com.jerry.work.reminder;

import com.jerry.work.service.AlertService;
import com.jerry.work.util.SpringUtil;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    //创建一个定时器，一秒后开始，每五秒执行一次run方法
    public static void remind() {
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 1000, 5000);
    }
}

class RemindTask extends TimerTask {

    //调用AlertService中的alert方法
    @Override
    public void run() {
        AlertService bean = SpringUtil.getBean(AlertService.class);
        bean.alert();
    }

}