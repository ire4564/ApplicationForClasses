package com.example.applicateclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.applicateclass.CustomView.CustomSlideBar;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.example.applicateclass.TimeTable.CustomTimeTable;
import com.example.applicateclass.TimeTable.CustomTimeset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Gson gson = new GsonBuilder().create();


    static public CustomTimeTable customTimeTable;
    static public CustomSlideBar customSlideBar;
    TextView       tv_openBtn;
    TextView       tv_commit;
    BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);
        Intent intent_info = getIntent();
        int mode = intent_info.getIntExtra("mode",0);

        String selectednumber = intent_info.getExtras().getString("select");

        backPressCloseHandler = new BackPressCloseHandler(this);

        customTimeTable = (CustomTimeTable)findViewById(R.id.edit_schedule_main_table);
        customSlideBar = (CustomSlideBar)findViewById(R.id.edit_slide);

        tv_openBtn = (TextView)findViewById(R.id.edit_schedule_underTab);
        tv_commit =(TextView)findViewById(R.id.edit_commit);

        tv_openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customSlideBar.open();
            }
        });


        if(mode==1){
            customTimeTable.setEditAble(false);
            getCompleteTimeTable();
            tv_commit.setText("수 정");
            tv_openBtn.setText("수업 리스트");

            customSlideBar.setEditable(false);
            tv_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCompleteTimeTable();
                    finish();
                    Intent intent = new Intent(MainActivity.this , MainActivity.class);
                    intent.putExtra("mode",2);
                    startActivity(intent);
                }
            });
        }
        else if(mode == 2){
            customTimeTable.setEditAble(true);
            getCompleteTimeTable();
            tv_commit.setText("완 료");

            tv_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCompleteTimeTable();
                    finish();
                    Intent intent = new Intent(MainActivity.this , MainActivity.class);
                    intent.putExtra("mode",1);
                    startActivity(intent);
                }
            });
        }
        else{
            customTimeTable.setEditAble(true);
            onSearchData(customTimeTable, selectednumber);

            tv_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCompleteTimeTable();
                    finish();
                    Intent intent = new Intent(MainActivity.this , MainActivity.class);
                    intent.putExtra("mode",1);
                    startActivity(intent);
                }
            });
        }


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
        Log.v("데이터확인", String.valueOf(datas.size()));
        for(CustomScheduleItem i : datas) {
            Log.e("asd",i.getTitle()+" "+i.getTimelist().get(0).getDay());
            customTimeTable.addTime(i);
        }

    }
    private void setCompleteTimeTable(){
        SharedPreferences sf = getSharedPreferences("check",MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        ArrayList<CustomScheduleItem>timeTable = customTimeTable.getScheduleItemArrayList();

        editor.putString("timetable",gson.toJson(timeTable));
        editor.commit();


    }
    private void getCompleteTimeTable(){
        SharedPreferences sf = getSharedPreferences("check",MODE_PRIVATE);
        Type listType = new TypeToken<ArrayList<CustomScheduleItem>>() {
        }.getType();
        List<CustomScheduleItem> datas  = gson.fromJson(sf.getString("timetable",""),listType);
        for(CustomScheduleItem i : datas) {
            customTimeTable.addTime(i);
        }


    }
    @Override
    public void onBackPressed() {
        if(customSlideBar.isOn())
            customSlideBar.close();
        else{
            backPressCloseHandler.onBackPressed();
        }
    }
}
