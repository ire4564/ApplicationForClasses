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
    private TextView title;
    private TextView sub;
    private ImageView divideLine;

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

        title = (TextView) findViewById(R.id.tableItem_title);
        sub = (TextView) findViewById(R.id.tableItem_sub);
        divideLine = (ImageView)findViewById(R.id.tableItem_divide);

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
        title.setText(titleText);
        String subText = typedArray.getString(R.styleable.TableItem_titleText);
        sub.setText(subText);

        boolean visible=true;
        visible = typedArray.getBoolean(R.styleable.TableItem_dividerSize,visible);
        if(!visible)
            divideLine.setVisibility(View.INVISIBLE);
        else
            divideLine.setVisibility(View.VISIBLE);

        typedArray.recycle();

    }


}
