package com.example.applicateclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.example.applicateclass.TimeTable.CustomTimeTable;
import com.example.applicateclass.TimeTable.CustomTimeset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  CustomTimeTable customTimeTable = (CustomTimeTable)findViewById(R.id.main_table);
      //  customTimeTable.addTime(new CustomScheduleItem("title","sub",new CustomTimeset(1,900,1330))); //title 제목 sub 부제목,
                                                                                                                                            // 커스텀 타임셋 (개수 제한없음 , 리스트 배열가능)
                                                                                                                                            //day (월요일부터 1~ 금요일 5)
                                                                                                                                            //startTime hhmm 09시 30 -> 930
                                                                                                                                            //endTime hhmm 위와 동일
                                                                                                                                            //생성시 색깔 랜덤지정 (추후추가 예정

    }
}
