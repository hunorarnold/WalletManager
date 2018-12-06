package com.example.huni.walletmanager;

import android.app.ProgressDialog;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registrationButton;
    private TextInputEditText editTextNickname;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextPasswordconfirm;
    private TextView textViewSingup;

    //private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        firebaseAuth = FirebaseAuth.getInstance();

        //progressDialog = new ProgressDialog(this);

        registrationButton = (Button)findViewById(R.id.registrationButton);
        editTextNickname = (TextInputEditText)findViewById(R.id.nickNameEditText);
        editTextEmail = (TextInputEditText) findViewById(R.id.emailEditText);
        editTextPassword = (TextInputEditText)findViewById(R.id.passwordEditText);
        editTextPasswordconfirm = (TextInputEditText)findViewById(R.id.confirmPasswordEditText);

        registrationButton.setOnClickListener(this);

    }

    private void registerUser(){
        String nickname = editTextNickname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmpassword = editTextPasswordconfirm.getText().toString().trim();

        if(TextUtils.isEmpty(nickname)){
            Toast.makeText(this,"Please enter a nickname!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your e-mail!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter a password!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(confirmpassword)){
            Toast.makeText(this,"Please confirm your password!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.equals(password,confirmpassword)){
            Toast.makeText(this,"Thwo password is not same!",Toast.LENGTH_LONG).show();
            return;
        }

        //progressDialog.setMessage("Registration...!");
        //progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registrationActivity.this, "Registered succesfull!",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(registrationActivity.this, "Registration fail!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == registrationButton) {
            registerUser();
        }
    }
}
