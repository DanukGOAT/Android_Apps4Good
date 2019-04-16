package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class TutorQuestions extends AppCompatActivity {

    ArrayList<Integer> subjects = new ArrayList<Integer>();
    ArrayList<Integer> preferences = new ArrayList<Integer>();
    private Tutor tutor = new Tutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_questions);
    }

    public void addInfo() {
        ArrayList<CheckBox> ja = new ArrayList<CheckBox>();
        ja.add((CheckBox) findViewById(R.id.Q1Answer1));
        ja.add((CheckBox) findViewById(R.id.Q1Answer2));
        ja.add((CheckBox) findViewById(R.id.Q1Answer3));
        ja.add((CheckBox) findViewById(R.id.Q1Answer4));
        ja.add((CheckBox) findViewById(R.id.Q1Answer5));
        ja.add((CheckBox) findViewById(R.id.Q1Answer6));
        ja.add((CheckBox) findViewById(R.id.Q1Answer7));
        ja.add((CheckBox) findViewById(R.id.Q1Answer8));

        for (int i = 0; i < ja.size(); i++)
            if (ja.get(i).isChecked())
                subjects.add(i);

        ja.clear();
        ja.add((CheckBox) findViewById(R.id.Q2Answer1));
        ja.add((CheckBox) findViewById(R.id.Q2Answer2));
        ja.add((CheckBox) findViewById(R.id.Q2Answer3));
        ja.add((CheckBox) findViewById(R.id.Q2Answer4));
        ja.add((CheckBox) findViewById(R.id.Q2Answer5));

        for (int i = 0; i < ja.size(); i++)
            if (ja.get(i).isChecked())
                preferences.add(i);
    }

    public ArrayList<Integer> getSubjects() {
        return subjects;
    }

    public ArrayList<Integer> getPreferences() {
        return preferences;
    }

    public void tutorNextButtonPress(View v){
        tutor.setPreferences(preferencesOfTutor());
        tutor.setSubjects(subjectsToTutor());
        Log.w("Preferences", "");
        Intent intent = new Intent(this, CalendarDisplay.class);
        startActivity(intent);
    }

    public ArrayList<String> subjectsToTutor(){
        String arr[] = {"Math", "Physics", "STEM", "STW", "Humanities", "Spanish", "French", "Computer Science"};
        ArrayList<String> tutorSubjects = new ArrayList<String>();
        for(int i=0; i<subjects.size(); i++){
            tutorSubjects.add(arr[subjects.get(i)]);
        }
        return tutorSubjects;
    }

    public ArrayList<String> preferencesOfTutor(){
        String arr[] = {"At my home", "At student's home", "Public place", "Online", "No Preference"};
        ArrayList<String> tutorPreferences = new ArrayList<String>();
        for(int i=0; i<subjects.size(); i++){
            tutorPreferences.add(arr[preferences.get(i)]);
        }
        return tutorPreferences;
    }
}
