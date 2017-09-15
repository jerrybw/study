package com.skch.service;

import com.skch.dao.T_form_itemRepository;
import com.skch.dao.T_formsRepository;
import com.skch.dao.T_user_formRepository;
import com.skch.dao.T_variableRepository;
import com.skch.entity.T_forms;
import com.skch.entity.T_variable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XX on 2017/7/28.
 */

@Service
public class GetResultService {

    @Autowired
    private T_formsRepository t_formsRepository;

    @Autowired
    private T_variableRepository t_variableRepository;

    private Logger logger = Logger.getLogger(GetResultService.class);

    public String getResult(String userId, Integer formId, HashMap<String, List<T_variable>> param){
        logger.info("收到处理结果的请求");
        T_forms form = t_formsRepository.getOne(formId);
        String method = form.getMethod();
        Class<?> clazz = null;
        Object result = null;
        try {
            clazz = Class.forName("com.skch.service.impl." + method);
            Object instance = clazz.newInstance();
            Method getResult = clazz.getDeclaredMethod("getResult", Map.class);
            getResult.setAccessible(true);
            result = getResult.invoke(instance, param);
        }catch (ClassNotFoundException e){
            return "完成";
        } catch (Exception e) {
            logger.error("发生错误"+e.fillInStackTrace());
        }
        return result == null? "发生错误":result.toString();
    }

}
