package com.example.applicateclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.ChooseSubjects.ChooseSubjectsActivity;
import com.example.applicateclass.ChooseSubjects.SubjectSet;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HowMuchActivity extends AppCompatActivity implements Runnable{
    public final String PREFERENCE = "com.example.applicateclass"; //저장, 불러오기 위한
    public String write_score = "write_score";
    private Context context;
    public int Grade;
    List<CustomScheduleItem> subject = new ArrayList<>();
    List<SubjectSet> subjectSets = new ArrayList<>();
    String[] subject_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howmuch);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.v("데이터","여기오긴오냐");
                        for(DataSnapshot keys : dataSnapshot.getChildren()){
                            if(keys.getKey().equals(String.valueOf(Grade))){
                                showData(keys);
                                onSaveData(subject);
                                List<String> subject_list = new ArrayList<>();
                                for(int i=0; i<subject.size();i++){
                                    CustomScheduleItem customScheduleItem  = subject.get(i);
                                    subject_list.add(customScheduleItem.getTitle()+" "+customScheduleItem.getClassnumber()+" "+customScheduleItem.getSub()+customScheduleItem.toString());
                                    Log.v("데이터",customScheduleItem.getTitle()+" "+customScheduleItem.getClassnumber()+" "+customScheduleItem.getSub());
                                }
                                subject_select = subject_list.toArray(new String[0]);
                                onSaveData_list(subject_list);
                            }



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        th.start();
        String grade[] = new String[4];

        /**********Grade는 1~4학년을 숫자 Grade: 1, 2, 3, 4로 표현하여 정보를 저장한다*******************/

        Intent intent_info = getIntent(); //데이터 수신 (학년 선택 수신)
        Boolean grade1_key = intent_info.getExtras().getBoolean("grade1_key");
        Boolean grade2_key= intent_info.getExtras().getBoolean("grade2_key");
        Boolean grade3_key= intent_info.getExtras().getBoolean("grade3_key");
        Boolean grade4_key= intent_info.getExtras().getBoolean("grade4_key");

        //받아온 정보 넣기(boolean->String으로 변환후 검사)
        grade[0] = String.valueOf(grade1_key); //1학년
        grade[1] = String.valueOf(grade2_key); //2학년
        grade[2] = String.valueOf(grade3_key); //3학년
        grade[3] = String.valueOf(grade4_key); //4학년

        for(int i=0; i<4; i++){
            if(grade[i] == "true"){ //키값이 트루가 된다면
                    if(i == 0){ //1학년일때
                        Grade = 1;
                    } else if(i == 1){ //2학년일때
                        Grade = 2;
                    } else if(i == 2){
                        Grade = 3;
                    } else if(i == 3){
                        Grade = 4;
                    }
            }
        }
        //이 정보만 다음에 같이 전달

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
                        setPreferenceInt(write_score, Integer.parseInt(score));//정보 받기
                        Intent intent = new Intent(
                                getApplicationContext(),
                                ChooseSubjectsActivity.class);
                        intent.putExtra("Write", getPerferenceInt(write_score)); //정보전송 -> 몇학점인지(int)
                        intent.putExtra("Grade", Grade); //정보 전송 -> 몇학년인지(int)
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                } else { //공백일때
                    Toast.makeText(HowMuchActivity.this, "입력란에 원하는 학점을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }



    public void setPreferenceInt(String key, int value){ //학점 점수 저장(int)
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getPerferenceInt(String key) { //데이터 불러오기(확인용) ->integer
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getInt(key,0);
    }

    @Override
    public void onBackPressed() { //화면에서 뒤로가기를 눌렀을 때 변수 초기화
        write_score = "write_score";
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }


    @Override
    public void run() {

    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot keys : dataSnapshot.getChildren()){
            CustomScheduleItem customScheduleItem = keys.getValue(CustomScheduleItem.class);
            subject.add(customScheduleItem);
        }
    }
    private void Adddata(CustomScheduleItem item){
        for (int i=0; i<subjectSets.size();i++){
            if(subjectSets.get(i).getName().equals(item.getTitle())){
                subjectSets.get(i).getSubjectsArray().add(item);
                return;
            }
        }
        List<CustomScheduleItem> items = new ArrayList<>();
        SubjectSet set = new SubjectSet(item.getTitle(),items,item.getCredit());

    }
    private void onSaveData(List<CustomScheduleItem> timelist) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<CustomScheduleItem>>(){}.getType();
        String json = gson.toJson(timelist,listType);
        SharedPreferences sharedPreferences = getSharedPreferences("choose",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("json",json);
        editor.commit();
    }
    private void onSaveData_list(List<String> values){
        SharedPreferences prefs = getSharedPreferences("choose",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString("string", a.toString());
        } else {
            editor.putString("string", null);
        }
        editor.commit();
    }
}
