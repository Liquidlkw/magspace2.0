package com.example.magspace.LevelThird;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Symmetric6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mI1;
    private ImageView mI2;
    private ImageView mI3;
    private ImageView mI4;
    private ImageView mI5;
    private ImageView mI6;
    /**
     * A.正方形
     */
    private TextView mT1;
    /**
     * B.规则六边形
     */
    private TextView mT2;
    /**
     * C.圆圈
     */
    private TextView mT3;
    /**
     * D.等边三角形
     */
    private TextView mT4;
    /**
     * E.棱形
     */
    private TextView mT5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_symmetric6);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mI1 = (ImageView) findViewById(R.id.i1);
        mI1.setOnClickListener(this);
        mI2 = (ImageView) findViewById(R.id.i2);
        mI2.setOnClickListener(this);
        mI3 = (ImageView) findViewById(R.id.i3);
        mI3.setOnClickListener(this);
        mI4 = (ImageView) findViewById(R.id.i4);
        mI4.setOnClickListener(this);
        mI5 = (ImageView) findViewById(R.id.i5);
        mI5.setOnClickListener(this);
        mI6 = (ImageView) findViewById(R.id.i6);
        mI6.setOnClickListener(this);
        mT1 = (TextView) findViewById(R.id.t1);
        mT1.setOnClickListener(this);
        mT2 = (TextView) findViewById(R.id.t2);
        mT2.setOnClickListener(this);
        mT3 = (TextView) findViewById(R.id.t3);
        mT3.setOnClickListener(this);
        mT4 = (TextView) findViewById(R.id.t4);
        mT4.setOnClickListener(this);
        mT5 = (TextView) findViewById(R.id.t5);
        mT5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.i1:
                DataUtil.error(mI1);
                break;
            case R.id.i2:
                DataUtil.error(mI2);
                break;
            case R.id.i3:
                DataUtil.error(mI3);
                break;
            case R.id.i4:
                DataUtil.error(mI4);
                break;
            case R.id.i5:
                DataUtil.correct(mI5,R.drawable.book3_section1_page6_img5_right);
                break;
            case R.id.i6:
                DataUtil.error(mI6);
                break;
            case R.id.t1:
                DataUtil.error(mT1);
                break;
            case R.id.t2:
                DataUtil.error(mT2);
                break;
            case R.id.t3:
                DataUtil.correctwithoutpic(mT3);
                mT3.setBackgroundColor(Color.GREEN);
                break;
            case R.id.t4:
                DataUtil.error(mT4);
                break;
            case R.id.t5:
                DataUtil.error(mT5);
                break;
        }
    }
}
