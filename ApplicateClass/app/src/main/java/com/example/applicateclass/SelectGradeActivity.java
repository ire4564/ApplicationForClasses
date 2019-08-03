package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

public class SelectGradeActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String grade1_key = "grade1_key";
    public String grade2_key = "grade2_key";
    public String grade3_key = "grade3_key";
    public String grade4_key = "grade4_key";
    private boolean isBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        SharedPreferences sf = getSharedPreferences("check",MODE_PRIVATE);
        SharedPreferences.Editor editor =sf.edit();
        editor.putString("empty","");
        editor.commit();
        Intent intent_info = getIntent(); //데이터 수신 확인 (그런데 시간표 자동 수동 선택하는 건 굳이 정보를 넘겨주지 않아도 되므로 예시 사용/실사용 패스해도 됨)
        Boolean auto_key = intent_info.getExtras().getBoolean("auto_key");
        Boolean direct_key= intent_info.getExtras().getBoolean("direct_key");

     //   TextView testdata = (TextView) findViewById(R.id.grad_what) ; //테스트용 전환
      //  testdata.setText("direct key:" +direct_key + " "+ "auto key: " +auto_key);

        //Select Grade1 Button
        CustomSelectBtn Grade1 = (CustomSelectBtn) findViewById(R.id.grade_grade1);
        Grade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(grade1_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                intent.putExtra("grade1_key", getPerferenceBoolean(grade1_key)); //정보 넘겨주기
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Select Grade2 Button
        CustomSelectBtn Grade2 = (CustomSelectBtn) findViewById(R.id.grade_grade2);
        Grade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(grade2_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                intent.putExtra("grade2_key", getPerferenceBoolean(grade2_key)); //정보 넘겨주기
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Select Grade3 Button
        CustomSelectBtn Grade3 = (CustomSelectBtn) findViewById(R.id.grade_grade3);
        Grade3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(grade3_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                intent.putExtra("grade3_key", getPerferenceBoolean(grade3_key)); //정보 넘겨주기
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Select Grade4 Button
        CustomSelectBtn Grade4 = (CustomSelectBtn) findViewById(R.id.grade_grade4);
        Grade4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(grade4_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                intent.putExtra("grade4_key", getPerferenceBoolean(grade4_key)); //정보 넘겨주기
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    public void setPreference(String key, boolean value){ //데이터 저장 함수
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getPerferenceBoolean(String key) { //데이터 불러오기(확인용)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getBoolean(key,false);
    }
    @Override
    public void onBackPressed() { //화면에서 뒤로가기를 눌렀을 때 변수 초기화
        grade1_key = "grade1_key";
        grade2_key = "grade2_key";
        grade3_key = "grade3_key";
        grade4_key = "grade4_key";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

}
