package com.jerry.study.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by 向博文 on 2017/10/17.
 */
public class MainScheduler {

    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }


    public static void schedulerJob() throws SchedulerException{
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1111111").build();

        JobDetail jobDetai2 = JobBuilder.newJob(MyJob2.class).withIdentity("job2", "group1111111").build();
        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3333333")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();
        //创建触发器 每3秒钟执行一次
        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group3333333")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();
        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.scheduleJob(jobDetai2, trigger2);
        //调度器开始调度任务
        scheduler.start();

    }

    public static void main(String[] args) throws SchedulerException {
        MainScheduler mainScheduler = new MainScheduler();
        mainScheduler.schedulerJob();
    }

}
