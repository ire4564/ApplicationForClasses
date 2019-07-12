package com.example.applicateclass.TimeTable;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applicateclass.R;

public class CustomTimeItem extends LinearLayout {
    private TextView tvTitle;
    private TextView tvSub;
    private ImageView ivDivideLine;
    private Boolean isFull;

    public CustomTimeItem(Context context) {
        super(context);
        initView();
    }
    public CustomTimeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }
    public CustomTimeItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView() {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_tableitem, this, false);
        addView(v);

        tvTitle = (TextView) findViewById(R.id.tableItem_title);
        tvSub = (TextView) findViewById(R.id.tableItem_sub);
        ivDivideLine = (ImageView)findViewById(R.id.tableItem_divide);
        isFull = false;

    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableItem);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableItem, defStyle, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typedArray) {
        String titleText = typedArray.getString(R.styleable.TableItem_titleText);
        tvTitle.setText(titleText);
        String subText = typedArray.getString(R.styleable.TableItem_titleText);
        tvSub.setText(subText);

        boolean visible=true;
        visible = typedArray.getBoolean(R.styleable.TableItem_dividerSize,visible);
        if(!visible)
            ivDivideLine.setVisibility(View.INVISIBLE);
        else
            ivDivideLine.setVisibility(View.VISIBLE);

        typedArray.recycle();

    }

    public void setTableItem(String title, String sub, boolean visible){
        tvTitle.setText(title);
        tvSub.setText(sub);

        if(!visible)
            ivDivideLine.setVisibility(View.INVISIBLE);
        else
            ivDivideLine.setVisibility(View.VISIBLE);

        isFull = true;
    }

    public Boolean getFull() {
        return isFull;
    }
}
