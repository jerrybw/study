package com.jerry.work.mapper.primary;

import com.jerry.work.bean.SchemeCondition;

import java.util.Map;

/**
 * Created by 向博文 on 2017/10/20.
 */
public interface SchemeConditionMapper {
    public SchemeCondition findSchemeConditionBySPIAndScriptAndFINAndFIV(Map<String,Object> param);
}
