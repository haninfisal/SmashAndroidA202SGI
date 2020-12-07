package com.example.smash.bookingApp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    TextView textView;
    EditText mEmail, mPassword;
    Button loginBtn;
    TextView tvRegister;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Log In");

        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginButton);
        tvRegister = findViewById(R.id.textViewRegister);


        // Login Authentication
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(loginActivity.this, "Welcome to Smash!", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(loginActivity.this, homeActivity.class);
                    startActivity(home);
                } else {
                    Toast.makeText(loginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();

                }

            }
        };

        loginBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString();
            String pass = mPassword.getText().toString();

            //Log in Validation
            if (email.isEmpty()) {
                mEmail.setError("Email is Required");
                mEmail.requestFocus();
            } else if (pass.isEmpty()) {
                mPassword.setError("Please enter your password");
                mPassword.requestFocus();
            } else if (email.isEmpty() && pass.isEmpty()) {
                Toast.makeText(loginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
            } else if (!(email.isEmpty() && pass.isEmpty())) {
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Login Error, Try Again", Toast.LENGTH_SHORT).show();

                        } else {
                            Intent intToHome = new Intent(loginActivity.this, homeActivity.class);
                            startActivity(intToHome);
                        }
                    }
                });
            } else {
                Toast.makeText(loginActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        // click to register
        tvRegister.setOnClickListener(v -> {
            Intent intRegister = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intRegister);

        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}