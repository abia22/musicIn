package com.example.musicin.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BandMember implements Parcelable {
    private String name;
    private String instrument;
    private String photo;
    private String email;
    public BandMember(String name, String instrument, String photo,String email) {
        this.name = name;
        this.instrument = instrument;
        this.photo = photo;
        this.email = email;
    }

    protected BandMember(Parcel in) {
        name = in.readString();
        instrument = in.readString();
        photo = in.readString();
        email = in.readString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final Creator<BandMember> CREATOR = new Creator<BandMember>() {
        @Override
        public BandMember createFromParcel(Parcel in) {
            return new BandMember(in);
        }

        @Override
        public BandMember[] newArray(int size) {
            return new BandMember[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(instrument);
        parcel.writeString(photo);
        parcel.writeString(email);
    }
}
