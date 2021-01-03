package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class MagicSquare extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_square);
        initView();
        initData();
    }

    private void initData() {
        addList(1, MagicSquare1.class);
        addList(2, MagicSquare2.class);
        addList(3, MagicSquare3.class);
        addList(4, MagicSquare4.class);
        addList(5, MagicSquare5.class);
        addList(6, MagicSquare6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
