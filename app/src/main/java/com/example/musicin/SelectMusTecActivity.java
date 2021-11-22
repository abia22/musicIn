package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class SelectMusTecActivity extends AppCompatActivity {

    final int MUSICIAN = 0;
    final int TECHNICIAN = 1;
    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mus_tec);

        ImageView musician = findViewById(R.id.musician_img);
        ImageView technician = findViewById(R.id.technician_img);
        MaterialButton next_bttn = findViewById(R.id.next_bttn2);

        musician.setBackgroundResource(0);
        technician.setBackgroundResource(0);
        next_bttn.setEnabled(false);


        musician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected == MUSICIAN){
                    musician.setBackgroundResource(0);
                    next_bttn.setEnabled(false);
                    selected = -1;
                } else {
                    musician.setBackgroundResource(R.drawable.border);
                    technician.setBackgroundResource(0);
                    selected = MUSICIAN;
                    next_bttn.setEnabled(true);
                }

            }
        });

        technician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected == TECHNICIAN){
                    technician.setBackgroundResource(0);
                    next_bttn.setEnabled(false);
                    selected = -1;
                }else{
                    technician.setBackgroundResource(R.drawable.border);
                    musician.setBackgroundResource(0);
                    selected = TECHNICIAN;
                    next_bttn.setEnabled(true);
                }

            }
        });

        next_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected == MUSICIAN){
                    startActivity(new Intent(SelectMusTecActivity.this, MusicianSignInActivity.class));
                }

            }
        });
    }
}