package com.example.exampleapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_selection);

        int buttonStyle = R.style.button;

        ArrayList<Button> tutorButtons = new ArrayList<>();

        LinearLayout tutorButtonContainer = (LinearLayout) findViewById(R.id.tutorButtonContainer);

        for(int i = 0; i < 4; i++){
            //tutorButtons.add(new Button(context, null, buttonStyle));
            //tutorButtons.get(i).setText("tutor1");
            //tutorButtonContainer.addView(tutorButtons.get(i));
        }


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tutor = bundle.getParcelable("tutor object");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        myRef.child(tutor.getName()).setValue(tutor);

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
}
