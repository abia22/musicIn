package com.example.musicin.data;

import java.util.List;

public class BandRequest {

    private List<String> instruments;
    private String genre;
    private List<BandMember> members;

    public BandRequest(List<String> instruments, String genre, List<BandMember> members){
        this.genre = genre;
        this.members = members;
        this.instruments = instruments;
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
