package com.example.musicin.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String photo;
    private String name;
    private String location;
    private String date;
    private int payment;
    private String genre;
    private String info;
    private String contact;
    private int distance;

    public Event(String photo, String name, String location, String date) {
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.date = date;
    }


    protected Event(Parcel in) {
        photo = in.readString();
        name = in.readString();
        location = in.readString();
        date = in.readString();
        payment = in.readInt();
        genre = in.readString();
        info = in.readString();
        contact = in.readString();
        distance = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Event(String photo, String name, String location, String date, int payment, String genre, String info, String contact, int distance) {
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.date = date;
        this.payment = payment;
        this.genre = genre;
        this.info = info;
        this.contact = contact;
        this.distance = distance;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(photo);
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeString(date);
        parcel.writeInt(payment);
        parcel.writeString(genre);
        parcel.writeString(info);
        parcel.writeString(contact);
        parcel.writeInt(distance);
    }
}
