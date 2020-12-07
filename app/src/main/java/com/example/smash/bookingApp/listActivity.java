package com.example.smash.bookingApp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class listActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    String str1[], str2[], str3[], str4[], str5[];
    int images[] = {R.drawable.pba, R.drawable.pbh, R.drawable.usm, R.drawable.ys};

    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.N)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerView);

        // Add string on recyclerview
        str1 = getResources().getStringArray(R.array.badminton_courts);
        str2 = getResources().getStringArray(R.array.location);
        str3 = getResources().getStringArray((R.array.contact));
        str4 = getResources().getStringArray(R.array.operating_hour);
        str5 = getResources().getStringArray(R.array.price_perHour);

        listAdapter listAdapter = new listAdapter(this, str1, str2, str3, str4, str5, images);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        textView = findViewById(R.id.text);
        getSupportActionBar().setTitle("View Courts");
    }
}