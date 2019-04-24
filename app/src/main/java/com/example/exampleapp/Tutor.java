package com.example.exampleapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Tutor implements Parcelable {
    private String name;
    private ArrayList<String> times;
//    private String times;
    private static int userNum=1;
    private ArrayList<String> subjects;
    private ArrayList<String> preferences;


    private String blurb;
    private String experience;
    private String contactInfo;

    public void otherTutor(Tutor tutor){
        this.name=tutor.name;
        this.times
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }




    protected Tutor(Parcel in) {
        name = in.readString();
        times = in.createStringArrayList();
        subjects = in.createStringArrayList();
        preferences = in.createStringArrayList();
    }


    public static final Creator<Tutor> CREATOR = new Creator<Tutor>() {
        @Override
        public Tutor createFromParcel(Parcel in) {
            return new Tutor(in);
        }

        @Override
        public Tutor[] newArray(int size) {
            return new Tutor[size];
        }
    };

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
        name=""+userNum;
        userNum++;
        blurb = "";
        experience = "";
        contactInfo = "";
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(times);
        dest.writeStringList(subjects);
        dest.writeStringList(preferences);
    }
}
