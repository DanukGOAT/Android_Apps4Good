package com.example.exampleapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void studentButtonPress(View v){
        Intent intent = new Intent(this, StudentQuestions.class);
        startActivity(intent);
    }

    public void tutorButtonPress(View v){
        Intent intent = new Intent(this, TutorQuestions.class);
        startActivity(intent);
    }

    public void debugButtonPress(View v){
        Intent intent = new Intent(this, CalendarDisplay.class);
        startActivity(intent);
    }
}
