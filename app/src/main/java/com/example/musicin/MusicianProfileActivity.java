package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicin.data.Data;
import com.example.musicin.data.Musician;
import com.example.musicin.data.MusicianProfile;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class MusicianProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_profile);

        Data data = new Data();

        String email = getIntent().getStringExtra("email");
        Musician musician = data.getMusician(email);
        MusicianProfile musicianProfile = data.getMusicianProfile(email);

        TextView name = findViewById(R.id.person_name_txt);
        TextView age = findViewById(R.id.person_age_txt);
        TextView instrument = findViewById(R.id.person_instrument_txt);
        TextView years_of_playing = findViewById(R.id.years_of_playing_txt);
        TextView places_played = findViewById(R.id.places_played_txt);
        TextView school_background = findViewById(R.id.school_background_txt);
        ImageView photo = findViewById(R.id.person_photo_iv);
        MaterialButton socialMedia_btn = findViewById(R.id.social_media_btn);
        MaterialButton accept_btn = findViewById(R.id.accept_btn);
        MaterialButton decline_btn = findViewById(R.id.decline_btn);

        name.setText(musician.getName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(musician.getBday());
            Instant instant = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                instant = date.toInstant();
                ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
                LocalDate givenDate = zone.toLocalDate();
                Period period = Period.between(givenDate, LocalDate.now());
                age.setText(period.getYears() + " years old");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instrument.setText(musician.getInstruments());
        years_of_playing.setText("I have played my instrument for " + musicianProfile.getYearsOfPlaying() + " years");
        places_played.setText("I have played at " + musicianProfile.getPlacesPlayedIn());
        school_background.setText("I have studied at " + musicianProfile.getMusicSchoolBackground());
        Picasso.get().load(musicianProfile.getPhoto()).into(photo);

        socialMedia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(musicianProfile.getSocialMedia()));
                startActivity(browserIntent);
            }
        });


    }
}