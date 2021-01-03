package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Possibility5 extends BasePageActivity implements View.OnClickListener {

    private ImageView mC1;
    private ImageView mC2;
    private ImageView mC3;
    private ImageView mC4;
    private ImageView mC5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_possibility5);
        ChangSubtileColor(R.color.colorblue);
        init("复习", "", "");
        setmPageNumber("05/06");
        initView();
    }

    private void initView() {
        mC1 = (ImageView) findViewById(R.id.c1);
        mC1.setOnClickListener(this);
        mC2 = (ImageView) findViewById(R.id.c2);
        mC2.setOnClickListener(this);
        mC3 = (ImageView) findViewById(R.id.c3);
        mC3.setOnClickListener(this);
        mC4 = (ImageView) findViewById(R.id.c4);
        mC4.setOnClickListener(this);
        mC5 = (ImageView) findViewById(R.id.c5);
        mC5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.c1:
                DataUtil.correct(mC1,R.drawable.book2_section8_page6_img1_right);
                break;
            case R.id.c2:
                DataUtil.error(mC2);
                break;
            case R.id.c3:
                DataUtil.error(mC3);
                break;
            case R.id.c4:
                DataUtil.error(mC4);
                break;
            case R.id.c5:
                DataUtil.correct(mC5,R.drawable.book2_section8_page6_img5_right);
                break;
        }
    }
}
