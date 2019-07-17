package com.example.bottomup_school_timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void auto_btn(View view) { //auto_making
        Intent auto = new Intent(this, Select_grade.class);
        startActivity(auto);
    }

    public void dire_btn(View view) { //direct_making
        Intent direct = new Intent(this, Select_grade.class);
        startActivity(direct);
    }
}
