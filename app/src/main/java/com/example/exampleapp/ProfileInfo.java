package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        TextView txtview = findViewById(R.id.contactInfo);
        Button editBut = findViewById(R.id.editButton);


        String date = getIntent().getStringExtra("Personal Contact Information");
        if(date!=null){
            txtview.setText(date);
        }
        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileInfo.this, CalendarDisplay.class);
                startActivity(intent);
            }
        });
    }
}
