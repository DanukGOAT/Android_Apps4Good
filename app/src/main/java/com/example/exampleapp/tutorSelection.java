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

    private ArrayList<String> studentPreferences = new ArrayList<>();
    private ArrayList<String> studentSubjects = new ArrayList<>();

    private ArrayList<Tutor> tutorList;
    private ArrayList<Tutor> sortedTutorList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selection);
        Log.d(TAG, "onCreate: started");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tutor=bundle.getParcelable( "student tutor");
        studentSubjects=tutor.getSubjects();
        studentPreferences=tutor.getPreferences();

        Log.w(TAG, "subjectscheck " + studentSubjects.get(0));
        Log.w(TAG, "preferencescheck " + studentPreferences.get(0));
        
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

//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Users");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot child: dataSnapshot.getChildren()){
//                    Log.w(TAG, child.getValue(Tutor.class).toString());
////                    Button tutorButton = new Button(tutorSelection.this);
////                    tutorButton.setText(child.getValue(Tutor.class).getName());
////                    tutorButtonContainer.addView(tutorButton);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException());
//            }
//        });
    }

    public void initTutorData(){
        Log.d(TAG, "initTutorData: preparing data");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
                    Log.w(TAG, "bruhhhh");
//                    Button tutorButton = new Button(tutorSelection.this);
//                    tutorButton.setText(child.getValue(Tutor.class).getName());
//                    tutorButtonContainer.addView(tutorButton);
                    if(subjectMatch(child.getValue(Tutor.class))) {
                        Log.w(TAG, "why isnt this working?");
                        Log.w(TAG, "yoooo wtf "+ child.getValue(Tutor.class).toString());

                        //Fix this line
                        tutorList.add(child.getValue(Tutor.class));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        Log.w(TAG, "Greg The Leg");
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

    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(tutorNames,tutorSubjects,tutorTimes,tutorPreferences,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sortTutors(){
        Log.w(TAG, "Greg The Leg part 2");
        boolean inMiddle=false;
        boolean firstElem=true;
        Log.w(TAG, "Greg The Leg part 3 "+tutorList.size());
        for(Tutor t: tutorList){
            Log.w(TAG, "Greg The Leg");
            if(firstElem){
                sortedTutorList.add(t);
                firstElem=false;
            }else{
                Log.w(TAG, "Greg The Leg");
                for(int i=0; i<sortedTutorList.size(); i++){
                    Log.w(TAG, "Greg The Leg");
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

        if(studentSubjects==null){
            return false;
        }else {
            return (t.getSubjects().contains(studentSubjects.get(0)));
        }
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
