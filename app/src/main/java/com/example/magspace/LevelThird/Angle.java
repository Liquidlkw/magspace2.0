package com.example.magspace.LevelThird;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Angle extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angle);
        initView();
        initData();
    }

    private void initData() {
        addList(1, Angle1.class);
        addList(2, Angle2.class);
        addList(3, Angle3.class);
        addList(4, Angle4.class);
        addList(5, Angle5.class);
        addList(6, Angle6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
