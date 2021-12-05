package com.example.musicin.data;

public class Request {
    int photo;
    String request_text;

    public Request(int photo, String request_text) {
        this.photo = photo;
        this.request_text = request_text;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getRequest_text() {
        return request_text;
    }

    public void setRequest_text(String request_text) {
        this.request_text = request_text;
    }
}
