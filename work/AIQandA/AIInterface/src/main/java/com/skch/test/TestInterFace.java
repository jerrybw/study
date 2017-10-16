package com.skch.test;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestInterFace {
    public static void main(String[] args){
        try {
            URL url = new URL("http://d.china-healthcare.cn/app/jk/id/JL100");// 创建连接
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");// 设置请求方式  
            connection.setRequestProperty("Accept","application/json");// 设置接收数据的格式  
            connection.setRequestProperty("Content-Type","application/json");// 设置发送数据的格式  
            connection.connect(); OutputStreamWriter out = new OutputStreamWriter( connection.getOutputStream(),"UTF-8");// utf-8编码  
            out.append("{\"fromid\": \"888888888\", \"toid\": \"100000343\", \"body\": \"我是测试的内\", \"type\": \"friend\"}"); out.flush(); out.close(); // 读取响应  
            int length = (int) connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();
            if (length != -1){
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0; int destPos = 0;
                while ((readLen = is.read(temp)) > 0){
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8");
                System.out.println(result);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}