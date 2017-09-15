package com.jerry.work.dao;

import com.jerry.work.bean.Navigation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 向博文 on 2017/8/29.
 */
@Mapper
public interface NavigationMapper {

    List<Navigation> getNavigationByUserId(String userId);
}
