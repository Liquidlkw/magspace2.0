package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Possibility extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possibility);
        initView();
        initData();
    }
    private void initData() {
        addList(1, Possibility1.class);
        addList(2, Possibility2.class);
        addList(3, Possibility3.class);
        addList(4, Possibility4.class);
        addList(5, Possibility5.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
