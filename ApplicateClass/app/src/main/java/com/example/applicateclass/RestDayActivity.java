package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

public class RestDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restday);

        //mon Button
        CustomSelectBtn mon = (CustomSelectBtn) findViewById(R.id.restday_mon);
        mon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //tue Button
        CustomSelectBtn tue = (CustomSelectBtn) findViewById(R.id.restday_tue);
        tue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //wed Button
        CustomSelectBtn wed = (CustomSelectBtn) findViewById(R.id.restday_wed);
        wed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //thr Button
        CustomSelectBtn thr = (CustomSelectBtn) findViewById(R.id.restday_thr);
        thr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //fri Button
        CustomSelectBtn fri = (CustomSelectBtn) findViewById(R.id.restday_fri);
        fri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //fine Button
        CustomSelectBtn fine = (CustomSelectBtn) findViewById(R.id.restday_ok);
        fine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CompleteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
