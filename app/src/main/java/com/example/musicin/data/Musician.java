package com.example.musicin.data;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Musician extends User {
    private String name;
    private String instruments;
    private String bday;
    private String favGenres;
    private String favArtists;
    private ArrayList<Band> bands;

    public Musician(String name, String instruments, String bday, String favGenres, String favArtists, String email, String password) {
        super(email, password);
        this.name = name;
        this.instruments = instruments;
        this.bday = bday;
        this.favGenres = favGenres;
        this.favArtists = favArtists;
        bands =  new ArrayList<>();
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruments() {
        return instruments;
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getFavGenres() {
        return favGenres;
    }

    public void setFavGenres(String favGenres) {
        this.favGenres = favGenres;
    }

    public String getFavArtists() {
        return favArtists;
    }

    public void setFavArtists(String favArtists) {
        this.favArtists = favArtists;
    }

    public ArrayList<Band> getBands() {
        return bands;
    }

    public void setBands(ArrayList<Band> bands) {
        this.bands = bands;
    }

    public void addBand(Band band){
        bands.add(band);
    }


}
