package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Dice extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        initView();
        initData();
    }

    private void initData() {
        addList(1, Dice1.class);
        addList(2, Dice2.class);
        addList(3, Dice3.class);
        addList(4, Dice4.class);
        addList(5, Dice5.class);
        addList(6, Dice6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
