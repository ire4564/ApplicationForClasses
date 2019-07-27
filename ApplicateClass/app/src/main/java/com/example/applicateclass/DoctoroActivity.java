package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import com.example.applicateclass.CustomView.CustomSelectBtn;

    public class DoctoroActivity extends AppCompatActivity {
        public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
        public String auto_key = "auto_key";
        public String direct_key = "direct_key";
        private boolean isBoolean = false;

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
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        CustomSelectBtn direct = (CustomSelectBtn) findViewById(R.id.doctoro_direct);
        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreference(direct_key, !isBoolean); //true

              Intent intent = new Intent( //화면 전환 (바로 시간표 만들기로 전환)
                        getApplicationContext(),
                        MainActivity.class);
                startActivity(intent); //다음 화면으로
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
         auto_key = "auto_key";
         direct_key = "direct_key";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

}
