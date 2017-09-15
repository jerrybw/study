package com.skch.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
//import net.sf.json.JSONObject;


public class SendResult {

    public static final String ADD_URL = "http://d.china-healthcare.cn/app/jk/id/JL100";
    private static Logger logger = Logger.getLogger(SendResult.class);

    /**
     * 实现家医中对话的接口
     *
     * @param fromId
     * @param toId
     * @param body
     * @param type
     */
    public static void sendWord(String fromId, String toId, String body, String type) throws Exception{
        //创建连接
        URL url = new URL(ADD_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();

        //POST请求
        DataOutputStream out = new DataOutputStream(
                connection.getOutputStream());
        body = URLEncoder.encode(body, "utf-8");
        String s = "fromid=" + fromId + "&toid=" + toId + "&body=" + body + "&type=" + type;
        out.writeBytes(s);
        out.flush();
        out.close();

        //读取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
    }
}
