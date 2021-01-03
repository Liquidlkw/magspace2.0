package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Find extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initView();
        initData();

    }
    private void initData() {
        addList(1,Find1.class);
        addList(2,Find2.class);
        addList(3,Find3.class);
        addList(4,Find4.class);
        addList(5,Find5.class);
        addList(6,Find6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}