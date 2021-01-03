package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Pic extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        initView();
        initData();

    }

    private void initData() {
        addList(1,Pic1.class);
        addList(2,Pic2.class);
        addList(3,Pic3.class);
        addList(4,Pic4.class);
        addList(5,Pic5.class);
        addList(6,Pic6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
