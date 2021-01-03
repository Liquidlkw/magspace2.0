package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Division extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);
        initView();
        initData();
    }

    private void initData() {
        addList(1, Division1.class);
        addList(2, Division2.class);
        addList(3, Division3.class);
        addList(4, Division4.class);
        addList(5, Division5.class);
        addList(6, Division6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
