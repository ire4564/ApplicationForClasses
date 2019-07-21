
package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.applicateclass.CustomView.CustomSelectBtn;

public class SelectTimeSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_timeset);

        //am Button
        CustomSelectBtn am = (CustomSelectBtn) findViewById(R.id.timeset_am);
        am.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //pm Button
        CustomSelectBtn pm = (CustomSelectBtn) findViewById(R.id.timeset_pm);
        pm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //nothing Button
        CustomSelectBtn nothing = (CustomSelectBtn) findViewById(R.id.timeset_noting);
        nothing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        RestDayActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
