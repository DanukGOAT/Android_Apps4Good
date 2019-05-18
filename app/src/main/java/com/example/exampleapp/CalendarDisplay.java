package com.example.exampleapp;

import android.content.Intent;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Displays the calendar and timepickers for the tutors to select from
 */

public class CalendarDisplay extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    private static final String TAG="AviIsMyFather";

    private String name;

    public String date="";
    public String time="";
    public boolean isStartTime=false;
    public String startTime="";
    public String endTime="";


    public void setTutor(Tutor tut) {
        tutor.otherTutor(tut);
    }

    public Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        date = df.format(c);

        tutor=bundle.getParcelable("tutor object");

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
                date = month + "/"+ dayOfMonth+"/"+year+" ";
//                tutor.addTime(date);
//                Intent intent = new Intent(CalendarDisplay.this, ProfileInfo.class);
//                intent.putExtra("Personal Contact Information", date);
//                startActivity(intent);
            }
        });
        Button finishedButton = findViewById(R.id.finishedButton);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                database = FirebaseDatabase.getInstance();
//                myRef = database.getReference("Users");
//                myRef.child(""+tutor.getUserNum()).setValue(tutor);
                if(date.isEmpty()||startTime.isEmpty()||endTime.isEmpty()){
                    Toast.makeText(CalendarDisplay.this, "Make sure to put in a date, a start time, and an end time.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(CalendarDisplay.this, ProfileInfo.class);
                    intent.putExtra("Personal Contact Information", date);
                    intent.putExtra("tutor object", tutor);
                    startActivity(intent);
                }
            }
        });
        Button addTimeButton = findViewById(R.id.addTimeButton);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<CheckBox> ja = new ArrayList<CheckBox>();
                ja.add((CheckBox) findViewById(R.id.weeklyCheckBox));

                if (ja.get(0).isChecked()) {
                    tutor.addTime(date + " * " + startTime + " "+ endTime + " weekly");
                }else{
                    tutor.addTime(date + " * " + startTime + " "+ endTime);
                }



                if(date.isEmpty()||startTime.isEmpty()||endTime.isEmpty()){
                    Toast.makeText(CalendarDisplay.this, "Make sure to put in a date, a start time, and an end time.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(CalendarDisplay.this, CalendarDisplay.class);
                    intent.putExtra("tutor object", tutor);
                    intent.putExtra("Personal Contact Information", date);
                    startActivity(intent);
                }
            }
        });

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(isStartTime)
        {
            if(minute < 10)
                startTime = hourOfDay + ":0" + minute;
            else
                startTime = hourOfDay + ":" + minute;
            Button startTimeButton = (Button) findViewById(R.id.endTimeButton);
            startTimeButton.setText(startTime);
        }
        else
        {
            if(minute < 10)
                endTime = hourOfDay + ":0" + minute;
            else
                endTime = hourOfDay + ":" + minute;
            Button endTimeButton = (Button) findViewById(R.id.startTimeButton);
            endTimeButton.setText(endTime);
        }

    }

}
