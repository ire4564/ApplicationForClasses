package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.example.applicateclass.CustomView.CustomSelectBtn;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DoctoroActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String auto_key = "auto_key";
    public String direct_key = "direct_key";
    private boolean isBoolean = false;
    private List<CustomScheduleItem> subjects = new ArrayList<CustomScheduleItem>();
    private List<CustomScheduleItem> culturesubjects = new ArrayList<CustomScheduleItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctoro);

        //data Test
        //final TextView result = (TextView)findViewById(R.id.docotoro_test);

        //Select auto and direct
        CustomSelectBtn auto = (CustomSelectBtn) findViewById(R.id.doctoro_auto);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(auto_key, !isBoolean); //true

                Intent intent = new Intent( //화면 전환
                        getApplicationContext(),
                        SelectGradeActivity.class);
                intent.putExtra("auto_key", getPerferenceBoolean(auto_key)); //키확인
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        CustomSelectBtn direct = (CustomSelectBtn) findViewById(R.id.doctoro_direct);
        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(direct_key, !isBoolean); //true
                loadClasses();
                Intent intent = new Intent( //화면 전환 (바로 시간표 만들기로 전환)
                        getApplicationContext(),
                        MainActivity.class);
                intent.putExtra("select", "");
                finish();
                startActivity(intent); //다음 화면으로
                overridePendingTransition(0, 0);
            }
        });

    }

    public void setPreference(String key, boolean value) { //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getPerferenceBoolean(String key) { //데이터 불러오기(확인용)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    @Override
    public void onBackPressed() { //화면에서 뒤로가기를 눌렀을 때 변수 초기화
        auto_key = "auto_key";
        direct_key = "direct_key";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    private void loadClasses() {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("들어오나요?", "ㅇㅇ");
                for (DataSnapshot keys : dataSnapshot.getChildren()) {
                    if (keys.getKey().equals(String.valueOf(1))) {
                        showData(keys);
                    }
                    if (keys.getKey().equals(String.valueOf(2))) {
                        showData(keys);
                    }
                    if (keys.getKey().equals(String.valueOf(3))) {
                        showData(keys);
                    }
                    if (keys.getKey().equals(String.valueOf(4))) {
                        showData(keys);
                    }
                    if (keys.getKey().equals(String.valueOf(0))) {
                        showCultureData(keys);
                    }
                }
                saveClasses();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot keys : dataSnapshot.getChildren()) {
            CustomScheduleItem customScheduleItem = keys.getValue(CustomScheduleItem.class);
            subjects.add(customScheduleItem);
        }
    }

    private void showCultureData(DataSnapshot dataSnapshot) {
        for (DataSnapshot keys : dataSnapshot.getChildren()) {
            CustomScheduleItem customScheduleItem = keys.getValue(CustomScheduleItem.class);
            culturesubjects.add(customScheduleItem);
        }
    }

    private void saveClasses() {

        Gson gson = new Gson();
        SharedPreferences sf = getSharedPreferences("check", MODE_PRIVATE);// check -> empty 가 no면 데이터가 이미 존재한다는 거
        SharedPreferences.Editor editor = sf.edit();

        editor.putString("subject", gson.toJson(subjects));
        editor.putString("culture", gson.toJson(culturesubjects));

        editor.commit();


    }

}
