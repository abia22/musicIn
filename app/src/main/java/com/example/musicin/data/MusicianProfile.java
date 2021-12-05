package com.example.musicin.data;

public class MusicianProfile {
    private int yearsOfPlaying;
    private String placesPlayedIn;
    private String musicSchoolBackground;
    private String socialMedia;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public MusicianProfile(int yearsOfPlaying, String placesPlayedIn, String musicSchoolBackground, String socialMedia, String photo) {
        this.yearsOfPlaying = yearsOfPlaying;
        this.placesPlayedIn = placesPlayedIn;
        this.musicSchoolBackground = musicSchoolBackground;
        this.socialMedia = socialMedia;
        this.photo = photo;
    }

    public int getYearsOfPlaying() {
        return yearsOfPlaying;
    }

    public void setYearsOfPlaying(int yearsOfPlaying) {
        this.yearsOfPlaying = yearsOfPlaying;
    }

    public String getPlacesPlayedIn() {
        return placesPlayedIn;
    }

    public void setPlacesPlayedIn(String placesPlayedIn) {
        this.placesPlayedIn = placesPlayedIn;
    }

    public String getMusicSchoolBackground() {
        return musicSchoolBackground;
    }

    public void setMusicSchoolBackground(String musicSchoolBackground) {
        this.musicSchoolBackground = musicSchoolBackground;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }
}
