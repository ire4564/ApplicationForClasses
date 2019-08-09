
package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.ChooseSubjects.SubjectSet;
import com.example.applicateclass.CustomView.CustomSelectBtn;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SelectTimeSetActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String am_key = "am_key";
    public String pm_key = "pm_key";
    public String anytime_key = "anytime_key";
    private boolean isBoolean = false;
    int Write, Grade;
    List<SubjectSet> allsubject = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_timeset);


        /**********Write는 학점으로 Int형으로 표현하여 정보를 저장한다*******************/

        Intent intent_info = getIntent(); //데이터 수신 (학년+ 학점) //다음 액티비티에도 포함하여 저장
        Write = intent_info.getExtras().getInt("Write"); //입력한 학점 받아옴
        Grade= intent_info.getExtras().getInt("Grade"); //선택한 grade1, grade2...
        allsubject = takeAlldata();


        //am Button
        final CustomSelectBtn am = (CustomSelectBtn) findViewById(R.id.timeset_am);
        am.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(am_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
                intent.putExtra("am_key", getPerferenceBoolean(am_key)); //정보전송 -> 오전 true
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
             //   intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //pm Button
        CustomSelectBtn pm = (CustomSelectBtn) findViewById(R.id.timeset_pm);
        pm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(pm_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
                intent.putExtra("pm_key", getPerferenceBoolean(pm_key)); //정보전송 -> 오후 true
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //nothing Button
        CustomSelectBtn nothing = (CustomSelectBtn) findViewById(R.id.timeset_noting);
        nothing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(anytime_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
                intent.putExtra("anytime_key", getPerferenceBoolean(anytime_key)); //정보전송 -> 상관없음 true
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void onSaveData() {

    }

    private List<SubjectSet> takeAlldata(){
        Gson gson =  new GsonBuilder().create();;
        SharedPreferences sp = getSharedPreferences("choose", MODE_PRIVATE);
        String strContact = sp.getString("readytotime", "");
        Type listType = new TypeToken<List<SubjectSet>>() {}.getType();
        List<SubjectSet> datas = gson.fromJson(strContact, listType);
        return datas;
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
        am_key = "am_key";
        pm_key = "pm_key";
        anytime_key = "anytime_key";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
