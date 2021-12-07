package com.example.musicin.data;

import java.util.List;

public class BandRequest {

    private List<String> instruments;
    private String genre;
    private List<BandMember> members;
    private String name;
    private String photo;

    public BandRequest(List<String> instruments, String genre, List<BandMember> members, String name, String photo) {
        this.genre = genre;
        this.members = members;
        this.instruments = instruments;
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<String> instruments) {
        this.instruments = instruments;
    }

    public List<BandMember> getMembers() {
        return members;
    }

    public void setMembers(List<BandMember> members) {
        this.members = members;
    }

}
