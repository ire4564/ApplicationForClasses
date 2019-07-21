package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

public class SelectGradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        //Select Grade1 Button
        CustomSelectBtn Grade1 = (CustomSelectBtn) findViewById(R.id.grade_grade1);
        Grade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Select Grade2 Button
        CustomSelectBtn Grade2 = (CustomSelectBtn) findViewById(R.id.grade_grade2);
        Grade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Select Grade3 Button
        CustomSelectBtn Grade3 = (CustomSelectBtn) findViewById(R.id.grade_grade3);
        Grade3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Select Grade4 Button
        CustomSelectBtn Grade4 = (CustomSelectBtn) findViewById(R.id.grade_grade4);
        Grade4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        HowMuchActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

}
