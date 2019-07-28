package com.example.applicateclass;

public class Subject {
    String classnumber;//분반
    String credit;//학점
    String grade;//학년
    String name;//계산이론
    String professor;//교수님
    String subjectnumber;//학수번호
    String time;//시간 및 강의실

    public Subject(){}

    public Subject(String classnumber,
            String credit,
            String grade,
            String name,
            String professor,
            String subjectnumber,
            String time){
        this.classnumber = classnumber;
        this.credit = credit;
        this.grade = grade;
        this.name = name;
        this.professor = professor;
        this.subjectnumber = subjectnumber;
        this.time = time;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSubjectnumber() {
        return subjectnumber;
    }

    public void setSubjectnumber(String subjectnumber) {
        this.subjectnumber = subjectnumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }
}
