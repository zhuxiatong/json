package com.api.json.entry;

public class ZXT {
    private String name;
    private String sex;
    private int age;
    private String jianjie;

    public ZXT(String name, String sex, int age, String jianjie) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.jianjie = jianjie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }
}
