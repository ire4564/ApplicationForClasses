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
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    private boolean isBoolean = false;
    int Write, Grade;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);//화면 로드

        /**********Write는 학점으로 Int형으로 표현하여 정보를 저장한다*******************/

        Intent intent_info = getIntent(); //데이터 수신 (학년+ 학점) //다음 액티비티에도 포함하여 저장
        Write = intent_info.getExtras().getInt("Write"); //입력한 학점 받아옴
        Grade= intent_info.getExtras().getInt("Grade"); //선택한 grade1, grade2...

        //시간표 정보 추가하기
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
                intent.putExtra("subject",arrayList); //체크박스로 선택한 객체만 넘어오게 해야 함
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() { //전으로 화면 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                //subjectarray를 초기화
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //화면에서 뒤로가기 방지
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었음.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
