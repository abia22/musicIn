package com.example.musicin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputEditText password;
    MaterialButton login_bttn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView sign_up_txt = findViewById(R.id.sing_up_txt);
        sign_up_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SelectIndEntActivity.class));
            }
        });

        email = findViewById(R.id.email_ed_txt);
        password = findViewById(R.id.password_ed_txt);
        login_bttn = findViewById(R.id.login_bttn);

        login_bttn.setEnabled(false);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                enableLoginIfReady();
            }
        });

        // Set an error if the password is less than 8 characters.
        login_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(password.getText())) {
                    password.setError(getString(R.string.error_password));
                } else {
                    password.setError(null); // Clear the error
                    Intent intent = new Intent(LoginActivity.this, MusicianHubActivity.class);
                    finishAffinity();
                    startActivity(intent);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                enableLoginIfReady();
            }
        });

        // Clear the error once more than 8 characters are typed.
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(password.getText())) {
                    password.setError(null); //Clear the error
                }
                return false;
            }
        });
    }

    private void enableLoginIfReady() {
        boolean isReady = true;
        if(email.getText().length() == 0 || password.getText().length() == 0)
            isReady = false;
        login_bttn.setEnabled(isReady);
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}