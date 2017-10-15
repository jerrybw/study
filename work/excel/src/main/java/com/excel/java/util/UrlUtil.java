package com.excel.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 向博文 on 2017/10/15.
 */
public class UrlUtil {

    public static String getUrl(String urlName){
        String url = "";
        InputStream urlIs = Class.class.getResourceAsStream("/url.properties");
        Properties properties = new Properties();
        try {
            properties.load(urlIs);
            url = properties.getProperty(urlName);
        }catch (IOException e){

        }
        return url;
    }
}
