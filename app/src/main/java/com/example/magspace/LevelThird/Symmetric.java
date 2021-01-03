package com.example.magspace.LevelThird;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Symmetric extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symmetric);
        initView();
        iniData();
    }

    private void iniData() {
        addList(1, Symmetric1.class);
        addList(2, Symmetric2.class);
        addList(3, Symmetric3.class);
        addList(4, Symmetric4.class);
        addList(5, Symmetric5.class);
        addList(6, Symmetric6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
