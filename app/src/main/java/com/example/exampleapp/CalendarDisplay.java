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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CalendarDisplay extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    private static final String TAG="AviIsMyFather";

    private String name;

    public String date="";
    public String time="";
    public boolean isStartTime=false;
    public Tutor tutor =  new Tutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

        ArrayList<String> arrs = new ArrayList<String>();

        arrs.add("Sup Daniel");

        Tutor Avi = new Tutor(arrs, "Avi");
//        Avi.setName("Avi");
        Avi.addTime("timeThingy");
        Avi.addTime("timeThingy2");
        Avi.addTime("timeThingy3");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
//        myRef.setValue("defaultUser");
        myRef.child("defaultUser").setValue(Avi);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });


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
                tutor.addTime(date + " * " + time);
                Intent intent = new Intent(CalendarDisplay.this, CalendarDisplay.class);
                intent.putExtra("Personal Contact Information", date);
                startActivity(intent);
            }
        });

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(isStartTime)
        {
            if(minute < 10)
                time = hourOfDay + ":0" + minute;
            else
                time = hourOfDay + ":" + minute;
            Button startTimeButton = (Button) findViewById(R.id.endTimeButton);
            startTimeButton.setText(time);
        }
        else
        {
            if(minute < 10)
                time = hourOfDay + ":0" + minute;
            else
                time = hourOfDay + ":" + minute;
            Button endTimeButton = (Button) findViewById(R.id.startTimeButton);
            endTimeButton.setText(time);
        }

    }


}
//