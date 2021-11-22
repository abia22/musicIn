package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MusicianEmailPwdSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_email_pwd_sign_in);

        TextInputEditText email = findViewById(R.id.email_ed_txt);
        TextInputEditText password = findViewById(R.id.password_ed_txt);
        TextInputEditText passwordConfirmed = findViewById(R.id.password_ed_txt3);

        MaterialButton login = findViewById(R.id.login_bttn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent());
            }
        });
    }
}