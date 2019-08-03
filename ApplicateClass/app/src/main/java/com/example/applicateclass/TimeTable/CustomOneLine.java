package com.example.applicateclass.TimeTable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applicateclass.R;

import java.util.ArrayList;
import java.util.Random;

public class CustomOneLine extends LinearLayout {
    private ArrayList<CustomTimeItem> ac_TableTimeItems = new ArrayList<>();
    private CustomTimeTable customTimeTable;
    private  boolean editAble;
    public CustomOneLine(Context context) {
        super(context);
        initView();
    }
    public CustomOneLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }
    public CustomOneLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView() {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_oneline, this, false);
        addView(v);

        String pkg = getContext().getPackageName();
        for (int i =0; i< 28; ++i){
            int id = getContext().getResources().getIdentifier("table_item"+(i+1), "id", pkg);
            ac_TableTimeItems.add((CustomTimeItem)v.findViewById(id));

        }

    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableLine);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableLine, defStyle, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typedArray) {

        typedArray.recycle();

    }

    public boolean addTime(String title, String sub,CustomTimeset item,CustomScheduleItem scheduleItem){
        int startTime = item.getStartTime();
        int endTime = item.getEndTime();

        int startFront  = (startTime-900) / 100;
        int startTail   = (startTime-900) % 100 > 0 ? 1 : 0;
        int endFront    = (endTime-900) / 100;
        int endTail     = (endTime-900) % 100 > 0 ? 1 : 0;

        startTime = startFront * 2 + startTail;
        endTime = endFront  * 2 + endTail -1;
        Log.e("asd",startTime+" "+endTime);

        for(int i = startTime; i <= endTime; ++i){
            if(ac_TableTimeItems.get(i).getFull())
                return false;
        }

        ac_TableTimeItems.get(startTime).setTableItem(title, sub, false , Color.parseColor("#a1a1a1"),true,scheduleItem);
        for(int i = startTime+1; i < endTime; ++i)
            ac_TableTimeItems.get(i).setTableItem("", "", false , Color.parseColor("#a1a1a1"),true,scheduleItem);
        ac_TableTimeItems.get(endTime).setTableItem("", "", true , Color.parseColor("#a1a1a1"),true,scheduleItem);



        return true;
    }

    public boolean removeTime(CustomTimeset item){
        int startTime = item.getStartTime();
        int endTime = item.getEndTime();

        int startFront  = (startTime-900) / 100;
        int startTail   = (startTime-900) % 100 > 0 ? 1 : 0;
        int endFront    = (endTime-900) / 100;
        int endTail     = (endTime-900) % 100 > 0 ? 1 : 0;

        startTime = startFront * 2 + startTail;
        endTime = endFront  * 2 + endTail-1;

        ac_TableTimeItems.get(startTime).setTableItem("", "", true , Color.parseColor("#ffffff"),false,null);
        for(int i = startTime+1; i < endTime; ++i)
            ac_TableTimeItems.get(i).setTableItem("", "", true , Color.parseColor("#ffffff"),false,null);
        ac_TableTimeItems.get(endTime).setTableItem("", "", true , Color.parseColor("#ffffff"),false,null);



        return true;
    }


    public void setStandard(){
        for(int i =0; i<ac_TableTimeItems.size(); ++i){
            if(i % 2 == 0 )
                ac_TableTimeItems.get(i).setTableItem(((i/2+9)+"시"),"",false,Color.parseColor("#ffffff"),true,null);
            else
                ac_TableTimeItems.get(i).setTableItem("","",true,Color.parseColor("#ffffff"),true,null);

        }

    }

    public void setEditAble(boolean editAble) {
        this.editAble = editAble;
    }

    public void setCustomTimeTable(CustomTimeTable customTimeTable) {
       for (CustomTimeItem i : ac_TableTimeItems){
           i.setTimeTable(customTimeTable);
       }
    }
}

