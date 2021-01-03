package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;

public class Four6 extends BasePageActivity implements View.OnClickListener {

    /**
     * +
     */
    private TextView mC1;
    /**
     * -
     */
    private TextView mC2;
    /**
     * x
     */
    private TextView mC3;
    /**
     * ÷
     */
    private TextView mC4;
    private TextView mT1;
    private TextView mT5;
    private TextView mT9;
    private TextView mT2;
    private TextView mT6;
    private TextView mT10;
    private TextView mT3;
    private TextView mT7;
    private TextView mT11;
    private TextView mT4;
    private TextView mT8;
    private TextView mT12;
    private int i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_four6);
        ChangSubtileColor(R.color.colorblue);
        init("复习", "", "使用4个3，加减乘除和小括号构成1~5这几个数字");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mC1 = (TextView) findViewById(R.id.c1);
        mC1.setOnClickListener(this);
        mC2 = (TextView) findViewById(R.id.c2);
        mC2.setOnClickListener(this);
        mC3 = (TextView) findViewById(R.id.c3);
        mC3.setOnClickListener(this);
        mC4 = (TextView) findViewById(R.id.c4);
        mC4.setOnClickListener(this);
        mT1 = (TextView) findViewById(R.id.t1);
        mT1.setOnClickListener(this);
        mT5 = (TextView) findViewById(R.id.t5);
        mT5.setOnClickListener(this);
        mT9 = (TextView) findViewById(R.id.t9);
        mT9.setOnClickListener(this);
        mT2 = (TextView) findViewById(R.id.t2);
        mT2.setOnClickListener(this);
        mT6 = (TextView) findViewById(R.id.t6);
        mT6.setOnClickListener(this);
        mT10 = (TextView) findViewById(R.id.t10);
        mT10.setOnClickListener(this);
        mT3 = (TextView) findViewById(R.id.t3);
        mT3.setOnClickListener(this);
        mT7 = (TextView) findViewById(R.id.t7);
        mT7.setOnClickListener(this);
        mT11 = (TextView) findViewById(R.id.t11);
        mT11.setOnClickListener(this);
        mT4 = (TextView) findViewById(R.id.t4);
        mT4.setOnClickListener(this);
        mT8 = (TextView) findViewById(R.id.t8);
        mT8.setOnClickListener(this);
        mT12 = (TextView) findViewById(R.id.t12);
        mT12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {

            default:
                break;
            case R.id.c1:
                AnimUtil.setShowAnimation(mC1,500);
                if (i>0){
                    if(i==2||i==4||i==5||i==6||i==7||i==12){
                        DataUtil.correctwithoutpic(mC1);
                        if(i==2) mT2.setText("+");
                        if(i==4) mT4.setText("+");
                        if(i==5) mT5.setText("+");
                        if(i==6) mT6.setText("+");
                        if(i==7) mT7.setText("+");
                        if(i==12) mT12.setText("+");
                    }
                    else {
                        DataUtil.error(mC1);
                    }
                }
                break;
            case R.id.c2:
                AnimUtil.setShowAnimation(mC2,500);
                DataUtil.error(mC2);
                break;
            case R.id.c3:
                AnimUtil.setShowAnimation(mC3,500);
                if(i>0)
                {
                    if(i==3){
                        mT3.setText("x");
                        DataUtil.correctwithoutpic(mC3);
                    }else {
                        DataUtil.error(mC3);
                    }
                }
                break;
            case R.id.c4:
                AnimUtil.setShowAnimation(mC4,500);
                if (i>0){
                    if(i==1||i==8||i==9||i==10||i==11){
                        DataUtil.correctwithoutpic(mC4);
                        if(i==1) mT1.setText("÷");
                        if(i==8) mT8.setText("÷");
                        if(i==9) mT9.setText("÷");
                        if(i==10) mT10.setText("÷");
                        if(i==11) mT11.setText("÷");
                    } else {
                        DataUtil.error(mC4);
                    }
                }
                break;
            case R.id.t1:
                i=1;
                AnimUtil.setShowAnimation(mT1,500);
                break;
            case R.id.t5:
                i=5;
                AnimUtil.setShowAnimation(mT5,500);
                break;
            case R.id.t9:
                i=9;
                AnimUtil.setShowAnimation(mT9,500);
                break;
            case R.id.t2:
                i=2;
                AnimUtil.setShowAnimation(mT2,500);
                break;
            case R.id.t6:
                i=6;
                AnimUtil.setShowAnimation(mT6,500);
                break;
            case R.id.t10:
                i=10;
                AnimUtil.setShowAnimation(mT10,500);
                break;
            case R.id.t3:
                i=3;
                AnimUtil.setShowAnimation(mT3,500);
                break;
            case R.id.t7:
                i=7;
                AnimUtil.setShowAnimation(mT7,500);
                break;
            case R.id.t11:
                i=11;
                AnimUtil.setShowAnimation(mT11,500);
                break;
            case R.id.t4:
                i=4;
                AnimUtil.setShowAnimation(mT4,500);
                break;
            case R.id.t8:
                i=8;
                AnimUtil.setShowAnimation(mT8,500);
                break;
            case R.id.t12:
                i=12;
                AnimUtil.setShowAnimation(mT12,500);
                break;
        }
    }
}
