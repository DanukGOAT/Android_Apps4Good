package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileInfo extends AppCompatActivity {
    //Data
    private Tutor tutor = new Tutor();
    private TextView usrname;
    private TextView blurb;
    private TextView experience;
    private TextView contactInfo;
    private TextView subjects;
    private TextView preferences;
    private TextView mamsPlug;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        //Grabs the tutor object from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tutor = bundle.getParcelable("tutor object");

        //Updating data with individual tutor values
        usrname = findViewById(R.id.userName);
        blurb = findViewById(R.id.personalBlurb);
        experience = findViewById(R.id.tutorExperience);
        contactInfo = findViewById(R.id.contactInfo);
        subjects = findViewById(R.id.subjects);
        preferences = findViewById(R.id.where);
        subjects.setText(tutor.getSubjects().toString());
        preferences.setText(tutor.getPreferences().toString());
        Button editBut = findViewById(R.id.editButton);
        mamsPlug = findViewById(R.id.plugForMAMS);

        //OnClickListeners to put blank text when the user taps on a text box so
        //that they don't have to clear it out themselves
//        usrname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                usrname.setText("");
//            }
//        });
//
//        blurb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blurb.setText("");
//            }
//        });
//
//        experience.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                experience.setText("");
//            }
//        });
//
//        contactInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                contactInfo.setText("");
//            }
//        });
//
//        mamsPlug.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mamsPlug.setText("https://www.massacademy.org/");
//            }
//        });



//        String date = getIntent().getStringExtra("Personal Contact ");
//        if(date!=null){
//            txtview.setText(date);
//        }

        //Sends the user back to the CalendarDisplay class
        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutor.setName(usrname.getText().toString());
                tutor.setBlurb(blurb.getText().toString());
                tutor.setExperience(experience.getText().toString());
                tutor.setContactInfo(contactInfo.getText().toString());
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Users");
                myRef.child(""+tutor.getName()).setValue(tutor);
                Intent intent = new Intent(ProfileInfo.this, MainActivity.class);
                intent.putExtra("tutor object", tutor);
                startActivity(intent);
            }
        });
    }
}
