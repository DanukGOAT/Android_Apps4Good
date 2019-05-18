package com.example.exampleapp;

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

import java.util.ArrayList;

public class tutorSelection extends AppCompatActivity {

    private static final String TAG="AviIsMyFather";

    //variables
    private ArrayList<String> tutorNames = new ArrayList<>();
    private ArrayList<ArrayList<String>> tutorSubjects = new ArrayList<>();
    private ArrayList<ArrayList<String>> tutorTimes = new ArrayList<>();
    private ArrayList<ArrayList<String>> tutorPreferences = new ArrayList<>();

    private ArrayList<String> studentPreferences = new ArrayList<>();
    private ArrayList<String> studentSubjects = new ArrayList<>();

    private ArrayList<Tutor> tutorList = new ArrayList<Tutor>();
    private ArrayList<Tutor> sortedTutorList = new ArrayList<>();

    private Tutor tutor;

    private static String subject;

    @Override
    protected void onStart(){
        super.onStart();
        tutor = getIntent().getParcelableExtra("student tutor");
        Log.w("yessir", tutor.toString());
        subject=tutor.getSubjects().get(tutor.getSubjects().size()-1);
        Log.w("listen", subject);
        tutorList.clear();
        sortedTutorList.clear();
        tutorNames.clear();
        tutorPreferences.clear();
        tutorSubjects.clear();
        tutorTimes.clear();
        initTutorData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selection);
        Log.d(TAG, "onCreate: started");
        Log.d(TAG, "tutorselectionclasscheck");
    }

    private void initTutorData(){
        Log.d(TAG, "initTutorData: preparing data");

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.w(TAG, child.getValue(Tutor.class).toString());
                    if(subjectMatch(child.getValue(Tutor.class))) {
                        tutorList.add(child.getValue(Tutor.class));
                    }

                }
                sortTutors();
                for(Tutor t: sortedTutorList){
                    Log.w("tutorlist size", ""+tutorList.size());
                    tutorNames.add(t.getName());
                    tutorPreferences.add(t.getPreferences());
                    tutorSubjects.add(t.getSubjects());
                    tutorTimes.add(t.getTimes());
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

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
        boolean b=false;
        for(int i=0; i<t.getSubjects().size();i++){
            Log.d("bruvhvhvhvhvvh", t.getSubjects().get(i));
            Log.d("bruvhvhvhvhvvh", subject);
            if(t.getSubjects().get(i).equals(subject)){
                b=true;
            }
        }
        return b;
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