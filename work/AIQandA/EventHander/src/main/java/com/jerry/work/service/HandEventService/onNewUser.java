package com.jerry.work.service.HandEventService;

import com.jerry.work.excrption.EventException;
import com.jerry.work.mapper.TmpMapper;
import com.jerry.work.util.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 向博文 on 2017/8/30.
 */
public class onNewUser implements EventHandleInterface{

    private Logger logger = Logger.getLogger(onNewUser.class);

    public String handEvent(String event,String trigger,String data) throws Exception{

        return "";
    }
}
