package com.example.applicateclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HowMuchActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String write_score = "write_score";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howmuch);

        //Write down score
        final EditText write = (EditText) findViewById(R.id.howmuch_writescore);

        //next icon Button
        ImageView Next = (ImageView) findViewById(R.id.howmuch_nextbtn);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력 값 체크
                if(write.getText().toString().length() != 0){ //공백이 아닐 때
                    String score = write.getText().toString();
                    if(Integer.parseInt(score) > 21){ //21학점 이상은 불가
                        //경고 알림창 띄우기
                        Toast.makeText(HowMuchActivity.this, "21학점이 최대 학점입니다.\n21학점 이하로 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else if(Integer.parseInt(score) < 2){ //2학점 이하는 불가
                        //경고 알림창 띄우기
                        Toast.makeText(HowMuchActivity.this, "2학점이 최소 학점입니다.\n2학점 이상으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        setPreference(write_score, Integer.parseInt(score));//정보 받기
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SelectTimeSetActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                } else { //공백일때
                    Toast.makeText(HowMuchActivity.this, "입력란에 원하는 학점을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void setPreference(String key, int value){ //학점 점수 저장(int)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    @Override
    public void onBackPressed() { //화면에서 뒤로가기를 눌렀을 때 변수 초기화
        write_score = "write_score";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
