package com.example.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class tutorSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selection);


        ArrayList<Button> tutorButtons = new ArrayList<>();

        LinearLayout tutorButtonContainer = (LinearLayout) findViewById(R.id.tutorButtonContainer);

        for(int i = 0; i < 4; i++){
            tutorButtons.add(new Button(this));
            tutorButtons.get(i).setText("tutor1");
            tutorButtonContainer.addView(tutorButtons.get(i));
        }

    }
}
