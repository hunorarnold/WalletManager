package com.example.huni.walletmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginActivity extends AppCompatActivity {

    private Intent intentLogin, intentRegistration, intentForgottenPassword;
    private Button buttonLogin, buttonRegistration, buttonForgottenPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intentLogin = new Intent(loginActivity.this, MainActivity.class);
        intentRegistration = new Intent(loginActivity.this, registrationActivity.class);
        intentForgottenPassword = new Intent(loginActivity.this, forgotten_passwordActivity.class);

        buttonLogin = findViewById(R.id.login_Button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentLogin);

            }
        });

        buttonRegistration = findViewById(R.id.registration_Button);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentRegistration);

            }
        });

        buttonForgottenPassword = findViewById(R.id.forgotten_passwordButton);

        buttonForgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentForgottenPassword);

            }
        });
    }
}
