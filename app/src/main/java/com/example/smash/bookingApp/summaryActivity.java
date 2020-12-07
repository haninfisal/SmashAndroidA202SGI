package com.example.smash.bookingApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class summaryActivity extends AppCompatActivity {

        TextView court_view, name_view, contact_view, date_view, time_view, hour_view;

        Button viewSumBtn, exitBtn;

        DatabaseReference refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        court_view = (TextView)findViewById(R.id.courtView);
        name_view = (TextView)findViewById(R.id.fNameView);
        contact_view = (TextView)findViewById(R.id.contactView);
        date_view = (TextView)findViewById(R.id.dateView);
        time_view = (TextView)findViewById(R.id.timeView);
        hour_view = (TextView)findViewById(R.id.hourView);
        exitBtn = (Button) findViewById(R.id.goMain);
        viewSumBtn = (Button)findViewById(R.id.viewBtn);

        viewSumBtn.setOnClickListener(view -> {
            refer= FirebaseDatabase.getInstance().getReference().child("userBook");
            refer.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    String cName = snapshot.child("courtName").getValue(String.class);
                    String name = snapshot.child("fname").getValue(String.class);
                    String contact = snapshot.child("phone").getValue(String.class);
                    String date = snapshot.child("dateText").getValue(String.class);
                    String time = snapshot.child("timeText").getValue(String.class);
                    String hour = snapshot.child("hourInput").getValue(String.class);

                    court_view.setText(cName);
                    name_view.setText(name);
                    contact_view.setText(contact);
                    date_view.setText(date);
                    time_view.setText(time);
                    hour_view.setText(hour);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });
        exitBtn.setOnClickListener(view -> {
            Intent intMain = new Intent(summaryActivity.this, homeActivity.class);
            startActivity(intMain);
        });
    }
}