package com.androidpprog2.openevents.entities;

public class FriendRequest {
    private String name;
    private String image;
    private int id;

    public FriendRequest(String name, String image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
