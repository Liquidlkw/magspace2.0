package com.example.magspace.Levelfirst;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Height4 extends BasePageActivity implements View.OnClickListener {

    /**
     * A
     */
    private TextView mA;
    /**
     * B
     */
    private TextView mB;
    /**
     * D
     */
    private TextView mD;
    /**
     * 123123131
     */
    private TextView mVs1;
    /**
     * 鸭子
     */
    private TextView mDuck;
    /**
     * 大象
     */
    private TextView mElep;
    /**
     * 兔子
     */
    private TextView mRabbit;
    /**
     * 123123131
     */
    private TextView mVs2;

    String text="";
    int i = 0;
    int k = 0;
    String str ="";
    /**
     * C
     */
    private TextView mC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_height4);
        init("找一找", "", "组织核心概念");
        setmPageNumber("04/06");
        initView();

    }

    private void initView() {
        mA = (TextView) findViewById(R.id.A);
        mA.setOnClickListener(this);
        mB = (TextView) findViewById(R.id.B1);
        mB.setOnClickListener(this);
        mD = (TextView) findViewById(R.id.D);
        mD.setOnClickListener(this);
        mVs1 = (TextView) findViewById(R.id.vs1);
        mVs1.setOnClickListener(this);
        mDuck = (TextView) findViewById(R.id.duck);
        mDuck.setOnClickListener(this);
        mElep = (TextView) findViewById(R.id.elep);
        mElep.setOnClickListener(this);
        mRabbit = (TextView) findViewById(R.id.rabbit);
        mRabbit.setOnClickListener(this);
        mVs2 = (TextView) findViewById(R.id.vs2);
        mVs2.setOnClickListener(this);
        mC = (TextView) findViewById(R.id.C);
        mC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.A:
                i++;
                if (i==5){
                    i=1;
                    text="";
                    mVs1.setTextColor(Color.BLACK);
                    mVs1.setText(text);

                }
                if (i >= 4) {
                    text += "A";
                    text.trim();
                    Log.i("lkw", text);
                    if (text.equals("B>D>C>A")) {
                        text += "正确";
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        mA.setClickable(false);
                        mB.setClickable(false);
                        mC.setClickable(false);
                        mD.setClickable(false);
                        final ObjectAnimator nopeAnimator = AnimUtil.nope(mVs1);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        mVs1.setTextColor(Color.GREEN);

                    } else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        text += "错误";
                        mVs1.setTextColor(Color.RED);
                    }

                    mVs1.setText(text);
                } else {
                    text += "A>";
                    mVs1.setText(text);
                }

                break;
            case R.id.B1:
                i++;
                if (i==5){
                    i=1;
                    text="";
                    mVs1.setTextColor(Color.BLACK);
                    mVs1.setText(text);
                }
                if (i == 4) {
                    text += "B";
                    text.trim();
                    if (text.equals("B>D>C>A")) {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        text += "正确";
                        mA.setClickable(false);
                        mB.setClickable(false);
                        mC.setClickable(false);
                        mD.setClickable(false);
                        final ObjectAnimator nopeAnimator = AnimUtil.nope(mVs1);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        mVs1.setTextColor(Color.GREEN);
                    } else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        text += "错误";
                        mVs1.setTextColor(Color.RED);
                    }
                    mVs1.setText(text);
                } else {
                    text += "B>";
                    mVs1.setText(text);
                }
                break;
            case R.id.C:
                i++;
                ToastUtil.getInstance().showToast(i+"");
                if (i==5){
                    text="";
                    mVs1.setTextColor(Color.BLACK);
                    mVs1.setText(text);

                    i=1;
                }
                if (i == 4) {
                    text += "C";
                    text.trim();
                    if (text.equals("B>D>C>A")) {
                        text += "正确";
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        mA.setClickable(false);
                        mB.setClickable(false);
                        mC.setClickable(false);
                        mD.setClickable(false);
                        final ObjectAnimator nopeAnimator = AnimUtil.nope(mVs1);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        mVs1.setTextColor(Color.GREEN);
                        mVs1.setText(text);

                    } else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        text += "错误";
                        mVs1.setTextColor(Color.RED);
                    }
                    mVs1.setText(text);
                } else {
                    text += "C>";
                    mVs1.setText(text);
                }
                break;
            case R.id.D:
                i++;
                if (i==5){
                    text="";
                    mVs1.setTextColor(Color.BLACK);
                    mVs1.setText(text);
                    i=1;
                }
                if (i == 4) {
                    text += "D";
                    text.trim();
                    if (text.equals("B>D>C>A")) {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        text += "正确";
                        mA.setClickable(false);
                        mB.setClickable(false);
                        mC.setClickable(false);
                        mD.setClickable(false);
                        mVs1.setTextColor(Color.GREEN);
                    } else {
                        text += "错误";
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        mVs1.setTextColor(Color.RED);
                    }

                    mVs1.setText(text);
                } else {
                    text += "D>";
                    mVs1.setText(text);
                }
                break;

            case R.id.duck:
                k++;
                if (k==4){
                    k=1;
                    str="";
                    mVs2.setText(str);
                }
                if (k==3)
                {
                    str+="鸭子";
                    if(str.equals("大象>鸭子>兔子"))
                    {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        str+= "正确";
                        mDuck.setClickable(false);
                        mElep.setClickable(false);
                        mRabbit.setClickable(false);
                        final ObjectAnimator nopeAnimator = AnimUtil.nope(mVs2);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        mVs2.setTextColor(Color.GREEN);
                    }else {
                        str += "错误";
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        mVs2.setTextColor(Color.RED);
                    }
                    mVs2.setText(str);
                }else {
                    str+="鸭子>";
                    mVs2.setText(str);
                }
                break;
            case R.id.elep:
                k++;
                if (k==4){
                    k=1;
                    str="";
                    mVs2.setText(str);
                }
                if (k==3)
                {
                    str+="大象";
                    if(str.equals("大象>鸭子>兔子"))
                    {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        str+= "正确";
                        mDuck.setClickable(false);
                        mElep.setClickable(false);
                        mRabbit.setClickable(false);
                        final ObjectAnimator nopeAnimator = AnimUtil.nope(mVs2);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        mVs2.setTextColor(Color.GREEN);
                    }else {
                        str += "错误";
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        mVs2.setTextColor(Color.RED);
                    }
                    mVs2.setText(str);
                }else {
                    str+="大象>";
                    mVs2.setText(str);
                }
                break;
            case R.id.rabbit:
                k++;
                if (k==4){
                    k=1;
                    str="";
                    mVs2.setText(str);
                }
                if (k==3)
                {
                    str+="兔子";
                    if(str.equals("大象>鸭子>兔子"))
                    {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        str+= "正确";
                        mDuck.setClickable(false);
                        mElep.setClickable(false);
                        mRabbit.setClickable(false);
                        final ObjectAnimator nopeAnimator = AnimUtil.nope(mVs2);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        mVs2.setTextColor(Color.GREEN);
                    }else {
                        str += "错误";
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        mVs2.setTextColor(Color.RED);
                    }
                    mVs2.setText(str);
                }else {
                    str+="兔子>";
                    mVs2.setText(str);
                }
                break;


        }
    }
}
