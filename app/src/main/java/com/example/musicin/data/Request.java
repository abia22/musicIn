package com.example.musicin.data;

public class Request extends Notification{
    private String email;

    public Request(int photo, String request_text, String email) {
        super(photo, request_text);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
