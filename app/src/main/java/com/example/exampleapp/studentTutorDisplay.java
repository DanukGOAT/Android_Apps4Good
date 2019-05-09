package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class studentTutorDisplay extends AppCompatActivity {
    Tutor tutor;
    TextView name;
    TextView blurb;
    TextView experience;
    TextView subjects;
    TextView preferences;
    TextView link;
    TextView contactInfo;
    Text dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tutor_display);
        tutor = getIntent().getParcelableExtra("parcel_data");
        name = findViewById(R.id.userName);
        name.setText(tutor.getName());
        blurb = findViewById(R.id.personalBlurb);
        blurb.setText(tutor.getBlurb());
        experience = findViewById(R.id.tutorExperience);
        experience.setText(tutor.getExperience());
        subjects = findViewById(R.id.subjects);
        if(!tutor.getSubjects().isEmpty()) {
            String tutSubjects = new String(tutor.getSubjects().get(0));
            for (int i = 1; i < tutor.getSubjects().size(); i++)
                tutSubjects = tutSubjects + ", " + tutor.getSubjects().get(i);
            subjects.setText(tutSubjects);
        }
    }
}
