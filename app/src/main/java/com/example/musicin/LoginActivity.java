package com.example.musicin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicin.data.Data;
import com.example.musicin.data.Musician;
import com.example.musicin.data.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputEditText password;
    MaterialButton login_bttn;
    ConstraintLayout layout;
    Data data;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        data = new Data();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

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
        layout = findViewById(R.id.login_layout);

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
                UIUtil.hideKeyboard(LoginActivity.this);
                if (!isPasswordValid(password.getText())) {
                    password.setError(getString(R.string.error_password));
                } else {
                    password.setError(null); // Clear the error
                    String email_txt = email.getText().toString();
                    String pass_txt = password.getText().toString();
                    if (verifyLogin(email_txt, pass_txt)){

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL,email_txt);
                        editor.apply();

                        Musician user = data.getMusician(email_txt);
                        CharSequence text = "Welcome back " + user.getName();
                        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(LoginActivity.this, MusicianHubActivity.class);
                        intent.putExtra("user", user);
                        finishAffinity();
                        startActivity(intent);
                    }

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

    private boolean verifyLogin(String email_txt, String pass_txt) {
        if (data.checkIfUserExists(email_txt)){
            if (data.verifyLogin(email_txt, pass_txt)){
                return true;
            } else{
                Snackbar.make(layout, R.string.login_error, Snackbar.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Snackbar.make(layout, R.string.no_user_error, Snackbar.LENGTH_SHORT).show();
            return false;
        }
    }

    private void enableLoginIfReady() {
        boolean isReady = true;
        if(email.getText().length() == 0 || password.getText().length() == 0)
            isReady = false;
        login_bttn.setEnabled(isReady);
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 5;
    }
}