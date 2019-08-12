package com.example.applicateclass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(), 2000);
    }

    private class splashhandler implements Runnable {
        public void run() {
            Gson gson = new Gson();
            SharedPreferences sf = getSharedPreferences("check",MODE_PRIVATE);
            Type listType = new TypeToken<ArrayList<CustomScheduleItem>>() {
            }.getType();
            List<CustomScheduleItem> datas  = gson.fromJson(sf.getString("timetable",""),listType);

            if(datas.size()==0) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        DoctoroActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.fadeout);
                SplashActivity.this.finish(); //로딩페이지 activity stack에서 제거
            }
            else{
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class);
                intent.putExtra("mode",1);
                startActivity(intent);
                overridePendingTransition(0, R.anim.fadeout);
                SplashActivity.this.finish(); //로딩페이지 activity stack에서 제거
            }
        }
    }
    @Override
    public void onBackPressed() {}
    //초반 화면에서 버튼 못 누르게 설정
}
