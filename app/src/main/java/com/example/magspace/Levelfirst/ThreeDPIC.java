package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class ThreeDPIC extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dpic);
        initView();
        initData();
    }

    private void initData() {
        addList(1,ThreeDPIC1.class);
        addList(2,ThreeDPIC2.class);
        addList(3,ThreeDPIC3.class);
        addList(4,ThreeDPIC4.class);
        addList(5,ThreeDPIC5.class);
        addList(6,ThreeDPIC6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
