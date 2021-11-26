package com.example.musicin.data;

public class BandMember {
    private String name;
    private String instrument;
    private String photo;

    public BandMember(String name, String instrument, String photo) {
        this.name = name;
        this.instrument = instrument;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
