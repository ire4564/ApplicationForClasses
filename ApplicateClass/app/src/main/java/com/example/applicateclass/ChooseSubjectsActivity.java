package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

import java.util.ArrayList;

public class ChooseSubjectsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);//화면 로드

        arrayList = new ArrayList<>();
        arrayList.add("웹 프로그래밍");
        arrayList.add("컴퓨터 프로그래밍2");
        arrayList.add("알고리즘");
        arrayList.add("창의 소프트웨어 설계");
        arrayList.add("모바일 소프트웨어 설계");
        arrayList.add("자료 구조");
        arrayList.add("선형대수학");

        final TextView text = (TextView)findViewById(R.id.choose_subjects_test);
        final Spinner spinner = (Spinner)findViewById(R.id.choose_subjects_spinner); //스피너 생성

        //arrayList를 Spinner에 추가해주기
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,arrayList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
        //      android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(adapter);


        CustomSelectBtn next_btn = (CustomSelectBtn)findViewById(R.id.choose_subjects_nextbtn);
        CustomSelectBtn back_btn = (CustomSelectBtn)findViewById(R.id.choose_subjects_backbtn);

        next_btn.setOnClickListener(new View.OnClickListener() { //다음으로 화면 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectTimeSetActivity.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() { //전으로 화면 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                startActivity(intent);
            }
        });
    }
/*
    @Override
    public void onBackPressed() {
        //화면에서 뒤로가기 방지
    }*/

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었음.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
