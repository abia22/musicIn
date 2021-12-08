package com.example.musicin.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BandRequest implements Parcelable {

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

    protected BandRequest(Parcel in) {
        instruments = in.createStringArrayList();
        genre = in.readString();
        members = in.createTypedArrayList(BandMember.CREATOR);
        name = in.readString();
        photo = in.readString();
    }

    public static final Creator<BandRequest> CREATOR = new Creator<BandRequest>() {
        @Override
        public BandRequest createFromParcel(Parcel in) {
            return new BandRequest(in);
        }

        @Override
        public BandRequest[] newArray(int size) {
            return new BandRequest[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(instruments);
        dest.writeString(genre);
        dest.writeTypedList(members);
        dest.writeString(name);
        dest.writeString(photo);
    }
}
