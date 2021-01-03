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

public class Weight6 extends BasePageActivity implements View.OnClickListener {


    private ImageView mAs1;
    private ImageView mAs2;
    private ImageView mAs3;
    private ImageView mAs4;
    private ImageView mAs5;
    private ImageView mAs6;
    private ImageView mAs7;
    private ImageView mAs8;
    private ImageView mAs9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_weight6);
        init("复习", "", "看图选出最轻的物体");
        setmPageNumber("06/06");
        initView();

    }


    private void initView() {
        mAs1 = (ImageView) findViewById(R.id.as1);
        mAs1.setOnClickListener(this);
        mAs2 = (ImageView) findViewById(R.id.as2);
        mAs2.setOnClickListener(this);
        mAs3 = (ImageView) findViewById(R.id.as3);
        mAs3.setOnClickListener(this);
        mAs4 = (ImageView) findViewById(R.id.as4);
        mAs4.setOnClickListener(this);
        mAs5 = (ImageView) findViewById(R.id.as5);
        mAs5.setOnClickListener(this);
        mAs6 = (ImageView) findViewById(R.id.as6);
        mAs6.setOnClickListener(this);
        mAs7 = (ImageView) findViewById(R.id.as7);
        mAs7.setOnClickListener(this);
        mAs8 = (ImageView) findViewById(R.id.as8);
        mAs8.setOnClickListener(this);
        mAs9 = (ImageView) findViewById(R.id.as9);
        mAs9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.as1:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAnimator = AnimUtil.nope(mAs1);
                nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                nopeAnimator.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAnimator.end();

                    }
                }, 1000);

                break;
            case R.id.as2:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                final ObjectAnimator animator = AnimUtil.tada(mAs2);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animator.end();
                        mAs2.setImageResource(R.drawable.book1_section3_page6_img1_cell2_right);
                    }
                }, 1000);

                break;
            case R.id.as3:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAnimator1 = AnimUtil.nope(mAs3);
                nopeAnimator1.setRepeatCount(ValueAnimator.INFINITE);
                nopeAnimator1.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAnimator1.end();
                    }
                }, 1000);
                break;
            case R.id.as4:

                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAnimator2 = AnimUtil.nope(mAs4);
                nopeAnimator2.setRepeatCount(ValueAnimator.INFINITE);
                nopeAnimator2.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAnimator2.end();
                    }
                }, 1000);

                break;
            case R.id.as5:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                final ObjectAnimator a = AnimUtil.tada(mAs5);
                a.setRepeatCount(ValueAnimator.INFINITE);
                a.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a.end();
                        mAs5.setImageResource(R.drawable.book1_section3_page6_img2_cell2_right);
                    }
                }, 1000);


                break;
            case R.id.as6:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAnor2 = AnimUtil.nope(mAs6);
                nopeAnor2.setRepeatCount(ValueAnimator.INFINITE);
                nopeAnor2.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAnor2.end();
                    }
                }, 1000);
                break;
            case R.id.as7:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAno2 = AnimUtil.nope(mAs7);
                nopeAno2.setRepeatCount(ValueAnimator.INFINITE);
                nopeAno2.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAno2.end();
                    }
                }, 1000);
                break;
            case R.id.as8:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAno = AnimUtil.nope(mAs8);
                nopeAno.setRepeatCount(ValueAnimator.INFINITE);
                nopeAno.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAno.end();
                    }
                }, 1000);

                break;
            case R.id.as9:

                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                final ObjectAnimator w = AnimUtil.tada(mAs9);
                w.setRepeatCount(ValueAnimator.INFINITE);
                w.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        w.end();
                        mAs9.setImageResource(R.drawable.book1_section3_page6_img3_cell3_right);
                    }
                }, 1000);
                break;
        }
    }
}
