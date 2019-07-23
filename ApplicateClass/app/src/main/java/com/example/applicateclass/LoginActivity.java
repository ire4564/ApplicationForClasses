package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.applicateclass.CustomView.WhiteBtn;

public class LoginActivity extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;
    private long lastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(this.getClass().getName(), "onCreate 실행"); //verbose
        Log.w(this.getClass().getName(), "onCreate 실행"); //warning
        Log.i(this.getClass().getName(), "onCreate 실행"); //info
        Log.e(this.getClass().getName(), "onCreate 실행"); //error


        //Login Button
        WhiteBtn Login = (WhiteBtn) findViewById(R.id.login_btn);
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        DoctoroActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    @Override
    public void onBackPressed() { //화면에서 뒤로가기 방지
        //super.onBackPressed();
        if(System.currentTimeMillis() - lastTimeBackPressed < 2000){
            finish();
            return;
        }
        Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
