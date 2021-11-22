package com.example.musicin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class LoginActivity extends AppCompatActivity {



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

        TextInputEditText email = findViewById(R.id.email_ed_txt);
        TextInputEditText password = findViewById(R.id.password_ed_txt);
        MaterialButton login_bttn = findViewById(R.id.login_bttn);

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //if (textView.getText().length() > 0)
                return false;
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
                }
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

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}