package com.example.musicin.data;

public class Event {
    private String photo;
    private String name;
    private String location;
    private String date;

    public Event(String photo, String name, String location, String date) {
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
