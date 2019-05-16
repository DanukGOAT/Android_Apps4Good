package com.example.exampleapp;


import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent password = new Intent(this, CalendarDisplay.class);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    child.getValue();
                }
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Button passbutton = (Button) findViewById(R.id.button2);
        passbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = findViewById(R.id.editText);
                final String usrcheck = t.getText().toString();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot child: dataSnapshot.getChildren()){
                            Log.w(TAG, child.getValue(Tutor.class).toString());
                            if(child.getValue(Tutor.class).getName().equals(usrcheck)){
                                password.putExtra("tutor object", child.getValue(Tutor.class));
                                startActivity(password);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "Failed to read value.", databaseError.toException());
                    }
                });
            }
        });
    }

    //Sends the user to the StudentQuestions activity
    public void studentButtonPress(View v){
        Intent intent = new Intent(this, StudentQuestions.class);
        startActivity(intent);
    }

    //Sends the user to the TutorQuestions activity
    public void tutorButtonPress(View v){
        Intent intent = new Intent(this, TutorQuestions.class);
        startActivity(intent);
    }
}
