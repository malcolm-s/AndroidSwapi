package com.malcolmstone.androidswapi;

import com.google.gson.annotations.SerializedName;

public class Person {
    private String name;
    private String gender;
    private String mass;
    private String height;
    @SerializedName("hair_color")
    private String hairColor;

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getMass() {
        return mass;
    }

    public String getHeight() {
        return height;
    }

    public String getHairColor() {
        return hairColor;
    }
}
