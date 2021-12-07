package com.example.musicin.data;

public class Request extends Notification{
    private String email;
    private String bandToJoin;

    public Request(int photo, String request_text, String email, String bandToJoin) {
        super(photo, request_text);
        this.email = email;
        this.bandToJoin = bandToJoin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBandToJoin() {
        return bandToJoin;
    }

    public void setBandToJoin(String bandToJoin) {
        this.bandToJoin = bandToJoin;
    }
}
