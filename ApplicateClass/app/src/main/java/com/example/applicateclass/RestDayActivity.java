package com.example.applicateclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

import java.util.ArrayList;

public class RestDayActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String mon_key = "mon_key";
    public String tue_key = "tue_key";
    public String wed_key = "wed_key";
    public String thr_key = "thr_key";
    public String fri_key = "fri_key";
    public String noting_key = "noting_key";
    private boolean isBoolean = false;

    int Write, Grade;
    int TimeSet;
    String TimeSelect[] = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restday);

        /**********TimeSet 시간대 선택(오전, 오후, 상관없음)으로 Int형으로 오전:1 오후:2 상관없음:0 표현하여 정보를 저장한다*******************/
        //계속 가지고 있는 정보
        Intent intent_info = getIntent(); //데이터 수신 (학년+ 학점) //다음 액티비티에도 포함하여 저장
     //  final ArrayList<String> subject = (ArrayList<String>) intent_info.getSerializableExtra("subject"); //과목명이 담긴 배열
        Write = intent_info.getExtras().getInt("Write"); //입력한 학점 받아옴
        Grade= intent_info.getExtras().getInt("Grade"); //선택한 grade1, grade2...

        //새로 받은 키
        Boolean am_key = intent_info.getExtras().getBoolean("am_key");
        Boolean pm_key= intent_info.getExtras().getBoolean("pm_key");
        Boolean anytime_key= intent_info.getExtras().getBoolean("anytime_key");

        //true인 값 걸러내기
        TimeSelect[0] = String.valueOf(am_key);
        TimeSelect[1] = String.valueOf(pm_key);
        TimeSelect[2] = String.valueOf(anytime_key);

        for(int i=0; i<3; i++){
            if(TimeSelect[i] == "true"){
                if(i == 0){ //오전
                    TimeSet = 1;
                } else if(i == 1){ //오후
                    TimeSet = 2;
                } else if(i == 2){ ///상관없음
                    TimeSet = 0;
                }
            }
        }

       // TextView testdata = (TextView) findViewById(R.id.restday_test) ; //테스트용 전환
       // testdata.setText("Grade:" + Grade + " "+ "score: " +Write + " "+ "TimeSet: " +TimeSet);

        //mon Button
        CustomSelectBtn mon = (CustomSelectBtn) findViewById(R.id.restday_mon);
        mon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(mon_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                intent.putExtra("mon_key", getPerferenceBoolean(mon_key)); //정보전송 ->  월
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
            //    intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                intent.putExtra("TimeSet", TimeSet); //정보 전송 -> 시간대 선택(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //tue Button
        CustomSelectBtn tue = (CustomSelectBtn) findViewById(R.id.restday_tue);
        tue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(tue_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                intent.putExtra("tue_key", getPerferenceBoolean(tue_key)); //정보전송 ->  화
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
               // intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                intent.putExtra("TimeSet", TimeSet); //정보 전송 -> 시간대 선택(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //wed Button
        CustomSelectBtn wed = (CustomSelectBtn) findViewById(R.id.restday_wed);
        wed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(wed_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                intent.putExtra("wed_key", getPerferenceBoolean(wed_key)); //정보전송 ->  수
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
            //    intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                intent.putExtra("TimeSet", TimeSet); //정보 전송 -> 시간대 선택(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //thr Button
        CustomSelectBtn thr = (CustomSelectBtn) findViewById(R.id.restday_thr);
        thr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(thr_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                intent.putExtra("thr_key", getPerferenceBoolean(thr_key)); //정보전송 ->  목
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
             //   intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                intent.putExtra("TimeSet", TimeSet); //정보 전송 -> 시간대 선택(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //fri Button
        CustomSelectBtn fri = (CustomSelectBtn) findViewById(R.id.restday_fri);
        fri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(fri_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                intent.putExtra("fri_key", getPerferenceBoolean(fri_key)); //정보전송 ->  금
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
            //    intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                intent.putExtra("TimeSet", TimeSet); //정보 전송 -> 시간대 선택(int)
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //fine Button
        CustomSelectBtn fine = (CustomSelectBtn) findViewById(R.id.restday_ok);
        fine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setPreference(noting_key, !isBoolean); //true
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                intent.putExtra("noting_key", getPerferenceBoolean(noting_key)); //정보전송 ->  공강해당없음
                intent.putExtra("Write", Write); //정보 전송 -> 학점(int)
                intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
          //      intent.putExtra("subject", subject); //정보 전송 -> 어떤 과목을 선택했는지
                intent.putExtra("TimeSet", TimeSet); //정보 전송 -> 시간대 선택(int)
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

    public boolean getPerferenceBoolean(String key) { //데이터 불러오기(확인용)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getBoolean(key,false);
    }

    @Override
    public void onBackPressed() { //화면에서 뒤로가기를 눌렀을 때 변수 초기화
        mon_key = "mon_key";
        tue_key = "tue_key";
        wed_key = "wed_key";
        thr_key = "thr_key";
        fri_key = "fri_key";
        noting_key = "noting_key";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
