package com.example.yangyong.androidtest.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yangyong.androidtest.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyong on 20-3-16.
 */

public class ListViewTest extends AppCompatActivity{

    // fruitList用于存储数据
    private List<String> mList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        // 先拿到数据并放在适配器上
        initFruits(); //初始化水果数据
        FruitAdapter adapter=new FruitAdapter(ListViewTest.this,R.layout.listview_item_ly,mList);

        // 将适配器上的数据传递给listView
        ListView listView = findViewById(R.id.listview_ly);
        listView.setAdapter(adapter);

        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tv = mList.get(position);
                Toast.makeText(ListViewTest.this, tv, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 初始化数据
    private void initFruits(){
        for(int i=0;i<10;i++){
            String str = "hello 0";
            mList.add(str);
            str = "hello 2";
            mList.add(str);
            str = "hello 3";
            mList.add(str);
            str = "hello 4";
            mList.add(str);
        }
    }

    public class FruitAdapter extends ArrayAdapter<String> {
        private int resourceId;

        // 适配器的构造函数，把要适配的数据传入这里
        public FruitAdapter(Context context, int textViewResourceId, List<String> objects){
            super(context,textViewResourceId,objects);
            resourceId = textViewResourceId;
        }

        // convertView 参数用于将之前加载好的布局进行缓存
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            String str = getItem(position); //获取当前项的Fruit实例

            // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
            View view;
            TextView viewHolder;
            if (convertView==null){

                // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
                view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

                // 避免每次调用getView()时都要重新获取控件实例
                viewHolder  =view.findViewById(R.id.textview);

                // 将ViewHolder存储在View中（即将控件的实例存储在其中）
                view.setTag(viewHolder);
            } else{
                view=convertView;
                viewHolder=(TextView) view.getTag();
            }

            // 获取控件实例，并调用set...方法使其显示出来
            viewHolder.setText(str);
            return view;
        }
    }
}
