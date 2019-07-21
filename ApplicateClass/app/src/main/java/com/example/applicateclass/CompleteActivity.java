package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicateclass.CustomView.CustomSelectBtn;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

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
}
