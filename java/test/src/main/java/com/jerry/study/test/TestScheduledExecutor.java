package com.jerry.study.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 向博文 on 2017/10/17.
 */
public class TestScheduledExecutor implements Runnable {
    private String jobName = "";
    public TestScheduledExecutor(String jobName) {
        super();
        this.jobName = jobName;
    }
    @Override
    public void run() {
        System.out.println("当前线程名："+Thread.currentThread().getName());
        if (jobName.equals("job2")) {
            long start = System.currentTimeMillis();
            for (int i = 1;i <60000;i++){
                int sum = 1;
                for (int j = 1;j <= i;j++){
                    sum *= j;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("耗费时间："+(end - start));
        }
        System.out.println("执行任务：" + jobName);
    }

    static class TimerTest extends TimerTask {
        private String jobName = "";
        public TimerTest(String jobName) {
            super();
            this.jobName = jobName;
        }

        @Override
        public void run() {
            System.out.println("当前线程名："+Thread.currentThread().getName());
            if (jobName.equals("TIMERJob4")) {
                long start = System.currentTimeMillis();
                for (int i = 1;i <60000;i++){
                    int sum = 1;
                    for (int j = 1;j <= i;j++){
                        sum *= j;
                    }
                }
                long end = System.currentTimeMillis();
                System.out.println("TIMER耗费时间："+(end - start));
            }
            System.out.println("执行TIMER任务：" + jobName);
        }
    }

        public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        service.scheduleAtFixedRate(
                new TestScheduledExecutor("job1"), initialDelay1,period1, TimeUnit.SECONDS);
        long initialDelay2 = 2;
        long delay2 = 2;
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        service.scheduleWithFixedDelay(
                new TestScheduledExecutor("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);

        Timer timer = new Timer();
        long timerDelay1 = 1 * 1000;
        long timerPeriod1 = 1000;
        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
        timer.schedule(new TimerTest("TIMERJob3"), timerDelay1, timerPeriod1);
        long timerDelay2 = 2 * 1000;
        long timerPeriod2 = 2000;
        // 从现在开始 2 秒钟之后，每隔 2 秒钟执行一次 job2
        timer.schedule(new TimerTest("TIMERJob4"), timerDelay2, timerPeriod2);
        }
}
