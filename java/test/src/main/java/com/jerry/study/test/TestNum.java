package com.jerry.study.test;

/**
 * Created by 向博文 on 2017/10/10.
 */
public class TestNum {
    public static void main(String[] args){
        for (int k = 0;k<100;k++) {
            long start = System.currentTimeMillis();
            for (int i = 1; i < 100000; i++) {
                int sum = 1;
                for (int j = 1; j <= i; j++) {
                    sum *= j;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
}
