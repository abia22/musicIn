package com.example.musicin.data;

public class Request {
    String photo;
    String request_text;

    public Request(String photo, String request_text) {
        this.photo = photo;
        this.request_text = request_text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRequest_text() {
        return request_text;
    }

    public void setRequest_text(String request_text) {
        this.request_text = request_text;
    }
}
