package com.example.applicateclass.CustomView;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.applicateclass.R;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.example.applicateclass.TimeTable.CustomTimeTable;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class CustomSlideBar extends LinearLayout {
    private boolean isOn;

    private View v;

    private TextView tv_down;

    private ListView ls;
    private ListViewAdapter adapter;

    private Animation upAnim;
    private Animation downAnim;

    private CustomSelectBtn majorBtn;
    private CustomSelectBtn cultureBtn;

    private ArrayList<CustomScheduleItem> subject = new ArrayList<>();
    private ArrayList<CustomScheduleItem> culture = new ArrayList<>();

    public CustomSlideBar(Context context) {
        super(context);
        initView();
    }
    public CustomSlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }
    public CustomSlideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView() {
        isOn = false;

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        v = li.inflate(R.layout.slide_buttom_tap, this, false);

        tv_down = (TextView)v.findViewById(R.id.slide_close);
        ls = (ListView)v.findViewById(R.id.slid_subject);
        majorBtn =(CustomSelectBtn)v.findViewById(R.id.slide_major);
        cultureBtn =(CustomSelectBtn)v.findViewById(R.id.slide_other);

        loadClasses();

        adapter = new ListViewAdapter(culture,getContext());

        ls.setAdapter(adapter);
        v.setVisibility(INVISIBLE);

        downAnim = AnimationUtils.loadAnimation(getContext(),R.anim.slidebar_down_anim);
        upAnim= AnimationUtils.loadAnimation(getContext(),R.anim.slidebar_anim);

        downAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(INVISIBLE);
                isOn = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tv_down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                v.startAnimation(downAnim);
            }
        });
        majorBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(subject);
            }
        });
        cultureBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(culture);
            }
        });


        addView(v);



    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SelectBtn);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SelectBtn, defStyle, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typedArray) {

        typedArray.recycle();
    }

    public void setSubject(ArrayList<CustomScheduleItem> subject) {
        this.subject = subject;
    }

    public void setCulture(ArrayList<CustomScheduleItem> culture) {
        this.culture = culture;
    }

    public  void open(){
        isOn = true;
        v.setVisibility(View.VISIBLE);
        v.startAnimation(upAnim);
    }

    public void close(){
        v.startAnimation(downAnim);
    }

    public boolean isOn() {
        return isOn;
    }

    private void loadClasses(){
        Gson gson = new Gson();
        SharedPreferences sf = getContext().getSharedPreferences("check",MODE_PRIVATE);// check -> empty 가 no면 데이터가 이미 존재한다는 거

        String sb =sf.getString("subject","");
        String cl =sf.getString("culture","");


        try {
            subject.addAll(Arrays.asList(gson.fromJson(sb, CustomScheduleItem[].class)));
            culture.addAll(Arrays.asList(gson.fromJson(cl, CustomScheduleItem[].class)));
        }catch (Exception e){

        }


    }
}
