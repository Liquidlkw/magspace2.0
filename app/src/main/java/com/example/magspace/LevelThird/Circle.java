package com.example.magspace.LevelThird;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Circle extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        initView();
        initData();
    }
    private void initData() {
        addList(1, Circle1.class);
        addList(2, Circle2.class);
        addList(3, Circle3.class);
        addList(4, Circle4.class);
        addList(5, Circle5.class);
        addList(6, Circle6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
