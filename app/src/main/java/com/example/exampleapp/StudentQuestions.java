package com.example.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
        ArrayList<CheckBox> ja = new ArrayList<CheckBox>();
        ja.add((CheckBox) findViewById(R.id.Q1Answer1));
        ja.add((CheckBox) findViewById(R.id.Q1Answer2));
        ja.add((CheckBox) findViewById(R.id.Q1Answer3));
        ja.add((CheckBox) findViewById(R.id.Q1Answer4));
        ja.add((CheckBox) findViewById(R.id.Q1Answer5));
        ja.add((CheckBox) findViewById(R.id.Q1Answer6));
        ja.add((CheckBox) findViewById(R.id.Q1Answer7));
        ja.add((CheckBox) findViewById(R.id.Q1Answer8));

        for (int i = 1; i <= ja.size(); i++)
            if (ja.get(i).isChecked())
                subjects.add(i);

        ja.clear();
        ja.add((CheckBox) findViewById(R.id.Q2Answer1));
        ja.add((CheckBox) findViewById(R.id.Q2Answer2));
        ja.add((CheckBox) findViewById(R.id.Q2Answer3));
        ja.add((CheckBox) findViewById(R.id.Q2Answer4));
        ja.add((CheckBox) findViewById(R.id.Q2Answer5));

        for (int i = 1; i <= ja.size(); i++)
            if (ja.get(i).isChecked())
                preferences.add(i);
    }

}
