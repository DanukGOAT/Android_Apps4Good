package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//what up dudes
public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //get the intent that started this activity and extract the data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Double ans = bundle.getDouble("answer");


        TextView answerView = (TextView) findViewById(R.id.answerView);
        answerView.setText(ans.toString());
    }
}
