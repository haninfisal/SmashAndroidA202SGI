package com.example.smash.bookingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class bookActivity extends AppCompatActivity implements View.OnClickListener {

    EditText fullName, contactNumber, date_txt, time_txt, hour_in;
    Spinner courts_name;
    Button mConfirmBtn, date_btn, time_btn, nextButton;
    private int mYear, mMonth, mDay, mHour, mMin;

    DatabaseReference userBookingRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        fullName = findViewById(R.id.fName);
        contactNumber = findViewById(R.id.phone);
        hour_in = findViewById(R.id.hourInput);
        courts_name = findViewById(R.id.courtName);
        mConfirmBtn = findViewById(R.id.confirmBtn);
        date_btn = (Button)findViewById(R.id.dateBtn);
        time_btn = (Button)findViewById(R.id.timeBtn);
        date_txt = (EditText)findViewById(R.id.dateText);
        time_txt = (EditText)findViewById(R.id.timeText);
        nextButton = findViewById(R.id.nextBtn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(bookActivity.this, summaryActivity.class);
                startActivity(i);
            }
        });

        date_btn.setOnClickListener(this);
        time_btn.setOnClickListener(this);

        userBookingRef = FirebaseDatabase.getInstance().getReference()
                .child("userBook");

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertBookingData();
            }
        });
    }

    private void insertBookingData(){

        String courts = courts_name.getSelectedItem().toString();
        String fName = fullName.getText().toString();
        String conNumber = contactNumber.getText().toString();
        String date = date_txt.getText().toString();
        String time = time_txt.getText().toString();
        String hour = hour_in.getText().toString();

        if(fName.isEmpty()){
            fullName.setError("Full name is required");
            fullName.requestFocus();

        } else if (conNumber.isEmpty()){
            contactNumber.setError("Please enter valid number");
            contactNumber.requestFocus();

        } else if (date.isEmpty()){
            date_txt.setError("Please select a date");
            date_txt.requestFocus();

        } else if (time.isEmpty()){
            time_txt.setError("Please select a time");
            time_txt.requestFocus();

        } else if (hour.isEmpty()){
            hour_in.setError("Please enter at least 1 hour");
            hour_in.requestFocus();

        }
        else {

            userBook userbook = new userBook(courts, fName, conNumber, date, time, hour);

            userBookingRef.push().setValue(userbook);

            Toast.makeText(bookActivity.this, "Booking Data Submitted", Toast.LENGTH_SHORT).show();

//            Intent i = new Intent(bookActivity.this, summaryActivity.class);
//            startActivity(i);
        }
    }
    //Date Picker
    @Override
    public void onClick(View view) {
        if(view==date_btn){
            final Calendar calendar=Calendar.getInstance();
            mYear=calendar.get(Calendar.YEAR);
            mMonth=calendar.get(Calendar.MONTH);
            mDay=calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    date_txt.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        }
        //Time Picker
        if(view==time_btn){
            final Calendar cal = Calendar.getInstance();
            mHour=cal.get(Calendar.HOUR_OF_DAY);
            mMin=cal.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                    time_txt.setText(hourOfDay+":"+min);
                }
            },mHour,mMin,false);
            timePickerDialog.show();
        }

    }
}