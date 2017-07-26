package com.jerry.gradle.todo;

import java.util.Scanner;

/**
 * Created by acer on 2017/7/23.
 */
public class App {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (++i > 0){
            System.out.println(i + ".please input your item");
            TodoItem todoItem = new TodoItem(scanner.nextLine());
            System.out.println(todoItem);
        }
    }

}
