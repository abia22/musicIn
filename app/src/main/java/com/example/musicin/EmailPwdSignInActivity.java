package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EmailPwdSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_pwd_sign_up);

        TextInputEditText email = findViewById(R.id.email_ed_txt);
        TextInputEditText password = findViewById(R.id.password_ed_txt);
        TextInputEditText passwordConfirmed = findViewById(R.id.password_ed_txt3);

        MaterialButton signup_bttn = findViewById(R.id.sign_up_bttn);


        signup_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailPwdSignInActivity.this, MusicianSignInActivity.class));
            }
        });
    }
}