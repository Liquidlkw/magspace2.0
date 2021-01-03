package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Rectangular extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangular);
        initView();
        initData();
    }

    private void initData() {
        addList(1, Rectangular1.class);
        addList(2, Rectangular2.class);
        addList(3, Rectangular3.class);
        addList(4, Rectangular4.class);
        addList(5, Rectangular5.class);
        addList(6, Rectangular6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
