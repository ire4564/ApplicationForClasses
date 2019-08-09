package com.example.applicateclass.CustomView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicateclass.MainActivity;
import com.example.applicateclass.R;
import com.example.applicateclass.TimeTable.CustomScheduleItem;
import com.example.applicateclass.TimeTable.CustomTimeTable;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<CustomScheduleItem> listViewItemList = new ArrayList<CustomScheduleItem>() ;
    private ArrayList<Integer> colorList;
    private Context context;
    // ListViewAdapter의 생성자
    public ListViewAdapter(ArrayList<CustomScheduleItem> s,Context context) {
        this.context = context;
        listViewItemList = s;
        colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#a08b88"));
        colorList.add(Color.parseColor("#cc7e72"));
        colorList.add(Color.parseColor("#a6c3c7"));
        colorList.add(Color.parseColor("#d9b8a7"));
        colorList.add(Color.parseColor("#64B5F6"));
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_subjectlist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView name = (TextView) convertView.findViewById(R.id.subjectItem_name);
        TextView point = (TextView) convertView.findViewById(R.id.subjectItem_point);
        TextView time1 = (TextView) convertView.findViewById(R.id.subjectItem_time1);
        TextView time2 = (TextView) convertView.findViewById(R.id.subjectItem_time2);
        LinearLayout back = (LinearLayout)convertView.findViewById(R.id.subjectItem_background);
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final CustomScheduleItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
       name.setText(listViewItem.getTitle().replace(" ","\n"));
       point.setText(listViewItem.getCredit()+"");
       try{
           time1.setText(listViewItem.getTimelist().get(0).toString());
           time2.setText(listViewItem.getTimelist().get(1).toString());
       }catch (Exception e){

       }
       back.setBackgroundColor(colorList.get(position%colorList.size()));

       convertView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(MainActivity.customTimeTable.addTime(listViewItem)){
                   MainActivity.customSlideBar.close();
                   Toast.makeText(context,"추가되었습니다!",Toast.LENGTH_SHORT).show();
               }
           }
       });

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(ArrayList<CustomScheduleItem> list) {
        listViewItemList= list;
        notifyDataSetChanged();
    }



}
