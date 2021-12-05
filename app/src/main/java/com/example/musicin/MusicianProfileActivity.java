package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.musicin.data.Data;
import com.example.musicin.data.Musician;
import com.example.musicin.data.MusicianProfile;

public class MusicianProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_profile);

        Data data = new Data();

        String email = getIntent().getStringExtra("email");
        Musician musician = data.getMusician(email);
        MusicianProfile musicianProfile = data.getMusicianProfile(email);



    }
}