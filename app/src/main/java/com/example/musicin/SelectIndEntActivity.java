package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class SelectIndEntActivity extends AppCompatActivity {

    final int INDIVIDUAL = 0;
    final int ENTERPRISE = 1;
    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ind_ent);

        ImageView musician = findViewById(R.id.musician_img);
        ImageView enterprise = findViewById(R.id.enterprise_img);
        MaterialButton next_bttn = findViewById(R.id.next_bttn);

        musician.setBackgroundResource(0);
        enterprise.setBackgroundResource(0);
        next_bttn.setEnabled(false);


        musician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (selected == INDIVIDUAL){
                   musician.setBackgroundResource(0);
                   next_bttn.setEnabled(false);
                   selected = -1;
               } else {
                   musician.setBackgroundResource(R.drawable.border);
                   enterprise.setBackgroundResource(0);
                   selected = INDIVIDUAL;
                   next_bttn.setEnabled(true);
               }

            }
        });

        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected == ENTERPRISE){
                    enterprise.setBackgroundResource(0);
                    next_bttn.setEnabled(false);
                    selected = -1;
                }else{
                    enterprise.setBackgroundResource(R.drawable.border);
                    musician.setBackgroundResource(0);
                    selected = ENTERPRISE;
                    next_bttn.setEnabled(true);
                }

            }
        });

        next_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected == INDIVIDUAL){
                    startActivity(new Intent(SelectIndEntActivity.this, MusicianSignInActivity.class));
                }
                //TODO: GO TO ENTERPRISE ACTIVITY
            }
        });

    }
}