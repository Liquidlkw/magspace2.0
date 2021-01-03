package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Height extends BasePage {
    private ViewPager mVp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        initView();
        initData();
    }

    private void initData() {
        addList(1,Height1.class);
        addList(2,Height2.class);
        addList(3,Height3.class);
        addList(4,Height4.class);
        addList(5,Height5.class);
        addList(6,Height6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
