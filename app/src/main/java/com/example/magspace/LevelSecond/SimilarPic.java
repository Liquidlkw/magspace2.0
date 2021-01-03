package com.example.magspace.LevelSecond;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.magspace.Base.BasePage;
import com.example.magspace.R;
import com.example.magspace.adapter.MyPagerAdapter;

public class SimilarPic extends BasePage {

    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pic);

        initView();
        initData();

    }

    private void initData() {
        addList(1, SimilarPic1.class);
        addList(2, SimilarPic2.class);
        addList(3, SimilarPic3.class);
        addList(4, SimilarPic4.class);
        addList(5, SimilarPic5.class);
        addList(6, SimilarPic6.class);
        mVp.setAdapter(new MyPagerAdapter(List));
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
