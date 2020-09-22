package com.example.vo;

public class Person {
    private int sid,id,age;
    private String name;
    private int c_goal,sql_goal,java_goal;

    public Person() {
    }

    public Person(int sid, int id, int age, String name) {
        this.sid = sid;
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getC_goal() {
        return c_goal;
    }

    public void setC_goal(int c_goal) {
        this.c_goal = c_goal;
    }

    public int getSql_goal() {
        return sql_goal;
    }

    public void setSql_goal(int sql_goal) {
        this.sql_goal = sql_goal;
    }

    public int getJava_goal() {
        return java_goal;
    }

    public void setJava_goal(int java_goal) {
        this.java_goal = java_goal;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sid=" + sid +
                ", id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", c_goal=" + c_goal +
                ", sql_goal=" + sql_goal +
                ", java_goal=" + java_goal +
                '}';
    }
}
