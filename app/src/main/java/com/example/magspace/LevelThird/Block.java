package com.example.magspace.LevelThird;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class Block extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        initView();
        initData();
    }
    private void initData() {
        addList(1, Block1.class);
        addList(2, Block2.class);
        addList(3, Block3.class);
        addList(4, Block4.class);
        addList(5, Block5.class);
        addList(6, Block6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
