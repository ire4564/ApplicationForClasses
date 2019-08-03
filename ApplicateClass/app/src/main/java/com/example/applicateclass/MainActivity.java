package com.example.applicateclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.example.applicateclass.TimeTable.CustomTimeTable;
import com.example.applicateclass.TimeTable.CustomTimeset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Gson gson = new GsonBuilder().create();
    CustomTimeTable customTimeTable;
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_schedule);
        Intent intent_info = getIntent();
        String selectednumber = intent_info.getExtras().getString("select");
        customTimeTable = (CustomTimeTable)findViewById(R.id.edit_schedule_main_table);

        onSearchData(customTimeTable, selectednumber);
       // customTimeTable.addTime(new CustomScheduleItem("title", "sub", new CustomTimeset(1, 900, 1300, "sd")));//title 제목 sub 부제목,
        // 커스텀 타임셋 (개수 제한없음 , 리스트 배열가능)
        //day (월요일부터 1~ 금요일 5)
       // customTimeTable.addTime(new CustomScheduleItem("title", "sub", new CustomTimeset(1, 900, 1300, "sd"))); //title 제목 sub 부제목,
        // 커스텀 타임셋 (개수 제한없음 , 리스트 배열가능)
        //day (월요일부터 1~ 금요일 5)
        //startTime hhmm 09시 30 -> 930
        //endTime hhmm 위와 동일
        //생성시 색깔 랜덤지정 (추후추가 예정
    }

    private void onSearchData(CustomTimeTable customTimeTable, String selectenumber) {
        SharedPreferences sf = getSharedPreferences("check", MODE_PRIVATE);
        String check_subejcts = sf.getString("empty", "");
        Log.v("데이터확인00", check_subejcts + "!!!!!!");


        SharedPreferences sp = getSharedPreferences(selectenumber, MODE_PRIVATE);
        String strContact = sp.getString("contacts", "");

        Type listType = new TypeToken<ArrayList<CustomScheduleItem>>() {
        }.getType();
        List<CustomScheduleItem> datas = gson.fromJson(strContact, listType); // 여기 다 저장되어있으므로 반복문으로 처리하면 될듯
        for(CustomScheduleItem i : datas) {
            Log.e("asd",i.getTitle());
            customTimeTable.addTime(i);
        }




    }
}
