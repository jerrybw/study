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

    /**
     * 处理事件请求的业务逻辑层分发器
     * @param event 事件
     * @param trigger 医生或患者标识
     * @param param //参数
     * @return
     */
    public String handEvent(String event,String trigger,String param){
        logger.info("参数：event = " +event + "&trigger=" + trigger + "&param = " + param);
        Class<?> clazz = null;
        String code = "1";
        Object result = "";
        String resultStr = "";
        try {
            clazz = Class.forName("com.jerry.work.service.HandEventService." + event.trim());
            Object instance = clazz.newInstance();
            Method getResult = clazz.getDeclaredMethod("handEvent", String.class,String.class,String.class);
            getResult.setAccessible(true);
            result = getResult.invoke(instance, event,trigger, param);
        }catch (InvocationTargetException e) {
            code = e.getTargetException().getMessage();
        }catch (Exception e){
            code = "500";
        }
        if(result == null || result.toString().equals("")){
            resultStr = ResultUtil.handResult(code);
        }else {
            resultStr = ResultUtil.handResultWithReturn(code,result.toString());
        }
        logger.info("返回结果：" + resultStr);
        return resultStr;
    }

}
