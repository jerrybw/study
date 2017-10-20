package com.jerry.work.runner;

import com.jerry.work.jennytype.M5;
import com.jerry.work.jennytype.S10;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by CavanLiu on 2017/2/28 0028.
 */
@Component
@Order(value=1)
public class StartupRunner implements CommandLineRunner
{
    @Override
    public void run(String... args) throws Exception
    {
        M5.run();
        S10.run();
    }
}