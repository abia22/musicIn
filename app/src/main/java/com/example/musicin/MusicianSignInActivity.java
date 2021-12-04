package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

public class MusicianSignInActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_sign_up);

        TextInputEditText name = findViewById(R.id.name_ed_txt);
        TextInputEditText instruments = findViewById(R.id.instrument_ed_txt);
        TextInputEditText favouriteGenres = findViewById(R.id.genre_ed_txt);
        TextInputEditText favouriteArtists = findViewById(R.id.favourite_artists_ed_txt);
        TextView bday = findViewById(R.id.bday_txt);
        MaterialButton next = findViewById(R.id.next_bttn_sign_in);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MusicianSignInActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                String currentDateString = DateFormat.getDateInstance().format(c.getTime());
                bday.setText(currentDateString);
            }
        };

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MusicianSignInActivity.this, EmailPwdSignInActivity.class));
                name.getText().toString();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

    }
}