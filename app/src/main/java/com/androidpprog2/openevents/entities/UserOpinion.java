package com.androidpprog2.openevents.entities;

public class UserOpinion {
    private String image;
    private String name;
    private String email;
    private float rating;
    private String comment;
    private Event event;

    public UserOpinion(String image, String name, String email, float rating, String comment, Event event) {
        this.image = image;
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.comment = comment;
        this.event = event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}