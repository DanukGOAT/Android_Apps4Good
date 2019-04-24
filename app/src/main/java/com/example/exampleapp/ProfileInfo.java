package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileInfo extends AppCompatActivity {
    private Tutor tutor = new Tutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tutor = bundle.getParcelable("tutor object");

        final TextView usrname = findViewById(R.id.userName);
        final TextView blurb = findViewById(R.id.personalBlurb);
        final TextView experience = findViewById(R.id.tutorExperience);
        final TextView contactInfo = findViewById(R.id.contactInfo);
        TextView subjects = findViewById(R.id.subjects);
        TextView preferences = findViewById(R.id.where);
        subjects.setText(tutor.getSubjects().toString());
        preferences.setText(tutor.getPreferences().toString());
        Button editBut = findViewById(R.id.editButton);



//        String date = getIntent().getStringExtra("Personal Contact ");
//        if(date!=null){
//            txtview.setText(date);
//        }
        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutor.setName(usrname.getText().toString());
                tutor.setBlurb(blurb.getText().toString());
                tutor.setExperience(experience.getText().toString());
                tutor.setContactInfo(contactInfo.getText().toString());
                Intent intent = new Intent(ProfileInfo.this, CalendarDisplay.class);
                intent.putExtra("tutor object", tutor);
                startActivity(intent);
            }
        });
    }
}
