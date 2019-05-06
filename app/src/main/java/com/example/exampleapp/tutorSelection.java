package com.example.exampleapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class tutorSelection extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseDatabase database;

    private static final String TAG="AviIsMyFather";

    private Tutor tutor = new Tutor();

    //vars
    private ArrayList<String> tutorNames = new ArrayList<>();
    private ArrayList<String> tutorSubjects = new ArrayList<>();
    private ArrayList<String> tutorTimes = new ArrayList<>();
    private ArrayList<String> tutorPreferences = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selection);
        Log.d(TAG, "onCreate: started");
        
       // int buttonStyle = R.style.button;

        initTutorData();

       // ArrayList<Button> tutorButtons = new ArrayList<>();

//        final LinearLayout tutorButtonContainer = (LinearLayout) findViewById(R.id.tutorButtonContainer);
//
//        for(int i = 0; i < 4; i++){
//            //tutorButtons.add(new Button(new ContextThemeWrapper(context, buttonStyle), null, buttonStyle);
//            //tutorButtons.get(i).setText("tutor1");
//            //tutorButtonContainer.addView(tutorButtons.get(i));
//        }


//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        tutor = bundle.getParcelable("tutor object");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
//                    Button tutorButton = new Button(tutorSelection.this);
//                    tutorButton.setText(child.getValue(Tutor.class).getName());
//                    tutorButtonContainer.addView(tutorButton);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void initTutorData(){
        Log.d(TAG, "initTutorData: preparing data");

        tutorNames.add("Kevin");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Math, Physics");
        tutorTimes.add("8:00 PM Monday, 9:00PM Tuesday");

        tutorNames.add("Nathan");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Physics, CS");
        tutorTimes.add("7:00 PM Wednesday, 3:00PM Friday");

        tutorNames.add("Danush");
        tutorPreferences.add("At WPI");
        tutorSubjects.add("Humanities ONLY");
        tutorTimes.add("3:14AM Monday, 12:21PM Sunday");

        tutorNames.add("Kevin");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Math, Physics");
        tutorTimes.add("8:00 PM Monday, 9:00PM Tuesday");


        tutorNames.add("Nathan");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Physics, CS");
        tutorTimes.add("7:00 PM Wednesday, 3:00PM Friday");

        tutorNames.add("Danush");
        tutorPreferences.add("At WPI");
        tutorSubjects.add("Humanities ONLY");
        tutorTimes.add("3:14AM Monday, 12:21PM Sunday");

        tutorNames.add("Kevin");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Math, Physics");
        tutorTimes.add("8:00 PM Monday, 9:00PM Tuesday");

        tutorNames.add("Nathan");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Physics, CS");
        tutorTimes.add("7:00 PM Wednesday, 3:00PM Friday");

        tutorNames.add("Danush");
        tutorPreferences.add("At WPI");
        tutorSubjects.add("Humanities ONLY");
        tutorTimes.add("3:14AM Monday, 12:21PM Sunday");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(tutorNames,tutorSubjects,tutorTimes,tutorPreferences,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
