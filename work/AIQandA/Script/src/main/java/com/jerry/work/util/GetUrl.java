package com.jerry.work.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 向博文 on 2017/9/20.
 */
public class GetUrl {
    public static String getUrl(String PropertiesName) throws Exception{
        ClassLoader classLoader = GetUrl.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("url.properties");
        Properties urlProperties = new Properties();
        String url = "";
        try {
            urlProperties.load(in);
            url = urlProperties.getProperty(PropertiesName);
        } catch (IOException e) {
        }finally {
            in.close();
        }
        return url;
    }
}
