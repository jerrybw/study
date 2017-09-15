package com.skch.dao;

import com.skch.entity.T_form_item;
import com.skch.entity.T_user_form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by XX on 2017/7/27.
 */
public interface T_form_itemRepository extends JpaRepository<T_form_item,Integer> {
    T_form_item findByFormIdAndItemId(Integer formId, Integer itemId);

    List<T_form_item> findByFormId(Integer formId);

    T_form_item findByFormIdAndNumber(Integer formId, Integer number);
}
