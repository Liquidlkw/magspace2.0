package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Shape extends BasePage {
    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        initView();
        initData();
    }

    private void initData() {
        addList(1,Shape1.class);
        addList(2,Shape2.class);
        addList(3,Shape3.class);
        addList(4,Shape4.class);
        addList(5,Shape5.class);
        addList(6,Shape6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }

}
