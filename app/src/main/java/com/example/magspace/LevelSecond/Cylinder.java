package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Cylinder extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder);
        initView();
        initData();
    }
    private void initData() {
        addList(1, Cylinder1.class);
        addList(2, Cylinder2.class);
        addList(3, Cylinder3.class);
        addList(4, Cylinder4.class);
        addList(5, Cylinder5.class);
        addList(6, Cylinder6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
