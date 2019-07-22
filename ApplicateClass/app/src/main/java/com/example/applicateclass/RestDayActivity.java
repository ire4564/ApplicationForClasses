package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

public class RestDayActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String mon_key = "mon_key";
    public String tue_key = "tue_key";
    public String wed_key = "wed_key";
    public String thr_key = "thr_key";
    public String fri_key = "fri_key";
    public String noting_key = "noting_key";
    private boolean isBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restday);

        //mon Button
        CustomSelectBtn mon = (CustomSelectBtn) findViewById(R.id.restday_mon);
        mon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(mon_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //tue Button
        CustomSelectBtn tue = (CustomSelectBtn) findViewById(R.id.restday_tue);
        tue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(tue_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //wed Button
        CustomSelectBtn wed = (CustomSelectBtn) findViewById(R.id.restday_wed);
        wed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(wed_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //thr Button
        CustomSelectBtn thr = (CustomSelectBtn) findViewById(R.id.restday_thr);
        thr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(thr_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //fri Button
        CustomSelectBtn fri = (CustomSelectBtn) findViewById(R.id.restday_fri);
        fri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(fri_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //fine Button
        CustomSelectBtn fine = (CustomSelectBtn) findViewById(R.id.restday_ok);
        fine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(noting_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
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
}
