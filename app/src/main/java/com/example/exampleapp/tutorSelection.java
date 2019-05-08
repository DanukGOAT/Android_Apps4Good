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

//    private Tutor tutor = new Tutor();

    //vars
    private ArrayList<String> tutorNames = new ArrayList<>();
    private ArrayList<String> tutorSubjects = new ArrayList<>();
    private ArrayList<String> tutorTimes = new ArrayList<>();
    private ArrayList<String> tutorPreferences = new ArrayList<>();

    private ArrayList<String> studentPreferences = new ArrayList<>();
    private ArrayList<String> studentSubjects = new ArrayList<>();

    private ArrayList<Tutor> tutorList = new ArrayList<>();
    private ArrayList<Tutor> sortedTutorList = new ArrayList<>();

    private Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selection);
        Log.d(TAG, "onCreate: started");

        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();

        tutor = getIntent().getParcelableExtra("student tutor");

//        tutor=bundle.getParcelable( "student tutor");
        studentSubjects=tutor.getSubjects();
        studentPreferences=tutor.getPreferences();

        Log.w(TAG, "subjectscheck " + studentSubjects.get(0));
        Log.w(TAG, "preferencescheck " + studentPreferences.get(0));

        initTutorData();

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        tutor = bundle.getParcelable("tutor object");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
                    if(subjectMatch(child.getValue(Tutor.class))) {
                        tutorList.add(child.getValue(Tutor.class));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        sortTutors();
        for(Tutor t: sortedTutorList){
            tutorNames.add(t.getName());
            tutorPreferences.add(t.getPreferences().toString());
            tutorSubjects.add(t.getSubjects().toString());
            tutorTimes.add(t.getTimes().toString());
        }

        tutorNames.add("Kevin");
        tutorPreferences.add("At home, at MAMS");
        tutorSubjects.add("Math, Physics");
        tutorTimes.add("8:00 PM Monday, 9:00PM Tuesday");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(tutorNames,tutorSubjects,tutorTimes,tutorPreferences,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sortTutors(){
        boolean inMiddle=false;
        boolean firstElem=true;
        for(Tutor t: tutorList){
            if(firstElem){
                sortedTutorList.add(t);
                firstElem=false;
            }else{
                for(int i=0; i<sortedTutorList.size(); i++){
                    if(preferenceNum(t)>=preferenceNum(sortedTutorList.get(i))){
                        sortedTutorList.add(i, t);
                        i=sortedTutorList.size()+10;
                        inMiddle=true;
                    }
                }
                if(!inMiddle){
                    sortedTutorList.add(t);
                }
            }
        }
    }

    public boolean subjectMatch(Tutor t){
        return (t.getSubjects().contains(studentSubjects.get(0)));
    }

    public int preferenceNum(Tutor t){
        int count=0;
        for(String s: studentPreferences){
            if(t.getPreferences().contains(s)){
                count++;
            }
        }
        return count;
    }
}
