package com.jerry.work.util;

import java.net.URLEncoder;

/**
 * Created by 向博文 on 2017/9/18.
 */
public class PushData {

    public static String pushData(String method,String issueId,String issueName,String issueRemark,String targetId,String targetName,String targetRemark) throws Exception{
        String Initiator = "{\"UserID\": \""+issueId+"\",\"UserName\": \""+issueName+"\",\"UserRemark\": \""+issueRemark+"\"}";
        String Confirmor = "{\"UserID\": \""+targetId+"\",\"UserName\": \""+targetName+"\",\"UserRemark\": \""+targetRemark+"\"}";
        String param = "method="+method+"&Initiator="+ URLEncoder.encode(Initiator,"utf-8")+"&Confirmor="+URLEncoder.encode(Confirmor,"utf-8");
        return HttpRequest.sendGet(" http://47.93.160.47:8868/nlu",param);
    }
}
