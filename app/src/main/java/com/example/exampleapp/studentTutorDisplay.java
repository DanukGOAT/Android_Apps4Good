package com.example.exampleapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    TextView dates;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private String TAG = "yooothiscouldbecool";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tutor_display);
        tutor = getIntent().getParcelableExtra("parcel_data");
        name = findViewById(R.id.userName);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        blurb = findViewById(R.id.personalBlurb);

        experience = findViewById(R.id.tutorExperience);

        subjects = findViewById(R.id.subjects);

        preferences = findViewById(R.id.where);

        contactInfo = findViewById(R.id.contactInfo);

        dates = findViewById(R.id.timeDisplay);

        Log.d("this could be cool", "tutorselectionclasscheck");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "test2");
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
                    if(tutor.getName().equals(child.getValue(Tutor.class).getName())){
                        name.setText(child.getValue(Tutor.class).getName());
                        blurb.setText(child.getValue(Tutor.class).getBlurb());
                        experience.setText(child.getValue(Tutor.class).getExperience());
                        subjects.setText(child.getValue(Tutor.class).getSubjects().toString());
                        preferences.setText(child.getValue(Tutor.class).getPreferences().toString());
                        contactInfo.setText(child.getValue(Tutor.class).getContactInfo());
                        dates.setText(child.getValue(Tutor.class).getTimes().toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

//        if(!tutor.getSubjects().isEmpty()) {
//            String tutSubjects = new String(tutor.getSubjects().get(0));
//            for (int i = 1; i < tutor.getSubjects().size(); i++)
//                tutSubjects = tutSubjects + ", " + tutor.getSubjects().get(i);
//            subjects.setText(tutSubjects);
//        }
    }
}
