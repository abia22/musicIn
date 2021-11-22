package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MusicianSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_sign_in);

        TextInputEditText name = findViewById(R.id.name_ed_txt);
        TextInputEditText instruments = findViewById(R.id.instrument_ed_txt);
        TextInputEditText age = findViewById(R.id.age_ed_txt);
        TextInputEditText favouriteGenres = findViewById(R.id.genre_ed_txt);
        TextInputEditText favouriteArtists = findViewById(R.id.favourite_artists_ed_txt);
        MaterialButton next = findViewById(R.id.next_bttn_sign_in);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MusicianSignInActivity.this,MusicianEmailPwdSignInActivity.class));
                name.getText().toString();
            }
        });

    }

}