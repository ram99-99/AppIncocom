package com.example.fininfocom;

public class MyDataModel {
    private String name;
    private int age;
    private String city;

    public MyDataModel(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
