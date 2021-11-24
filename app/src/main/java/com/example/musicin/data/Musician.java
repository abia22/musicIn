package com.example.musicin.data;

import android.os.Parcelable;
import android.os.Parcel;

public class Musician extends User implements Parcelable {
    private String name;
    private String instruments;
    private String bday;
    private String favGenres;
    private String favArtists;

    public Musician(String name, String instruments, String bday, String favGenres, String favArtists, String email, String password) {
        super(email, password);
        this.name = name;
        this.instruments = instruments;
        this.bday = bday;
        this.favGenres = favGenres;
        this.favArtists = favArtists;
    }

    protected Musician(Parcel in) {
        super(in);
        name = in.readString();
        instruments = in.readString();
        bday = in.readString();
        favGenres = in.readString();
        favArtists = in.readString();
    }

    public static final Creator<Musician> CREATOR = new Creator<Musician>() {
        @Override
        public Musician createFromParcel(Parcel in) {
            return new Musician(in);
        }

        @Override
        public Musician[] newArray(int size) {
            return new Musician[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(name);
        parcel.writeString(instruments);
        parcel.writeString(bday);
        parcel.writeString(favGenres);
        parcel.writeString(favArtists);
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
}
