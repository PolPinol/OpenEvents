package com.androidpprog2.openevents.entities;

public class UserOpinion {
    private String name;
    private String surname;
    private String email;
    private String rating;
    private String comment;

    public UserOpinion(String name, String surname, String email, String rating, String comment) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.rating = rating;
        this.comment = comment;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}