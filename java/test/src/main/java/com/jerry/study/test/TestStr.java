package com.jerry.study.test;

import java.lang.reflect.Method;

/**
 * Created by 向博文 on 2017/10/9.
 */
public class TestStr {

    public static void main(String[] args){
        System.out.println("abcdefghijk".startsWith("def",3));
        test1();
        test2();
    }

    public static long getRunTime(String methodName){
        Class<?> clazz = null;
        long start = 0;
        long end = 0;
        try {
            clazz = Class.forName("com.jerry.study.test.TestStr");
            Method method = clazz.getDeclaredMethod("methodName");
            Object o = clazz.newInstance();
            start = System.currentTimeMillis();
            method.invoke(o);
            end = System.currentTimeMillis();
        } catch (Exception e) {

        }
        return end - start;
    }

    public static void test1(){
        long start = System.currentTimeMillis();
        for (int i = 0;i< 1000000;i++){
            StringBuilder sb = new StringBuilder();
            sb.append("select * from xxx where 1=1");
            sb.append("and id = ? and name = ?");
            sb.append("order by name desc");
            String sql = sb.toString();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void test2(){
        long start = System.currentTimeMillis();
        for (int i = 0;i< 1000000;i++){
            String s = "select * from xxx where 1=1" +  "and id = ? and name = ?"+ "order by name desc";
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
