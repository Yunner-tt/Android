package com.example.vo;

import java.io.Serializable;

public class Employ implements Serializable {
    private String name, profess,sex;
    private int age;

    public Employ(){
        //
    }

    public Employ(String name, String profess, String sex, int age) {
        this.name = name;
        this.profess = profess;
        this.sex = sex;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfess() {
        return profess;
    }

    public void setProfess(String profess) {
        this.profess = profess;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Employ{" +
                "name='" + name + '\'' +
                ", profess='" + profess + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
