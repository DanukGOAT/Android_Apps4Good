package com.example.exampleapp;

import android.content.Intent;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

public class CalendarDisplay extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public String date="";
    public boolean isStartTime=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

        Button endTimeButton = (Button) findViewById(R.id.endTimeButton);
        endTimeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isStartTime = true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button startTimeButton = (Button) findViewById(R.id.startTimeButton);
        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStartTime = false;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        CalendarView calendarView = findViewById(R.id.Calendarr);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = month + "/"+ dayOfMonth+"/"+year;
//                Intent intent = new Intent(CalendarDisplay.this, ProfileInfo.class);
//                intent.putExtra("Personal Contact Information", date);
//                startActivity(intent);
            }
        });
        Button finishedButton = findViewById(R.id.finishedButton);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarDisplay.this, ProfileInfo.class);
                intent.putExtra("Personal Contact Information", date);
                startActivity(intent);
            }
        });
        Button addTimeButton = findViewById(R.id.addTimeButton);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarDisplay.this, CalendarDisplay.class);
                intent.putExtra("Personal Contact Information", date);
                startActivity(intent);
            }
        });


    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(isStartTime)
        {

            TextView endTimeDisplay = (TextView) findViewById(R.id.endTimeDisplay);
            endTimeDisplay.setText(hourOfDay + ":" + minute);
        }
        else
        {
            TextView startTimeDisplay = (TextView) findViewById(R.id.startTimeDisplay);
            startTimeDisplay.setText(hourOfDay + ":" + minute);
        }

    }
}
