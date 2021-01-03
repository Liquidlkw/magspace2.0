package com.example.magspace.Levelfirst;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;

public class Find6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mFin1;
    private ImageView mFin2;
    private ImageView mFin3;
    private ImageView mFin4;
    private ImageView mFin5;
    private ImageView mFin6;
    private ImageView mFind7;
    private ObjectAnimator nopeAnimator;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_find6);
        init("复习", "", "发现模式，确定空中图形，并选出正确的图形。");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mFin1 = (ImageView) findViewById(R.id.fin1);
        mFin1.setOnClickListener(this);
        mFin2 = (ImageView) findViewById(R.id.fin2);
        mFin2.setOnClickListener(this);
        mFin3 = (ImageView) findViewById(R.id.fin3);
        mFin3.setOnClickListener(this);
        mFin4 = (ImageView) findViewById(R.id.fin4);
        mFin4.setOnClickListener(this);
        mFin5 = (ImageView) findViewById(R.id.fin5);
        mFin5.setOnClickListener(this);
        mFin6 = (ImageView) findViewById(R.id.fin6);
        mFin6.setOnClickListener(this);
        mFind7 = (ImageView) findViewById(R.id.find7);
        mFind7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fin1:
          DataUtil.correct(mFin1,R.drawable.book1_section8_page6_img1_cell1_right);
                break;
            case R.id.fin2:
                DataUtil.error(mFin2);
                break;
            case R.id.fin3:
                DataUtil.correct(mFin3,R.drawable.book1_section8_page6_img1_cell3_right);
                break;
            case R.id.fin4:
                DataUtil.error(mFin4);
                break;
            case R.id.fin5:
                DataUtil.error(mFin5);
                break;
            case R.id.fin6:
                DataUtil.error(mFin6);
                break;
            case R.id.find7:
               DataUtil.correct(mFind7,R.drawable.book1_section8_page6_img1_cell7_right);
                break;
        }
    }
}
