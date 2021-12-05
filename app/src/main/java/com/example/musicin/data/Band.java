package com.example.musicin.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Band implements Parcelable {
    private String name;
    private List<BandMember> members;

    public Band(String name, List<BandMember> members) {
        this.name = name;
        this.members = members;
    }

    protected Band(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Band> CREATOR = new Creator<Band>() {
        @Override
        public Band createFromParcel(Parcel in) {
            return new Band(in);
        }

        @Override
        public Band[] newArray(int size) {
            return new Band[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BandMember> getMembers() {
        return members;
    }

    public void setMembers(List<BandMember> members) {
        this.members = members;
    }

    public void addMember(BandMember member) {
        members.add(member);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
