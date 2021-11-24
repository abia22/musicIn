package com.example.musicin.data;

public class BandMember {
    private String name;
    private String instrument;
    private byte[] photo;

    public BandMember(String name, String instrument, byte[] photo) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
