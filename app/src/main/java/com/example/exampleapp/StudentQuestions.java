package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentQuestions extends AppCompatActivity {

    ArrayList<Integer> subjects = new ArrayList<Integer>();
    ArrayList<Integer> preferences = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_questions);
    }

    public void addInfo() {
        ArrayList<RadioButton> ja = new ArrayList<>();
        ja.add((RadioButton) findViewById(R.id.Q1answer1));
        ja.add((RadioButton) findViewById(R.id.Q1answer2));
        ja.add((RadioButton) findViewById(R.id.Q1answer3));
        ja.add((RadioButton) findViewById(R.id.Q1answer4));
        ja.add((RadioButton) findViewById(R.id.Q1answer5));
        ja.add((RadioButton) findViewById(R.id.Q1answer6));
        ja.add((RadioButton) findViewById(R.id.Q1answer7));
        ja.add((RadioButton) findViewById(R.id.Q1answer8));
        subjects.clear();

        for (int i = 0; i < ja.size(); i++)
            if (ja.get(i).isChecked())
                subjects.add(i);

        ja.clear();
        ArrayList<CheckBox> ja2 = new ArrayList<>();
        ja2.add((CheckBox) findViewById(R.id.q2Answer1));
        ja2.add((CheckBox) findViewById(R.id.q2Answer2));
        ja2.add((CheckBox) findViewById(R.id.q2Answer3));
        ja2.add((CheckBox) findViewById(R.id.q2Answer4));
        ja2.add((CheckBox) findViewById(R.id.q2Answer5));

        preferences.clear();

        for (int i = 0; i < ja2.size(); i++)
            if (ja2.get(i).isChecked())
                preferences.add(i);


    }
    public void goToTutorSelection(View v){
        addInfo();
        Intent intent = new Intent(this, tutorSelection.class);
        Tutor tutt = new Tutor();
        Log.d("YUHHHHHH", subjectsToTutor().toString());
        Log.d("YUHHHHHH", preferencesOfTutor().toString());
        tutt.setSubjects(subjectsToTutor());
        tutt.setPreferences(preferencesOfTutor());
        intent.putExtra("student tutor", tutt);
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("myID", tutt);
//        intent.putExtras(bundle);

        if(subjectsToTutor().isEmpty())
        {
            Toast.makeText(this, "Enter a subject", Toast.LENGTH_SHORT).show();
        }
        else if(preferencesOfTutor().isEmpty())
        {
            Toast.makeText(this, "Enter your preferences", Toast.LENGTH_SHORT).show();
        }
        else
            startActivity(intent);
    }

    public ArrayList<String> preferencesOfTutor(){
        String arr[] = {"At my home", "At student's home", "Public place", "Online", "No Preference"};
        ArrayList<String> tutorPreferences = new ArrayList<String>();
        for(int i=0; i<preferences.size(); i++){
            tutorPreferences.add(arr[preferences.get(i)]);
        }
        return tutorPreferences;
    }

    public ArrayList<String> subjectsToTutor(){
        String arr[] = {"Math", "Physics", "STEM", "STW", "Humanities", "Spanish", "French", "Computer Science"};
        ArrayList<String> tutorSubjects = new ArrayList<String>();
        for(int i=0; i<subjects.size(); i++){
            tutorSubjects.add(arr[subjects.get(i)]);
        }
        return tutorSubjects;
    }

}