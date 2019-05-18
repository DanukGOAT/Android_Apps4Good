//material learned from CodingWithMitch tutorial on youtube.

package com.example.exampleapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> tutorNames;
    private ArrayList<ArrayList<String>> tutorSubjects;
    private ArrayList<ArrayList<String>> tutorTimes;
    private ArrayList<ArrayList<String>> tutorPreferences;
    private Context context;
    /**
     * Initializes the RecyclerView for tutorselection
     */
    public RecyclerViewAdapter(ArrayList<String> ntutorNames, ArrayList<ArrayList<String>> ntutorSubjects, ArrayList<ArrayList<String>> ntutorTimes, ArrayList<ArrayList<String>> ntutorPreferences, Context ncontext){
        tutorNames = ntutorNames;
        tutorSubjects = ntutorSubjects;
        tutorTimes = ntutorTimes;
        tutorPreferences = ntutorPreferences;
        context = ncontext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Puts data in the card, and sets a clicklistener
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        viewHolder.tutorName.setText(tutorNames.get(position));
        viewHolder.tutorPreference.setText(tutorPreferences.get(position).toString());
        viewHolder.tutorSubject.setText(tutorSubjects.get(position).toString());
        viewHolder.tutorTime.setText(tutorTimes.get(position).toString());

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + tutorNames.get(position));

                Toast.makeText(context, tutorNames.get(position), Toast.LENGTH_SHORT).show();

                Tutor tutor = new Tutor();
                tutor.setName(tutorNames.get(position));
                tutor.setPreferences(tutorPreferences.get(position));
                tutor.setSubjects(tutorSubjects.get(position));
                tutor.setTimes(tutorTimes.get(position));
                Intent intent = new Intent(context, studentTutorDisplay.class);
                intent.putExtra("parcel_data", tutor);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutorNames.size();
    }

    /**
     * Viewholder to set up interface xml
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tutorName;
        TextView tutorSubject;
        TextView tutorTime;
        TextView tutorPreference;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tutorName = itemView.findViewById(R.id.tutorName);
            tutorSubject = itemView.findViewById(R.id.tutorSubject);
            tutorTime = itemView.findViewById(R.id.tutorTime);
            tutorPreference = itemView.findViewById(R.id.tutorPreference);
            parentLayout = itemView.findViewById(R.id.parent_layout);


        }
    }
}
