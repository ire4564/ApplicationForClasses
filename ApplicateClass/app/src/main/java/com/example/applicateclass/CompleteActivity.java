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
import com.example.applicateclass.TimeTable.CustomTimeset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class CompleteActivity extends AppCompatActivity {
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    private long lastTimeBackPressed;
    int Write, Grade, TimeSet, RestDay;
    int essential_subjects_credit;
    String Days[] = new String[6];
    private Gson gson;
    private List<CustomScheduleItem> subjects = new ArrayList<CustomScheduleItem>();
    private List<CustomScheduleItem> culturesubjects = new ArrayList<CustomScheduleItem>();
    private List<CustomScheduleItem> essential_subjects = new ArrayList<>();
    private List<CustomScheduleItem> first_subjects = new ArrayList<>();
    private List<CustomScheduleItem> second_subjects = new ArrayList<>();
    private List<CustomScheduleItem> third_subjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        /**********RestDay 공강 요일 선택(월,화,수,목,금,무관)으로 Int형으로 차례대로 1 2 3 4 5 0 으로 표현하여 정보를 저장한다*******************/
        //계속 가지고 있는 정보
        Intent intent_info = getIntent(); //데이터 수신 (학년+ 학점) //다음 액티비티에도 포함하여 저장
        final ArrayList<String> subject = (ArrayList<String>) intent_info.getSerializableExtra("subject"); //과목명이 담긴 배열
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
        essential_subjects_credit = count_essential_credit();
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("들어오나요?","ㅇㅇ");
                for(DataSnapshot keys : dataSnapshot.getChildren()){
                    if(keys.getKey().equals(String.valueOf(Grade))){
                        showData(keys);
                    }
                    if (keys.getKey().equals(String.valueOf(0))){
                        showCultureData(keys);
                    }
                }
                istimeavailable(subjects);
                istimeavailable(culturesubjects);
                culturesubjects.size();
                subjects.addAll(culturesubjects);
                Write -= essential_subjects_credit;
                make_timetable(Write);
                SharedPreferences sf = getSharedPreferences("check",MODE_PRIVATE);// check -> empty 가 no면 데이터가 이미 존재한다는 거
                String check_subejcts = sf.getString("empty","");
                Log.v("데이터확인",check_subejcts+"!!!!!!");
                if(check_subejcts.equals("")){
                    SharedPreferences.Editor editor =sf.edit();
                    editor.putString("empty","no");
                    editor.commit();
                    onSaveData(first_subjects);
                    onSaveData(second_subjects);
                    onSaveData(third_subjects);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                intent.putExtra("select", "1");
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
                intent.putExtra("select", "2");
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
                intent.putExtra("select", "3");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    private void onSaveData(List<CustomScheduleItem> timelist) {
        gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<CustomScheduleItem>>(){}.getType();
        String json = gson.toJson(timelist,listType);
        SharedPreferences sharedPreferences;
        if(timelist.toString().equals(first_subjects.toString())){
            sharedPreferences = getSharedPreferences("1",MODE_PRIVATE);
        }
        else if (timelist.toString().equals(second_subjects.toString())){
            sharedPreferences = getSharedPreferences("2",MODE_PRIVATE);
        }
        else {
            sharedPreferences = getSharedPreferences("3",MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("contacts",json);
        editor.commit();
    }


    public boolean getPerferenceBoolean(String key) { //데이터 불러오기(확인용)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getBoolean(key,false);
    }
    private void showData(DataSnapshot dataSnapshot){
       for (DataSnapshot keys : dataSnapshot.getChildren()){
           CustomScheduleItem customScheduleItem = keys.getValue(CustomScheduleItem.class);
           subjects.add(customScheduleItem);
          // Log.v("데이터", String.valueOf(customScheduleItem.getTitle()));
       }
    }
    private void showCultureData(DataSnapshot dataSnapshot){
        for (DataSnapshot keys : dataSnapshot.getChildren()){
            CustomScheduleItem customScheduleItem = keys.getValue(CustomScheduleItem.class);
            culturesubjects.add(customScheduleItem);
            //Log.v("데이터", String.valueOf(customScheduleItem.getTitle()));
        }
    }
    @Override
    public void onBackPressed() { //화면에서 뒤로가기 방지
        //super.onBackPressed();
        if(System.currentTimeMillis() - lastTimeBackPressed < 2000){
            finish();
            return;
        }
        Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

    private void istimeavailable(List<CustomScheduleItem> subjects) {
        Iterator<CustomScheduleItem> item = subjects.iterator();
        while(item.hasNext()){
            CustomScheduleItem scheduleItem = item.next();
            if ( !checktimelist(scheduleItem,TimeSet,RestDay)){
                item.remove();
            }
        }
        subjects.size();

    }

    private boolean checktimelist(CustomScheduleItem subject, int timeSet ,int restDay) {
        ArrayList<CustomTimeset> timelist = subject.getTimelist();
        for(int i=0; i<timelist.size();i++){
            CustomTimeset time = timelist.get(i);
            if(timeSet ==1 && (time.getStartTime()>=1300 || restDay==time.getDay())){
                return false;
            }
            else if( timeSet ==2 && (time.getStartTime()<=1200 || restDay==time.getDay())){
                return false;
            }
            else {
                if( timeSet==0 && restDay==time.getDay()){
                    return false;
                }
            }
        }
        if(!isaddtosubjects(essential_subjects,subject)){
            return false;
        }
        return true;

    }

    private boolean isaddtosubjects(List<CustomScheduleItem> essential_subjects, CustomScheduleItem addsubject) {

        for (int i=0; i<essential_subjects.size();i++){
            CustomScheduleItem subject = essential_subjects.get(i);
            ArrayList<CustomTimeset> timesets = subject.getTimelist();
            if(subject.getSubjectnumber().equals(addsubject.getSubjectnumber())){
                return false;
            }
            for (int j=0; j<timesets.size();j++){
                CustomTimeset timeset = timesets.get(j);
                ArrayList<CustomTimeset> addsubject_list = addsubject.getTimelist();

                for (int k=0; k<addsubject_list.size();k++){
                    CustomTimeset addsubject_timeset = addsubject_list.get(k);
                    if((timeset.getDay()==addsubject_timeset.getDay())&&(( timeset.getStartTime()<=addsubject_timeset.getStartTime() && addsubject_timeset.getStartTime()<=timeset.getEndTime())
                            || (addsubject_timeset.getEndTime()<=timeset.getEndTime() && timeset.getStartTime()<=addsubject_timeset.getEndTime()))){
                        return false;
                    }

                }
            }
        }
        return true;
    }

    private int count_essential_credit() {
        int result = 0;
        for(int i=0; i<essential_subjects.size();i++){
            CustomScheduleItem item = essential_subjects.get(i);
            result+= item.getCredit();
        }
        return result;
    }
    private void make_timetable(int write) {
        int Write2=Write,Write3 = Write;
        first_subjects.addAll(essential_subjects);
        second_subjects.addAll(essential_subjects);
        third_subjects.addAll(essential_subjects);
        Collections.shuffle(subjects);
        for (int i=0; i<subjects.size();i++){
            if(Write>=subjects.get(i).getCredit() && isaddtosubjects(first_subjects,subjects.get(i))){
                first_subjects.add(subjects.get(i));
                Write-=subjects.get(i).getCredit();
                continue;
            }
            if(Write2>=subjects.get(i).getCredit() && isaddtosubjects(second_subjects,subjects.get(i))){
                second_subjects.add(subjects.get(i));
                Write2-=subjects.get(i).getCredit();
                continue;
            }
            if(Write3>=subjects.get(i).getCredit() && isaddtosubjects(third_subjects,subjects.get(i))){
                third_subjects.add(subjects.get(i));
                Write3-=subjects.get(i).getCredit();
                continue;
            }
        }
    }


}
