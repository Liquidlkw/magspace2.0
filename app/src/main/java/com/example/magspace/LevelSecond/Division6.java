package com.example.magspace.LevelSecond;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Division6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mMath1;
    private ImageView mMath2;
    private ImageView mMath3;
    private ImageView mMath4;
    private ImageView mMath5;
    private ImageView mMatch6;
    private ImageView mMath7;
    private ImageView mMath1cell1;
    private ImageView mMath1cell2;
    private ImageView mMath1cell3;
    private ImageView mMath2cell1;
    private ImageView mMath2cell2;
    private ImageView mMath3cell1;
    private ImageView mMath3cell2;
    /**
     * 真分数：
     */
    private TextView mMathq1;
    /**
     * 假分数：
     */
    private TextView mMathq2;
    /**
     * 混合数：
     */
    private TextView mMathq3;

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_division6);
        ChangSubtileColor(R.color.colorblue);
        init("复习", "", "阅读三种类型的定语将题目中的分数分类");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mMath1 = (ImageView) findViewById(R.id.math1);
        mMath1.setOnClickListener(this);
        mMath2 = (ImageView) findViewById(R.id.math2);
        mMath2.setOnClickListener(this);
        mMath3 = (ImageView) findViewById(R.id.math3);
        mMath3.setOnClickListener(this);
        mMath4 = (ImageView) findViewById(R.id.math4);
        mMath4.setOnClickListener(this);
        mMath5 = (ImageView) findViewById(R.id.math5);
        mMath5.setOnClickListener(this);
        mMatch6 = (ImageView) findViewById(R.id.match6);
        mMatch6.setOnClickListener(this);
        mMath7 = (ImageView) findViewById(R.id.math7);
        mMath7.setOnClickListener(this);
        mMath1cell1 = (ImageView) findViewById(R.id.math1cell1);
        mMath1cell2 = (ImageView) findViewById(R.id.math1cell2);
        mMath1cell3 = (ImageView) findViewById(R.id.math1cell3);
        mMath2cell1 = (ImageView) findViewById(R.id.math2cell1);
        mMath2cell2 = (ImageView) findViewById(R.id.math2cell2);
        mMath3cell1 = (ImageView) findViewById(R.id.math3crll1);
        mMath3cell2 = (ImageView) findViewById(R.id.math3cell2);
        mMathq1 = (TextView) findViewById(R.id.mathq1);
        mMathq1.setOnClickListener(this);
        mMathq2 = (TextView) findViewById(R.id.mathq2);
        mMathq2.setOnClickListener(this);
        mMathq3 = (TextView) findViewById(R.id.mathq3);
        mMathq3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.math1:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);

                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath3cell1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.math2:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath1cell1.setVisibility(View.VISIBLE);
                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                break;
            case R.id.math3:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath2cell1.setVisibility(View.VISIBLE);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                break;
            case R.id.math4:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath1cell2.setVisibility(View.VISIBLE);
                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                break;
            case R.id.math5:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath3cell2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.match6:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath1cell3.setVisibility(View.VISIBLE);
                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                break;
            case R.id.math7:
                if (i==1)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                if (i==2)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mMath2cell2.setVisibility(View.VISIBLE);
                }
                if (i==3)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                }
                break;
            case R.id.mathq1:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                i=1;
                mMathq1.setBackgroundColor(Color.GREEN);
                mMathq2.setBackgroundColor(Color.TRANSPARENT);
                mMathq3.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.mathq2:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                i=2;
                mMathq2.setBackgroundColor(Color.GREEN);
                mMathq1.setBackgroundColor(Color.TRANSPARENT);
                mMathq3.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.mathq3:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                i=3;
                mMathq3.setBackgroundColor(Color.GREEN);
                mMathq2.setBackgroundColor(Color.TRANSPARENT);
                mMathq1.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
    }
}
