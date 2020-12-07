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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText mEmail, mPassword;
    Button registerBtn;
    TextView tvLogin;
    FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("Register");

        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        tvLogin = findViewById(R.id.textViewLogin);
        registerBtn = findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(v -> {

            String userEmail = mEmail.getText().toString();
            String userPass = mPassword.getText().toString();

            // User Register Validation
            if(userEmail.isEmpty()) {
                mEmail.setError("Email is Required");
                mEmail.requestFocus();
            }
            else if (userPass.isEmpty()) {
                mPassword.setError("Please enter your password");
                mPassword.requestFocus();
            }
            else if (userPass.length() < 6 ) {
                mPassword.setError("Password must be at least 8 characters");
                mPassword.requestFocus();
            }
            else if (userEmail.isEmpty() && userPass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
            }
            else if (!(userEmail.isEmpty() && userPass.isEmpty())) {
                mAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(MainActivity.this, task -> {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                    }
                    else if (task.isSuccessful()){
                        User user = new User(userEmail, userPass);

                        // Insert User registration data Into Database
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(MainActivity.this, loginActivity.class));
                                }
                            }
                        });

                    }
//                    else {
//                        startActivity(new Intent(MainActivity.this, loginActivity.class));
//                    }
                });
            }
            else {
                Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, loginActivity.class);
                startActivity(i);

            }
        });

    }
}