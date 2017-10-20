package com.skch.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.skch.dao.*;
import com.skch.entity.*;
import com.skch.service.GetResultService;
import com.skch.util.HttpRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.skch.util.JSONUtil.*;

/**
 * 此接口用于读取数据库中的评估表信息，评估项信息，用户正在评估哪张表的哪个评估项的信息
 * 并将用户下一个评估项返回，处理评估结果返回并保存在mongodb中，并将用户每个评估项的答案存储
 * Created by XX on 2017/7/27.
 */
@RestController
public class CAFRDBController {

    @Autowired
    private MongoDBResultRepository mongoDBResultRepository;//操作mongodb中评估结果的数据库层

    @Autowired
    private T_user_formRepository t_user_formRepository;//操作用户当前正在评估的评估表和评估项数据表的数据库层

    @Autowired
    private T_formsRepository t_formsRepository;//操作评估表数据表的数据库层

    @Autowired
    private T_form_itemRepository t_form_itemRepository;//操作评估项数据表的数据库层

    @Autowired
    private GetResultService getResultService;//操作评估结果的业务逻辑层

    @Autowired
    private T_resultRepository t_resultRepository;//操作评估结果数据表的数据库层

    @Autowired
    private T_variableRepository t_variableRepository;//操作用户评估项答案数据表的数据库层


    @Autowired
    private FormItemMapper formItemMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /***
     * 设置当前评估问题
     * @param str 传入的json参数 包含userId
     * @return 返回 {success:欢迎语}
     */
    @Transactional
    @RequestMapping("SetUserAssessment")
    public String setUserAssessment(String str){
        String userId = getValue(str,"userId");
        Integer formId = Integer.parseInt(getValue(str,"Assessment"));
        Integer number = Integer.parseInt(getValue(str,"number"));
        T_form_item form_item = t_form_itemRepository.findByFormIdAndNumber(formId, number);
        Integer itemId = form_item.getItemId();
        String next = getValue(str,"next");
        T_user_form user_form = new T_user_form(userId,formId,itemId,number);
        T_user_form t_user_form =t_user_formRepository.save(user_form);
        t_variableRepository.deleteByUserid(userId);
        return getResult("success",t_user_form != null ?"[\'\',\'" + next + "\']":"error");
    }

    /***
     * 获取欢迎语句
     * @param str 传入的json参数 包含userId
     * @return 返回 {welcomeMsg:欢迎语}
     */
    @RequestMapping("getWelcomeWords")
    public String getWelcomeWords(String str){
        String userId = getValue(str,"userId");
        T_user_form t_user_form = t_user_formRepository.findByUserId(userId);
        Integer formId = t_user_form.getFormId();
        T_forms t_forms = t_formsRepository.getOne(formId);
        String welcomeMsg = t_forms.getWelcomeMsg();
        return getResult("welcomeMsg",welcomeMsg);
    }


    /***
     * 获取问句
     * @param str 传入的json参数 包含userId
     * @return 返回 {ask:问句}
     */
    @RequestMapping("CAFRDBEvaluationItemAsk")
    public String getItemAsk(String str){
        T_form_item t_form_item = getFormItem(str);
        return getResult("ask",t_form_item.getAsk());
    }

    /***
     * 获取face
     * @param str 传入的json参数 包含userId
     * @return 返回 {face:face}
     */
    @RequestMapping("CAFRDBEvaluationItemFace")
    public String getItemFace(String str){
        T_form_item t_form_item = getFormItem(str);
        return getResult("face",t_form_item.getFace());
    }

    /***
     * 获取variable
     * @param str 传入的json参数 包含userId
     * @return 返回 {variable:variable}
     */
    @RequestMapping("CAFRDBEvaluationItemVariable")
    public String getItemVariable(String str){
        T_form_item t_form_item = getFormItem(str);
        return getResult("variable",t_form_item.getVariable());
    }

    /***
     * 获取下一个要去的标签的定位
     * @param str 传入的json参数 包含userId 继续的askId 结束的askId
     * @return 返回 {next:["","id******"]}
     */
    @RequestMapping("CAFRDBEvaluationItemNext")
    public String getItemNext(String str){
        String userId = getValue(str,"userId");
        T_user_form userForm = t_user_formRepository.findByUserId(userId);
        if (userForm == null)
            return getResult("next","[\'\',\'"+getValue(str,"end")+"\']");
        Integer number = userForm.getItemNumber();
        Integer formId = userForm.getFormId();
        T_form_item form_item = null;
        String value = "";
        String paramValue = "";
        do {
            form_item = t_form_itemRepository.findByFormIdAndNumber(formId,++number);
            if(form_item == null)
                break;
            String selection = form_item.getSelection();
            if(selection == null || selection.equals(""))
                break;
            String[] split = selection.split(":");
            Integer lastNumber = Integer.parseInt(split[0]);
            value = split[1];
            T_form_item lastFormItem = t_form_itemRepository.findByFormIdAndNumber(form_item.getFormId(), lastNumber);
            String variable = lastFormItem.getVariable();
            T_variable t_variable = t_variableRepository.findByUseridAndParamkey(userId, variable);
            if(t_variable == null){
                continue;
            }
            paramValue = t_variable.getParamvalue();
        }while (!value.equals(paramValue));
        if(form_item != null){
            T_user_form user_form = new T_user_form();
            user_form.setUserId(userId);
            user_form.setFormId(form_item.getFormId());
            user_form.setItemId(form_item.getItemId());
            user_form.setItemNumber(form_item.getNumber());
            t_user_formRepository.save(user_form);
            return getResult("next","[\'\',\'"+getValue(str,"continue")+"\']");
        }else {
            logger.info("脚本问题问完，调用处理结果方法");
            return getResult("next","[\'\',\'"+getValue(str,"end")+"\']");
        }
    }

    /***
     * 获取评估结果并存入数据库
     * @param str 传入的json参数 包含userId
     * @return 返回 {"result":结果}
     */
    @Transactional
    @RequestMapping("CAFRDBEvaluationResultMsg")
    public String getResultMsg(String str){
        JSONObject object = JSONObject.fromObject(str);
        String userId = object.getString("userId");
        String result = "";
        try {
            T_user_form user_form = t_user_formRepository.findByUserId(userId);
            Integer formId = user_form.getFormId();
            T_forms forms = t_formsRepository.getOne(formId);
            List<T_variable> t_variables = t_variableRepository.findByUserid(userId);
            Map<String,Object> items = new HashMap<>();
            for (T_variable t_variable: t_variables) {
                String key = t_variable.getParamkey();
                String value = t_variable.getParamvalue();
                items.put(key,value);
            }
            HashMap<String, List<T_variable>> param = new HashMap<String,List<T_variable>>();
            param.put("t_variables",t_variables);
            result = getResultService.getResult(userId,formId,param);
            T_result t_result = new T_result();
            t_result.setUserId(userId);
            t_result.setFormName(forms.getName());
            t_result.setFormPurpose(forms.getPurpose());
            t_result.setResult(result);
            LocalDateTime createTime = LocalDateTime.now();
            ZoneId zoneId = ZoneId.systemDefault();
            Instant instant = createTime.atZone(zoneId).toInstant();
            Date date = Date.from(instant);
            t_result.setCreateTime(date);
            T_result saveResult = t_resultRepository.save(t_result);
            MongoDBResult mongoDBResult = new MongoDBResult(userId,createTime,items);
            MongoDBResult save = mongoDBResultRepository.save(mongoDBResult);
            saveResult.setMongoDB(save.getId());
        } catch (Exception e){
            logger.error("计算结果出现错误"+"\n" + e.toString());
            return getResult("result","计算结果出现错误");
        }
        if (result == null){
            logger.info("计算结果出现错误"+"\n" + "结果返回为空");
        }
        return getResult("result",result == null?"计算结果出现错误":result);
    }

    /**
     * 获取评估表内所有评估项的答案
     * @param resultId
     * @return
     */
    @PostMapping("/getDetailResult")
    public String getDetailResult(String resultId){
        int id = Integer.parseInt(resultId);
        T_result result = null;
        String resultStr = "";
        result = t_resultRepository.getOne(id);
        MongoDBResult mongoDBResult = mongoDBResultRepository.findOne(result.getMongoDB());
        Map<String, Object> items = mongoDBResult.getItems();
        Set<String> keys = items.keySet();
        List<T_form_item> t_form_items = formItemMapper.findByFormName(result.getFormName());
        Map<String, String> resultMap = new HashMap<String, String>();
        for (T_form_item t : t_form_items) {
            if (!keys.contains(t.getVariable())) {
                continue;
            }
            String ask = t.getAsk();
            String answer = items.get(t.getVariable()).toString();
            answer = answer.replaceAll("<span>","");
            answer = answer.replaceAll("</span>","");
            resultMap.put(ask, answer);
        }
        JSONObject object = JSONObject.fromObject(resultMap);
        resultStr = object.toString();
        return resultStr;
    }

    public T_form_item getFormItem(String str){
        String userId = getValue(str,"userId");
        T_user_form t_user_form = t_user_formRepository.findByUserId(userId);
        Integer formId = t_user_form.getFormId();
        Integer itemNumber = t_user_form.getItemNumber();
        T_form_item t_form_item = t_form_itemRepository.findByFormIdAndNumber(formId, itemNumber);
        return t_form_item;
    }

    public String getResult(String key,String value){
        return "{" + key +":\"" + value.replaceAll("\"","\'") + "\"}";
    }
}
