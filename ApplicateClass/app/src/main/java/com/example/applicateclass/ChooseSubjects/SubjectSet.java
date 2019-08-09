package com.example.applicateclass.ChooseSubjects;

import com.example.applicateclass.TimeTable.CustomScheduleItem;

import java.util.ArrayList;
import java.util.List;

public class SubjectSet {

    String name;
    List<CustomScheduleItem> SubjectsArray;
    int credit;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public  SubjectSet(){

    }

    public SubjectSet(String name, List<CustomScheduleItem> subjectsArray,int credit) {
        this.name = name;
        SubjectsArray = subjectsArray;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomScheduleItem> getSubjectsArray() {
        return SubjectsArray;
    }

    public void setSubjectsArray(ArrayList<CustomScheduleItem> subjectsArray) {
        SubjectsArray = subjectsArray;
    }
}
