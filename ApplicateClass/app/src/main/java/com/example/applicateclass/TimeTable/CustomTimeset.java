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

    @Override
    public String toString() {
        String dayText="";
        String stTime;
        String enTime;

        switch (day){
            case 1: dayText= "월";
            break;
            case 2: dayText= "화";
                break;
            case 3: dayText= "수";
                break;
            case 4: dayText= "목";
                break;
            case 5: dayText= "금";
                break;
        }

        stTime = startTime/100+":"+(startTime%100 == 0 ? "00": startTime%100);
        enTime =endTime/100+":"+(endTime%100 == 0 ? "00": endTime%100);
        return classname+"  "+dayText+" "+stTime+"\n"+"~ "+enTime;
    }
}
