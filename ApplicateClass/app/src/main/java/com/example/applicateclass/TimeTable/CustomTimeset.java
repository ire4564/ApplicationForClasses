package com.example.applicateclass.TimeTable;

public class CustomTimeset {
    private int day;
    private int startTime;
    private int endTime;
    private String classname;


    public CustomTimeset(){}

    public CustomTimeset(int day, int startTime, int endTime, String classname) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classname = classname;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
