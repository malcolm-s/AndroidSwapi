package com.malcolmstone.androidswapi;

public class Person {
    private String name;
    private String age;
    private String gender;

    Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
