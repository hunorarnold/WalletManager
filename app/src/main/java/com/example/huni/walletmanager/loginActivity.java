package com.example.huni.walletmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton, registrationButton,forgotPasswordButton;
    private EditText editTextEmail;
    private TextInputEditText editTextPassword;

    private FirebaseAuth firebaseAuth;

    private Intent goToMain,intentRegistration,intentForgotPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goToMain = new Intent(loginActivity.this, MainActivity.class);
        intentRegistration = new Intent(loginActivity.this, registrationActivity.class);
        intentForgotPassword = new Intent(loginActivity.this, forgotten_passwordActivity.class);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(goToMain);
        }

        registrationButton = (Button)findViewById(R.id.registration_Button);
        loginButton = (Button)findViewById(R.id.login_Button);
        editTextEmail = (EditText) findViewById(R.id.email_editText);
        editTextPassword = (TextInputEditText)findViewById(R.id.password_TextInputEdit);
        forgotPasswordButton = (Button)findViewById(R.id.forgotten_passwordButton);


        loginButton.setOnClickListener(this);
        registrationButton.setOnClickListener(this);
        forgotPasswordButton.setOnClickListener(this);


    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your e-mail!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter a password!",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "Log in successful!",Toast.LENGTH_LONG).show();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(firebaseAuth.getCurrentUser() != null){
                                finish();
                                startActivity(goToMain);
                            }
                        } else {
                            Toast.makeText(loginActivity.this, "Log in fail!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == loginButton){
           userLogin();
        }
        if(view == registrationButton){
            startActivity(intentRegistration);
        }
        if(view == forgotPasswordButton){
            startActivity(intentForgotPassword);
        }

    }
}
