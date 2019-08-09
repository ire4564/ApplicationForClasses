package com.example.applicateclass.ChooseSubjects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.applicateclass.CustomView.CustomSelectBtn;
import com.example.applicateclass.HowMuchActivity;
import com.example.applicateclass.R;
import com.example.applicateclass.SelectTimeSetActivity;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.example.applicateclass.TimeTable.CustomTimeTable;
import com.example.applicateclass.TimeTable.CustomTimeset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChooseSubjectsActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    private boolean isBoolean = false;
    int Write, Grade;
    List<CustomScheduleItem> subject = new ArrayList<>(); //저장되어 다음으로 넘겨질 정보들
    /*final String[] subject_select = {"웹 프로그래밍","컴퓨터 프로그래밍3","알고리즘","창의 소프트웨어 설계","모바일 소프트웨어 설계",
                                    "인공지능", "선형대수학", "객체지향 설계", "컴퓨터 구조"};*/ //2번 String 타입의 배열 선언
    String[] subject_select = {};
    ArrayAdapter adapter;
    ListView listview;
    List<CustomScheduleItem> all_subject = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);//화면 로드
        /**********Write는 학점으로 Int형으로 표현하여 정보를 저장한다*******************/

        Intent intent_info = getIntent(); //데이터 수신 (학년+ 학점) //다음 액티비티에도 포함하여 저장
        Write = intent_info.getExtras().getInt("Write"); //입력한 학점 받아옴
        Grade= intent_info.getExtras().getInt("Grade"); //선택한 grade1, grade2...
        subject_select = getStringArrayPref().toArray(new String[0]);

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.simple_list_item, subject_select);
        listview = (ListView)findViewById(R.id.choose_subjects_List);
        listview.setAdapter(adapter);
        CustomSelectBtn next_btn = (CustomSelectBtn)findViewById(R.id.choose_subjects_nextbtn);
        CustomSelectBtn back_btn = (CustomSelectBtn)findViewById(R.id.choose_subjects_backbtn);

        next_btn.setOnClickListener(new View.OnClickListener() { //다음으로 화면 이동
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions(); //체크박스로 체크한 셀의 정보를 담고 있는 희소 논리 배열 얻어오기
                int count = adapter.getCount(); //전체 몇개인지 세기
                all_subject = takeAlldata();
                if(checkedItems.size()!=0){
                    for(int i=count-1; i>=0; i--){
                        if(checkedItems.get(i)){ //희소 논리 배열의 해당 인덱스가 선택되어 있다면
                            subject.add(all_subject.get(i)) ; //arrayList에 추가하기
                        }
                    }

                } /*else {
                    Toast.makeText(ChooseSubjectsActivity.this, "꼭 필수로 들을 과목을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }*/
                if(isnotconflict(subject)){
                    onSaveData(subject);
                    Intent intent = new Intent(
                            getApplicationContext(),
                            SelectTimeSetActivity.class);
                    intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                    intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                else{
                    subject = new ArrayList<>();
                }
                listview.clearChoices() ;
                adapter.notifyDataSetChanged();
                //화면 전환 및 정보 전송

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
}
