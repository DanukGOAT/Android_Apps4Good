package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_page);
    }

    public void studentButtonPress(View v){
        Intent intent = new Intent(this, StudentQuestions.class);
        startActivity(intent);
    }

    public void tutorButtonPress(View v){
        Intent intent = new Intent(this, TutorQuestions.class);
        startActivity(intent);
    }
}
