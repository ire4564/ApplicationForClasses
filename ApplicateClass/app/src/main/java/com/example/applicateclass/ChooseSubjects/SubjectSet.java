package com.example.applicateclass.ChooseSubjects;

import com.example.applicateclass.TimeTable.CustomScheduleItem;

import java.util.ArrayList;
import java.util.List;

public class SubjectSet {

    String name;
    List<CustomScheduleItem> SubjectsArray;

    public  SubjectSet(){

    }

    public SubjectSet(String name, List<CustomScheduleItem> subjectsArray) {
        this.name = name;
        SubjectsArray = subjectsArray;
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
