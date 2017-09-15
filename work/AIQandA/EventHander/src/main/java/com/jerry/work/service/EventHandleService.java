package com.jerry.work.service;

import com.jerry.work.excrption.EventException;
import com.jerry.work.util.ResultUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 向博文 on 2017/8/30.
 */
@Service
public class EventHandleService {


    private Logger logger = Logger.getLogger(EventHandleService.class);

    public String handEvent(String event,String trigger,String data){
        logger.info("参数：event = " +event + "&trigger=" + trigger + "&data = " + data);
        Class<?> clazz = null;
        String code = "1";
        try {
            clazz = Class.forName("com.jerry.work.service.HandEventService." + event);
            Object instance = clazz.newInstance();
            Method getResult = clazz.getDeclaredMethod("handEvent", String.class,String.class,String.class);
            getResult.setAccessible(true);
            getResult.invoke(instance, event,trigger, data);
        }catch (InvocationTargetException e) {
            code = e.getTargetException().getMessage();
        }catch (Exception e){
            code = "500";
        }
        String resultStr = ResultUtil.handResult(code);
        logger.info("返回结果：" + resultStr);
        return resultStr;
    }

}
