package com.example.exampleapp;

import java.util.ArrayList;

public class Tutor {
    private String name = "";
    private ArrayList<String> times;

    public Tutor(){
        ArrayList<String> times;
    }

    public void addTime(String time){
        times.add(time);
    }

    public ArrayList<String> getTimes(){
        return times;
    }

}
