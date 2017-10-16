package com.jerry.study.test;

/**
 * Created by 向博文 on 2017/10/10.
 */
public class TestNum {
    public static void main(String[] args){
        int a = 11;
        int b = 12;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a);
        System.out.println(b);
    }
}
