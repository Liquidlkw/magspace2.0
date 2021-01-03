package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class ViewPoint extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point);
        initView();
        initData();
    }
    private void initData() {
        addList(1,ViewPoint1.class);
        addList(2,ViewPoint2.class);
        addList(3,ViewPoint3.class);
        addList(4,ViewPoint4.class);
        addList(5,ViewPoint5.class);
        addList(6,ViewPoint6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
