package com.skch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skch.entity.T_form_item;

/**
 * Created by XX on 2017/7/27.
 */
public interface T_form_itemRepository extends JpaRepository<T_form_item,Integer> {
    T_form_item findByFormIdAndItemId(Integer formId, Integer itemId);

    List<T_form_item> findByFormId(Integer formId);

    T_form_item findByFormIdAndNumber(Integer formId, Integer number);
}
