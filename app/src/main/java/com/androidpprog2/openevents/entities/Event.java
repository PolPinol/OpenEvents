package com.androidpprog2.openevents.entities;

public class Event {
    private String name;
    private String image;
    private String location;
    private String description;
    private String startDate;
    private String endDate;
    private String numPart;
    private String type;
    private int id;
    private int ownerId;

    public Event(String name, String image, String location, String description, String startDate,
                 String endDate, String numPart, String type) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numPart = numPart;
        this.type = type;
    }

    public Event(String name, String image, String location, String description, String startDate,
                 String endDate, String numPart, String type, int id, int ownerId) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numPart = numPart;
        this.type = type;
        this.id = id;
        this.ownerId = ownerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setNumPart(String numPart) {
        this.numPart = numPart;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNumPart() {
        return numPart;
    }

    public String getType() {
        return type;
    }
}
