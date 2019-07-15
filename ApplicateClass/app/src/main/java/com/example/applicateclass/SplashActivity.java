package com.example.applicateclass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
            startActivity(new Intent(getApplication(), SelectGradeActivity.class)); //로딩후 페이지 이동
            SplashActivity.this.finish(); //로딩페이지 activity stack에서 제거
        }
    }
    @Override
    public void onBackPressed() {}
    //초반 화면에서 버튼 못 누르게 설정
}
