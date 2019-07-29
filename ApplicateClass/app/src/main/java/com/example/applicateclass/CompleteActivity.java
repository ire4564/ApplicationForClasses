package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.applicateclass.CustomView.CustomSelectBtn;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CompleteActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    private long lastTimeBackPressed;
    int Write, Grade, TimeSet, RestDay;
    String Days[] = new String[6];
    private List<CustomScheduleItem> subjects = new ArrayList<CustomScheduleItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        /**********RestDay 공강 요일 선택(월,화,수,목,금,무관)으로 Int형으로 차례대로 1 2 3 4 5 0 으로 표현하여 정보를 저장한다*******************/
        //계속 가지고 있는 정보
        Intent intent_info = getIntent(); //데이터 수신 (학년+ 학점) //다음 액티비티에도 포함하여 저장
        Write = intent_info.getExtras().getInt("Write"); //입력한 학점 받아옴
        Grade= intent_info.getExtras().getInt("Grade"); //선택한 grade1, grade2...
        TimeSet= intent_info.getExtras().getInt("TimeSet"); //선택한 시간대(오전 오후 무관 1 2 0 )

        Boolean mon_key = intent_info.getExtras().getBoolean("mon_key");
        Boolean tue_key = intent_info.getExtras().getBoolean("tue_key");
        Boolean wed_key = intent_info.getExtras().getBoolean("wed_key");
        Boolean thr_key = intent_info.getExtras().getBoolean("thr_key");
        Boolean fri_key = intent_info.getExtras().getBoolean("fri_key");
        Boolean noting_key = intent_info.getExtras().getBoolean("noting_key");

        Days[0] = String.valueOf(mon_key);
        Days[1] = String.valueOf(tue_key);
        Days[2] = String.valueOf(wed_key);
        Days[3] = String.valueOf(thr_key);
        Days[4] = String.valueOf(fri_key);
        Days[5] = String.valueOf(noting_key);

        for(int i =0; i<6; i++) {
            if (Days[i] == "true") {
                if (i == 0) {  //월
                    RestDay = 1;
                } else if (i == 1) { //화
                    RestDay = 2;
                } else if (i == 2) { //수
                    RestDay = 3;
                } else if (i == 3) { //목
                    RestDay = 4;
                } else if (i == 4) { //금
                    RestDay = 5;
                } else if (i == 5) { //무관
                    RestDay = 0;
                }
            }
        }

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("all");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.v("데이터확인", "학점"+String.valueOf(Write));
        Log.v("데이터확인", "학년"+String.valueOf(Grade));
        Log.v("데이터확인", "시간대"+String.valueOf(TimeSet));
        Log.v("데이터확인", "공강"+String.valueOf(RestDay));
        /**********************
         *
         * 정보 받아오기 완료
         * Grade: 학년
         * Write: 학점
         * TimeSet: 오전/오후
         * RestDay: 공간 선택
         *
         ***********************/

        //테스트 코드
    //    TextView testdata = (TextView) findViewById(R.id.complete_textView) ; //테스트용 전환
    //    testdata.setText("Grade:" + Grade + " "+ "score: " +Write + " "+ "TimeSet: " +TimeSet + " "+ "RestDay: " +RestDay);



        //select1 Button
        CustomSelectBtn sel1 = (CustomSelectBtn) findViewById(R.id.complete_select1);
        sel1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        //select2 Button
        CustomSelectBtn sel2 = (CustomSelectBtn) findViewById(R.id.complete_select2);
        sel2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //select3 Button
        CustomSelectBtn sel3 = (CustomSelectBtn) findViewById(R.id.complete_select3);
        sel3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    public boolean getPerferenceBoolean(String key) { //데이터 불러오기(확인용)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getBoolean(key,false);
    }
    private void showData(DataSnapshot dataSnapshot){
       for (DataSnapshot keys : dataSnapshot.getChildren()){
           CustomScheduleItem customScheduleItem = keys.getValue(CustomScheduleItem.class);
           subjects.add(customScheduleItem);
           Log.v("데이터", String.valueOf(customScheduleItem.getTitle()));
       }
       subjects.size();
        Log.v("데이터", String.valueOf(subjects.size()));
    }
    @Override
    public void onBackPressed() { //화면에서 경고 메세지 띄우고 뒤로 가기
        //super.onBackPressed();
        if(System.currentTimeMillis() - lastTimeBackPressed < 2000){
            Intent intent = new Intent(
                    getApplicationContext(),
                    RestDayActivity.class);
            startActivity(intent); //종료시 오류나는 것 고치기
            overridePendingTransition(0, 0);
            return;
        }
        Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 기존에 생성된 시간표가 사라집니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
