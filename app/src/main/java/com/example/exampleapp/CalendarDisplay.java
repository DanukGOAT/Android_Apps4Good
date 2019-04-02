package com.example.exampleapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarDisplay extends AppCompatActivity {
    public String date="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

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
}
