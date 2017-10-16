package com.skch.test;

import java.util.List;
import redis.clients.jedis.Jedis;
 
public class TestRedis {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //存储数据到列表中
//        jedis.lpush("site-list", "daoyi.xml,id003");
//        jedis.lpush("site-list", "daoyi.xml,id00");
//        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
        String lpop = jedis.lpop("site-list");
        System.out.println(lpop);
    }
}