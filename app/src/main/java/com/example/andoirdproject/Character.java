package com.example.andoirdproject;

import com.google.gson.annotations.SerializedName;

public class Character {

    @SerializedName("name")
    private String name;

    @SerializedName("imageUrl")
    private String imageUrl;

    public Character(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
