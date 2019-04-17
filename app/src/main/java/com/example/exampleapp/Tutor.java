package com.example.exampleapp;

import java.util.ArrayList;

public class Tutor {
    private String name;
    private ArrayList<String> times;
//    private String times;
    private static int userNum=1;
    private ArrayList<String> subjects;
    private ArrayList<String> preferences;

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences = preferences;
    }

    public Tutor(){
        times = new ArrayList<String>();
        subjects = new ArrayList<String>();
        preferences  = new ArrayList<String>();
        name = ""+userNum;
        userNum++;
    }

    public String getName() {
        return name;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }

    public static int getUserNum() {
        return userNum;
    }

    public static void setUserNum(int userNum) {
        Tutor.userNum = userNum;
    }

    public Tutor(ArrayList<String> tutorTimes, String newName){
        times = tutorTimes;
        name = newName;
        userNum=0;
    }

    public void addTime(String time){
        times.add(time);
    }
    public void setName(String newName){
        name=newName;
    }

    public ArrayList<String> getTimes(){
        return times;
    }

    public String toString(){
        return "times "+ times.toString()+" name: "+ name + " preferences " + preferences.toString() +" subjects "+ subjects.toString();
    }

}
