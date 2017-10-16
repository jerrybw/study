package com.jerry.work.util;

import com.jerry.work.bean.primary.Tmp;
import com.jerry.work.dao.primary.TmpRepository;
import com.jerry.work.eventEnum.JiaoChaOrJiaYi;
import org.apache.log4j.Logger;

import java.util.Map;

import static com.jerry.work.util.SpringUtil.getBean;

/**
 * Created by 向博文 on 2017/8/25.
 */
public class SendWeiXin {

    private static Logger logger = Logger.getLogger(SendWeiXin.class);

    private static TmpRepository tmpRepository;

    public static void sendWeiXin(String event, String openId, String clickUrl, String first, Map<String,String> keyWords, String remark) throws Exception{
        tmpRepository = getBean(TmpRepository.class);
        String jiayimuban = "";
        String jiaochaxuekemuban = "";
        String jiayipmuban = "";
        String tmpId = "";
        JiaoChaOrJiaYi jiaoChaOrJiaYi = null;
        jiayimuban = GetUrl.getUrl("jiayimuban");
        jiaochaxuekemuban = GetUrl.getUrl("jiaochaxuekemuban");
        jiayipmuban = GetUrl.getUrl("jiayipmuban");
        Tmp tmp = tmpRepository.findByEvent(event);
        tmpId = tmp.getdTmpId();
        jiaoChaOrJiaYi = JiaoChaOrJiaYi.JIA_YI;
        boolean isDoctor = IsDoctor.isDoctor(openId);
        if(!isDoctor){
            tmpId = tmp.getpTmpId();
            jiaoChaOrJiaYi = JiaoChaOrJiaYi.JIA_YI_P;
        }
        String param = "tmpid="+tmpId+"&openid="+openId+"&url="+clickUrl+"&first="+first+"&remark="+remark;
        for(int i = 1;i<=keyWords.size();i++){
            param += "&keyword"+i+"="+keyWords.get("keyword"+i);
        }
        if(jiaoChaOrJiaYi == JiaoChaOrJiaYi.JIA_YI){
            HttpRequest.sendPost(jiayimuban,param);
        }else {
            HttpRequest.sendPost(jiayipmuban,param);
        }
    }

}
