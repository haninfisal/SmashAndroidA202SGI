package com.example.smash.bookingApp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class homeActivity extends AppCompatActivity {
    TextView textView;
    Button logoutBtn, viewBtn, viewSummaryBtn;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Home");


        viewBtn = findViewById(R.id.view);
        viewSummaryBtn = findViewById(R.id.viewSumBtn);
        logoutBtn = findViewById(R.id.logoutButton);

        // view badminton court list
        viewBtn.setOnClickListener(v -> {
            Intent intToBook = new Intent(homeActivity.this, listActivity.class);
            startActivity(intToBook);
        });

        // log out button
        logoutBtn.setOnClickListener(v -> {
            mAuth.getInstance().signOut();
            Intent intToMain = new Intent(homeActivity.this, MainActivity.class);
            startActivity(intToMain);

        });

        // View summary button
        viewSummaryBtn.setOnClickListener(v -> {
            Intent intToSummary = new Intent(homeActivity.this, summaryActivity.class);
            startActivity(intToSummary);

        });

    }
}