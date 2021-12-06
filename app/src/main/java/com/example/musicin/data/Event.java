package com.example.musicin.data;

public class Event {
    private String photo;
    private String name;
    private String location;
    private String date;
    private int payment;
    private String genre;
    private String info;
    private String contact;

    public Event(String photo, String name, String location, String date) {
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.date = date;
    }


    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Event(String photo, String name, String location, String date, int payment, String genre, String info, String contact) {
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.date = date;
        this.payment = payment;
        this.genre = genre;
        this.info = info;
        this.contact = contact;
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
