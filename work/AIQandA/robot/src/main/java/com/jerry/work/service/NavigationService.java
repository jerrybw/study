package com.jerry.work.service;

import com.jerry.work.bean.Navigation;
import com.jerry.work.dao.NavigationMapper;
import com.jerry.work.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理导航栏
 * Created by 向博文 on 2017/8/25.
 */
@Service
public class NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    /***
     * 根据用户id查询出用户匹配的导航栏
     *
     * @param userId
     * @return
     */
    public String getNavigation(String userId) {
        String[] strings = {"１", "２", "３", "４", "５", "６", "７", "８", "９", "０"};
        List<Navigation> navigations = navigationMapper.getNavigationByUserId(userId);
        String navigationsStr = "";
        for (int i = 0; i < navigations.size(); i++) {
            Navigation navigation = navigations.get(i);
            if (i <= 9 && i >= 0) {

            }
            navigationsStr += "<a href='" + navigation.getUrl() + "'>【" + strings[i] + "】" + navigation.getNavigationName() + "</a> \\n";
        }
        String result = "功能导航\\n" +
                "------------------\\n"
                + navigationsStr +
                "发送语音“提醒我XXXXX”，可添加提醒";
        return result;
    }
}
