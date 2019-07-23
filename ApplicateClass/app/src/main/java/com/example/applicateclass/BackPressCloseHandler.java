package com.example.applicateclass;

import android.app.Activity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BackPressCloseHandler {
    //마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    //첫번째 뒤로가기 버튼을 누를 떄 표시
    private Toast toast;
    //종료시킬 activity
    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }
    public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }
    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }


}
