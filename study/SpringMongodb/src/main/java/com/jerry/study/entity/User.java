package com.jerry.study.entity;


import org.springframework.data.annotation.Id;

/**
 * Created by XX on 2017/7/26.
 */
public class User {
    //id属性是给mongodb用的，用@Id注解修饰
    @Id
    private static Integer id = 0;
    private String name;
    private int age;

    public User() {
        this.id = id++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(Integer id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}