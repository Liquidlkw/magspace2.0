package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Division1 extends BasePageActivity implements View.OnClickListener {

    private ImageView mD1;
    private ImageView mD2;
    private ImageView mD3;
    private ImageView mD4;
    private ImageView mD5;
    private ImageView mD6;
    private ImageView mD7;
    private ImageView mD8;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_division1);
        showtoplayout();
        ChangSubtileColor(R.color.colorblue);
        init("3.分数", "理解分母和分子的关系是学习分数的基础", "做好准备", "在活动之前抓中主要概念", "为图形上色以显示分数");
        setmPageNumber("01/06");
        initView();
        mD1.setClickable(true);
        mD2.setClickable(true);
        mD3.setClickable(true);
        mD4.setClickable(true);
        mD5.setClickable(true);
        mD6.setClickable(true);
        mD7.setClickable(true);
        mD8.setClickable(true);
    }

    private void initView() {

        mD1 = (ImageView) findViewById(R.id.d1);
        mD1.setOnClickListener(this);
        mD2 = (ImageView) findViewById(R.id.d2);
        mD2.setOnClickListener(this);
        mD3 = (ImageView) findViewById(R.id.d3);
        mD3.setOnClickListener(this);
        mD4 = (ImageView) findViewById(R.id.d4);
        mD4.setOnClickListener(this);
        mD5 = (ImageView) findViewById(R.id.d5);
        mD5.setOnClickListener(this);
        mD6 = (ImageView) findViewById(R.id.d6);
        mD6.setOnClickListener(this);
        mD7 = (ImageView) findViewById(R.id.d7);
        mD7.setOnClickListener(this);
        mD8 = (ImageView) findViewById(R.id.d8);
        mD8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {

            default:
                break;
            case R.id.d1:
                mD1.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                mD1.setClickable(false);
                break;
            case R.id.d2:
                mD2.setClickable(false);
                mD2.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
            case R.id.d3:
                mD3.setClickable(false);
                mD3.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
            case R.id.d4:
                mD4.setClickable(false);
                mD4.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
            case R.id.d5:
                mD5.setClickable(false);
                mD5.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
            case R.id.d6:
                mD6.setClickable(false);
                mD6.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
            case R.id.d7:
                mD7.setClickable(false);
                mD7.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
            case R.id.d8:
                mD8.setClickable(false);
                mD8.setImageResource(R.drawable.square);
                i++;
                Log.i("asd", "onClick: "+i);
                if (i==5){
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    mD1.setClickable(false);
                    mD2.setClickable(false);
                    mD3.setClickable(false);
                    mD4.setClickable(false);
                    mD5.setClickable(false);
                    mD6.setClickable(false);
                    mD7.setClickable(false);
                    mD8.setClickable(false);
                }
                break;
        }
    }
}
