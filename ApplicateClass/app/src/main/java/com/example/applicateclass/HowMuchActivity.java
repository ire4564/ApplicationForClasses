package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class HowMuchActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String write_score = "write_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howmuch);

        //Write down score
         final EditText write = (EditText) findViewById(R.id.howmuch_writescore);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(write_score, Integer.parseInt(write.getText().toString()));//정보 받기
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectTimeSetActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //next icon Button
        ImageView Next = (ImageView) findViewById(R.id.howmuch_nextbtn);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectTimeSetActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    public void setPreference(String key, int value){ //학점 점수 저장(int)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
