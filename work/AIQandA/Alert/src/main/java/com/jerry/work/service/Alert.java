package com.jerry.work.service;

import com.jerry.work.util.IsDoctor;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by 向博文 on 2017/8/25.
 */
@Service
public class Alert {

    public static String getAlert(String dateTime, String thing, String appointment_id, String uid, String remarkName,boolean createBySelf,boolean wantCreateToOther){
        String result = "创建提醒\\n";
        String remark = "点击这里</a>，可以进一步设置该提醒 ";
        result += "--------------------\\n";
        if(!createBySelf){
            result += "提醒："+remarkName + "\\n";
        }
       result += "主题："+thing+"\\n" +
                "时间："+dateTime+"\\n\\n" ;
        if(wantCreateToOther){
            remark = "点击这里创建成功</a>";
        }
        if(IsDoctor.isDoctor(uid)){
            result+= "<a href='http://d.china-healthcare.cn/Appointment/appointmentRemind/appointment_id/"+appointment_id+"/uid/" + uid + "'>"+remark;
        }else {
            result+= "<a href='http://p.china-healthcare.cn/Appointment/appointmentRemind/appointment_id/"+appointment_id+"/uid/" + uid + "'>"+remark;
        }
        return result;
    }
}
