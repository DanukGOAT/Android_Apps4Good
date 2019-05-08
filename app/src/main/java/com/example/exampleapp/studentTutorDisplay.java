package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class studentTutorDisplay extends AppCompatActivity {
    Tutor tutor;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tutor_display);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        Tutor tutor = bundle.getBundle("Tutor")
        tutor = getIntent().getParcelableExtra("parcel_data");
        name = findViewById(R.id.userName);
        name.setText(tutor.getName());
    }
}
