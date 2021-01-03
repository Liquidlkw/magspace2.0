package com.example.magspace.Levelfirst;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class Number extends AppCompatActivity {
    private ViewPager mVp;
    private ArrayList<View> List;
    private LocalActivityManager manager;
    private Intent intentItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        manager = new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);//必须写上这一行代码，不然会报错

        initView();
        initData();

    }

    private void initData() {
        List = new ArrayList<View>();
        addList(1,Number1.class);
        addList(2,Number2.class);
        addList(3,Number3.class);
        addList(4,Number4.class);
        addList(5,Number5.class);
        addList(6,Number6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void addList(int id,Class<?>cls){
        //这个类的第一个参数是上下文，第二个参数是你需要转化的Activity
        intentItem = new Intent(getApplicationContext(),cls);
        //将Activity转化为View然后放入View集合
        List.add(manager.startActivity(id+"", intentItem).getDecorView());

    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }


}
