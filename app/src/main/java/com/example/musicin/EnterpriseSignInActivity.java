package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EnterpriseSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_sign_in);

        TextInputEditText name = findViewById(R.id.name_ed_txt);
        TextInputEditText nif = findViewById(R.id.nif_ed_txt);
        TextInputEditText address = findViewById(R.id.address_ed_txt);

        MaterialButton next = findViewById(R.id.next_bttn_sign_in2);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterpriseSignInActivity.this,MusicianEmailPwdSignInActivity.class));
            }
        });
    }
}