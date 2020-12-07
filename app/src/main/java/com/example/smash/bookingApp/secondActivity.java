package com.example.smash.bookingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {

    ImageView mainLogoView;
    TextView title, description, contactNum, operationHour, pricePerHour;
    Button mBook;
    String data1, data2, data3, data4, data5;
    int logoView;

    private Button openFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainLogoView = findViewById(R.id.mainLogoView);
        title = findViewById(R.id.mainTitle);
        description = findViewById(R.id.description);
        mBook = findViewById(R.id.bookBtn);
        contactNum = findViewById(R.id.contact);
        operationHour = findViewById(R.id.opHour);
        pricePerHour = findViewById(R.id.price);
        openFragmentBtn = (Button)findViewById(R.id.slotBtn);

        openFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFragment dialogfragment =new dialogFragment();
                dialogfragment.show(getSupportFragmentManager(),"viewSlotFragment");

            }
        });

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intBook = new Intent(secondActivity.this, bookActivity.class);
                startActivity(intBook);
            }
        });

        getData();
        setData();
    }

    private void getData() {
        if(getIntent().hasExtra("logoView") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2") &&
                getIntent().hasExtra("data3") && getIntent().hasExtra("data4") && getIntent().hasExtra("data5")){

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            data5 = getIntent().getStringExtra("data5");
            logoView = getIntent().getIntExtra("logoView", 1);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData() {
        title.setText(data1);
        description.setText(data2);
        contactNum.setText(data3);
        operationHour.setText(data4);
        pricePerHour.setText(data5);
        mainLogoView.setImageResource(logoView);

    }

}