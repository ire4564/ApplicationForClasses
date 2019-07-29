package com.example.applicateclass.TimeTable;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomScheduleItem {
    private String title;
    private String sub;
    private String subjectnumber;
    private String classnumber;
    private int grade;
    private int credit;

    private ArrayList<CustomTimeset> timelist;

    public CustomScheduleItem(){

    }
    public CustomScheduleItem(String title, String sub) {
        this.title = title;
        this.sub = sub;
        timelist =  new ArrayList<>();
    }
    public CustomScheduleItem(String title, String sub, CustomTimeset... timelist) {
        this.title = title;
        this.sub = sub;
        this.timelist = new ArrayList<>();
        this.timelist.addAll(Arrays.asList(timelist));
    }

    public CustomScheduleItem(String title, String sub, ArrayList<CustomTimeset> timelist) {
        this.title = title;
        this.sub = sub;
        this.timelist = timelist;
    }

    public void addTime(int day, int startTime, int endTime,String classname){
        timelist.add(new CustomTimeset(day,startTime,endTime,classname));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setTimelist(ArrayList<CustomTimeset> timelist) {
        this.timelist = timelist;
    }

    public String getTitle() {
        return title;
    }

    public String getSub() {
        return sub;
    }

    public ArrayList<CustomTimeset> getTimelist() {
        return timelist;
    }

    public String getSubjectnumber() {
        return subjectnumber;
    }

    public void setSubjectnumber(String subjectnumber) {
        this.subjectnumber = subjectnumber;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
