package com.example.magspace.LevelThird;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Solid extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid);
        initView();
        iniData();
    }

    private void iniData() {
        addList(1, Solid1.class);
        addList(2, Solid2.class);
        addList(3, Solid3.class);
        addList(4, Solid4.class);
        addList(5, Solid5.class);
        addList(6, Solid6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
