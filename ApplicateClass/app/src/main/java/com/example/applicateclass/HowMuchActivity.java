package com.example.applicateclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class HowMuchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howmuch);

        //Write down score
        EditText next = (EditText) findViewById(R.id.howmuch_writescore);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectTimeSetActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //next icon Button
        ImageView Next = (ImageView) findViewById(R.id.howmuch_nextbtn);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        SelectTimeSetActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
