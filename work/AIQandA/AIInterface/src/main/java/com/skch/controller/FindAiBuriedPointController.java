package com.skch.controller;

import org.springframework.stereotype.Controller;

@Controller
public class FindAiBuriedPointController {

//    @Autowired
//    private T_variableRepository t_variableRepository;
//
//    @Autowired
//    private T_buriedPointRepository t_buriedPointRepository;
//
//    @Autowired
//    private RedisTemplate<String,String > redisTemplate;
//
//    @ResponseBody
//    @RequestMapping("/findAiBuriedPoint")
//    public Object findAiBuriedPoint(String userId,String question,String noPointId){
//        redisTemplate.delete(userId + "aiAnswer");
//        String url = "http://47.93.160.47:8868/nlu";
//        String returnData = "";
//        try {
//            question = URLEncoder.encode(question,"utf-8");
//            String param = "AppID=0&DeviceID=868331011992179&Timestamp=1474249988&UserID=636286295166029733&Longitude=&Latitude=&UserType=1&Question="+question+"&QuestionID=9527";
//            returnData = sendGet(url, param);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JSONObject fromObject = JSONObject.fromObject(returnData);
//        Object object = fromObject.get("answer");
//        if(returnData.contains("&")){
//            returnData = JSONObject.fromObject(object).values().toArray()[0].toString();
//            returnData = returnData.replaceAll("&","");
//            System.out.println(returnData);
//        } else {
//            Set<Entry<String , String >> entrySet = JSONObject.fromObject(object).entrySet();
//            for (Entry<String , String > entry : entrySet) {
//                redisTemplate.opsForList().leftPush(userId + "aiAnswer", entry.getKey() + ":" + entry.getValue());
//            }
//            System.out.println(returnData);
//            returnData = "{'list':[\"\","+ noPointId +"]}";
//        }
//        returnData = JSONObject.fromObject(returnData).toString();
//        return returnData;
//    }
//
//
//    @ResponseBody
//    @RequestMapping("/aiLastAnswer")
//    public Object aiLastAnswer(@RequestParam("userId") String userid, String endId, String nextId){
//
//        String rpop = redisTemplate.opsForList().rightPop(userid + "aiAnswer");
//        T_variable t_variable = new T_variable();
//        t_variable.setUserid(userid);
//        t_variable.setParamkey("aiNextAnswerId");
//        if (rpop != null && !"".equals(rpop)) {
//            t_variable.setParamvalue(nextId);
//        } else {
//            t_variable.setParamvalue(endId);
//        }
//        T_variable result = t_variableRepository.findByUseridAndParamkey(userid,"aiNextAnswerId");
//        if (result != null) {
//            t_variable.setId(result.getId());
//            t_variableRepository.save(t_variable);
//        }else {
//            t_variableRepository.save(t_variable);
//        }
//        if(rpop != null &&!"".equals(rpop)){
//            rpop = "{\"aiAnswer\":\"" + rpop + "\"}";
//        }
//        return rpop;
//    }

}
