package com.example.huni.walletmanager.NavigationDrawerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huni.walletmanager.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class user_detailsActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textViewEmail, textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewEmail= (TextView)findViewById(R.id.get_emailTextView);
        textViewEmail.setText("e-mail: " + user.getEmail());


    }
}
