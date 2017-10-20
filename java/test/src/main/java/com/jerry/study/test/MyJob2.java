package com.jerry.study.test;

/**
 * Created by 向博文 on 2017/10/17.
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建要被定执行的任务类
 * @author long
 *
 */
public class MyJob2 implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(new Date()) + "job2");
    }

}