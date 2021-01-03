package com.example.magspace.Levelfirst;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.DiyView.CustomView;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Height6 extends BasePageActivity implements View.OnClickListener {

    /**
     * A
     */
    private TextView mA;
    /**
     * B
     */
    private TextView mB;
    private CustomView mC1;
    /**
     * C
     */
    private TextView mC;
    /**
     * D
     */
    private TextView mD;
    /**
     * E
     */
    private TextView mE;
    /**
     * 学校
     */
    private TextView mSchool;
    /**
     * 邮局
     */
    private TextView mPostoffic;
    /**
     * E
     */
    private TextView mTrain;
    private CustomView mC2;
    private TextView result;
    int i = 0;
    String str = "";
    private ObjectAnimator nopeAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_height6);
        init("复习", "", "");
        setmPageNumber("06/06");
        initView();
    }


    private void initView() {
        result = findViewById(R.id.answe);
        mA = (TextView) findViewById(R.id.A);
        mA.setOnClickListener(this);
        mB = (TextView) findViewById(R.id.B);
        mB.setOnClickListener(this);
        mC1 = (CustomView) findViewById(R.id.c1);
        mC1.setOnClickListener(this);
        mC = (TextView) findViewById(R.id.C);
        mC.setOnClickListener(this);
        mD = (TextView) findViewById(R.id.D);
        mD.setOnClickListener(this);
        mC2 = (CustomView) findViewById(R.id.c2);
        mC2.setOnClickListener(this);
        mE = (TextView) findViewById(R.id.E);
        mE.setOnClickListener(this);
        mSchool = (TextView) findViewById(R.id.school);
        mSchool.setOnClickListener(this);
        mPostoffic = (TextView) findViewById(R.id.postoffic);
        mPostoffic.setOnClickListener(this);
        mTrain = (TextView) findViewById(R.id.train);
        mTrain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.A:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator animator = AnimUtil.tada(mA);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animator.end();
                    }
                }, 1000);
                break;
            case R.id.B:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                mC1.circleAnimation();
                mB.setEnabled(false);
                break;
            case R.id.c1:
                break;
            case R.id.C:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator animato = AnimUtil.tada(mC);
                animato.setRepeatCount(ValueAnimator.INFINITE);
                animato.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animato.end();
                    }
                }, 1000);
                break;
            case R.id.D:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                mC2.circleAnimation();
                mD.setEnabled(false);
                break;

            case R.id.E:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator animat = AnimUtil.tada(mE);
                animat.setRepeatCount(ValueAnimator.INFINITE);
                animat.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animat.end();
                    }
                }, 1000);
                break;
            case R.id.school:
                i++;
                if (i==4){
                result.setTextColor(Color.BLACK);
                i=1;
                str="";
                }
                if (i<3){
                    str+="学校>";
                }else {
                    str+="学校";
                    if (str.equals("学校>火车站>邮局")){
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        str+="正确";
                        mSchool.setEnabled(false);
                        mPostoffic.setEnabled(false);
                        mTrain.setEnabled(false);
                        nopeAnimator = AnimUtil.nope(result);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();
                            }
                        }, 1000);
                        result.setTextColor(Color.GREEN);
                    }else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        str+="错误";
                        result.setTextColor(Color.RED);
                    }
                }
                result.setText(str);

                break;
            case R.id.postoffic:
                i++;
                if (i==4){
                    result.setTextColor(Color.BLACK);
                    i=1;
                    str="";
                }
                if (i<3){
                    str+="邮局>";
                }else {
                    str+="邮局";
                    if (str.equals("学校>火车站>邮局")){
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        str+="正确";
                        mSchool.setEnabled(false);
                        mPostoffic.setEnabled(false);
                        mTrain.setEnabled(false);
                         nopeAnimator = AnimUtil.nope(result);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        result.setTextColor(Color.GREEN);
                    }else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        str+="错误";
                        result.setTextColor(Color.RED);
                    }
                }
                result.setText(str);
                break;
            case R.id.train:
                i++;
                if (i==4){
                    i=1;
                    result.setTextColor(Color.BLACK);
                    str="";
                }
                if (i<3){
                    str+="火车站>";
                }else {
                    str+="火车站";
                    if (str.equals("学校>火车站>邮局")){
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        str+="正确";
                        mSchool.setEnabled(false);
                        mPostoffic.setEnabled(false);
                        mTrain.setEnabled(false);
                        nopeAnimator = AnimUtil.nope(result);
                        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        nopeAnimator.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nopeAnimator.end();

                            }
                        }, 1000);
                        result.setTextColor(Color.GREEN);
                    }else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        str+="错误";
                        result.setTextColor(Color.RED);
                    }
                }
                result.setText(str);

                break;
        }
    }
}
