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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarDisplay.this, ProfileInfo.class);
                intent.putExtra("Personal Contact Information", date);
                startActivity(intent);
            }
        });


    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }
}
