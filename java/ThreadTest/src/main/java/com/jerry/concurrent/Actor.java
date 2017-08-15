package com.jerry.concurrent;

/**
 * Created by 向博文 on 2017/8/15.
 */
public class Actor extends Thread {
    @Override
    public void run() {
        System.out.println(getName()+"是一个演员！");
        int count = 0;
        System.out.println(getName()+"登台演出"+(count++));
        System.out.println(getName()+"演出结束！");
    }

    public static void main(String[] args){

    }
}
