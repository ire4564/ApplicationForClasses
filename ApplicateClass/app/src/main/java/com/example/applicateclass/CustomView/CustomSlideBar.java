package com.example.applicateclass.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applicateclass.R;

public class CustomSlideBar extends LinearLayout {
    boolean isOn;
    View v;
    TextView tv_down;
    Animation upAnim;
    Animation downAnim;
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

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        v = li.inflate(R.layout.slide_buttom_tap, this, false);
        tv_down = (TextView)v.findViewById(R.id.slide_close);
        isOn = false;
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
}
