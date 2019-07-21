package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import com.example.applicateclass.CustomView.WhiteBtn;

public class LoginActivity extends AppCompatActivity {

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
}
