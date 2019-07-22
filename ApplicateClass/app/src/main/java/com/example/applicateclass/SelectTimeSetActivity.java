
package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.applicateclass.CustomView.CustomSelectBtn;

public class SelectTimeSetActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String am_key = "am_key";
    public String pm_key = "pm_key";
    public String anytime_key = "anytime_key";
    private boolean isBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_timeset);

        //am Button
        final CustomSelectBtn am = (CustomSelectBtn) findViewById(R.id.timeset_am);
        am.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(am_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
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
