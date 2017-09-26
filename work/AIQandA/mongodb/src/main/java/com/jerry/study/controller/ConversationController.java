package com.jerry.study.controller;

import com.jerry.study.dao.ConversationRepository;
import com.jerry.study.entity.Conversation;
import com.jerry.study.util.Decoder;
import com.jerry.study.util.HttpRequest;
import com.jerry.study.util.SendResult;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static com.jerry.study.util.SendResult.*;

/**
 * Created by 向博文 on 2017/8/1.
 */
@RestController
public class ConversationController {

    @Value("#{url}")
    private String url;

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

//     在Java类中创建 logger 实例
    private Logger logger = LoggerFactory.getLogger(this.getClass());





    @PostMapping("conversation")
    public String save(HttpServletRequest request,Conversation conversation){
        logger.info(getIpAddress(request));
        logger.info(conversation.toString());
        if("JiaYiDuiHuaBaoCun".equals(conversation.getEncryptionStr())){
            try {
                conversationRepository.save(conversation);
            }catch (Exception e){
                logger.error("数据保存失败");
                return "数据保存失败";
            }
        }else {
            logger.info("加密码错误");
            return "加密码错误";
        }
        handConversation(conversation);
        return "保存成功";
    }

    @GetMapping("conversation")
    public String getConversationByFromIdAndToIdAndTimeInBeginAndEnd(Integer begin,Integer end,String fromId,String toId){
        BasicDBObject timestampGt = new BasicDBObject("timestamp",new BasicDBObject("$gt", begin));
        BasicDBObject timestampLt = new BasicDBObject("timestamp", new BasicDBObject("$lt", end));
        BasicDBObject fromIdObject = new BasicDBObject("fromId", fromId);
        BasicDBObject toIdObject = new BasicDBObject("toId", toId);
        BasicDBList andList = new BasicDBList();
        andList.add(timestampGt);
        andList.add(timestampLt);
        andList.add(fromIdObject);
        andList.add(toIdObject);
        BasicDBObject queryCondition = new BasicDBObject("$and",andList);
        List<Conversation> conversations = mongoTemplate.find(new BasicQuery(queryCondition), Conversation.class);
        StringBuffer result = new StringBuffer("");
//        Gson gson = new Gson();
//        String s = gson.toJson(conversations);
        return "";
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 处理对话是否需要与机器人对话
     * @param conversation
     * @return
     */
    public String handConversation(Conversation conversation){
        String type = conversation.getType();
        String to = conversation.getTo();
        String result = "";
        if(!conversation.getFrom().equals(to)) {
            if ("friend".equals(type) && "888888888".equals(to)) {//当直接发消息给888888888即发给机器人时
                result = sendMsg(conversation);
                JSONObject object = JSONObject.fromObject(result);
                if ("1".equals(object.getString("code"))) {
                    appadd("888888888", conversation.getFrom(), object.getString("answer"), "friend");
                } else {
                    appadd("888888888", conversation.getFrom(), "这个问题太难，我不知道怎样回答您，换个问题吧。", "friend");
                }
            } else if ("group".equals(type)) { // 当是在群里@机器人时
                String data = getData(conversation);
//            if ()
            }
        }
        return  result;
    }

    /**
     * 发送对话请求
     * @param conversation
     * @return
     */
    public String sendMsg(Conversation conversation){
        String data = getData(conversation);
        String result = "";
        if(!"".equals(data)) {
            result = HttpRequest.sendPost(url, "jsonstr={\n" +
                    "                    \"questionId\": \"111\",\n" +
                    "                    \"question\": \"\",\n" +
                    "                    \"answer\": {\n" +
                    "                        \"askId\": \"\",\n" +
                    "                        \"script\": \"\",\n" +
                    "                        \"value\": \"" + data + "\"\n" +
                    "                    },\n" +
                    "                    \"clientId\": \"h5\",\n" +
                    "                    \"deviceId\": \"\",\n" +
                    "                    \"userId\": \""+conversation.getFrom()+"\",\n" +
                    "                    \"longitude\": \"\",\n" +
                    "                    \"latitude\": \"\",\n" +
                    "                    \"userType\": \""+conversation.getUserType()+"\",\n" +
                    "                    \"userName\":\"\",\n" +
                    "                    \"userTitle\":\"\"\n" +
                    "            }");
        }
        return result;
    }

    /**
     * 获取参数中的具体对话
     * @param conversation
     * @return
     */
    public String getData(Conversation conversation){
        String info = conversation.getInfo();
        String[] split = info.split(",");
        String data = "";
        for (String s:split) {
            if (s.contains("content")){
                if(s.split(":").length > 1){
                    data = s.split(":")[1];
                    data = data.substring(1,data.length()-1);
                    data = Decoder.decode(data);
                }
                break;
            }
        }
        return data;
    }

}
