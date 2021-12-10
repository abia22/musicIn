package com.example.musicin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.musicin.data.Data;
import com.example.musicin.data.MusicianProfile;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileSettingsActivity extends AppCompatActivity {
    CircleImageView photo;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if(uri != null)
                        photo.setImageURI(uri);
                }
            });

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    mGetContent.launch("image/*");
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Permission is necessary in order to change the profile picture", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        String email = getIntent().getStringExtra("email");

        photo = findViewById(R.id.edit_photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    mGetContent.launch("image/*");
                } else {
                    requestPermissionLauncher.launch(
                            Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
        });

        TextInputEditText years_of_playing_ed_tx = findViewById(R.id.years_of_playing_ed_txt);
        TextInputEditText places_played_ed_txt = findViewById(R.id.places_played_ed_txt);
        TextInputEditText school_background_ed_txt = findViewById(R.id.school_background_ed_txt);
        TextInputEditText social_media_ed_txt = findViewById(R.id.social_media_ed_txt);
        MaterialButton save_btn = findViewById(R.id.save_btn);

        Data data = Data.getInstance();
        MusicianProfile oldMusicianProfile = data.getMusicianProfile(email);

        if(oldMusicianProfile != null) {
            if(oldMusicianProfile.getYearsOfPlaying() != 0)
                years_of_playing_ed_tx.setText(Integer.toString(oldMusicianProfile.getYearsOfPlaying()));

            if (oldMusicianProfile.getPlacesPlayedIn() != null)
                places_played_ed_txt.setText(oldMusicianProfile.getPlacesPlayedIn());

            if(oldMusicianProfile.getMusicSchoolBackground() != null)
                school_background_ed_txt.setText(oldMusicianProfile.getMusicSchoolBackground());

            if (oldMusicianProfile.getSocialMedia() != null)
                social_media_ed_txt.setText(oldMusicianProfile.getSocialMedia());
        }

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicianProfile musicianProfile = new MusicianProfile(Integer.parseInt(years_of_playing_ed_tx.getText().toString()),
                        places_played_ed_txt.getText().toString(), school_background_ed_txt.getText().toString(),
                        social_media_ed_txt.getText().toString(), "");
                data.setMusicianProfile(email, musicianProfile);
                finish();
            }
        });

    }

}