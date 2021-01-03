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

public class ThreeDPIC6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mA;
    private ImageView mB;
    private ImageView mC;
    private ImageView mD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_three_dpic6);
        init("复习", "", "以下哪个和框中图形所用形状数量一样多");
        setmPageNumber("06/06");
        initView();
    }
    private void initView() {
        mA = (ImageView) findViewById(R.id.A);
        mA.setOnClickListener(this);
        mB = (ImageView) findViewById(R.id.B);
        mB.setOnClickListener(this);
        mC = (ImageView) findViewById(R.id.C);
        mC.setOnClickListener(this);
        mD = (ImageView) findViewById(R.id.D);
        mD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.A:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator nopeAnimator = AnimUtil.nope(mA);
                nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                nopeAnimator.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nopeAnimator.end();
                    }
                }, 1000);
                break;

            case R.id.B:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                final ObjectAnimator animator = AnimUtil.tada(mB);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animator.end();
                        mB.setImageResource(R.drawable.book1_section6_page6_img2_right);
                    }
                }, 1000);
                break;
            case R.id.C:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator a = AnimUtil.nope(mC);
                a.setRepeatCount(ValueAnimator.INFINITE);
                a.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a.end();

                    }
                }, 1000);
                break;
            case R.id.D:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                final ObjectAnimator k = AnimUtil.nope(mD);
                k.setRepeatCount(ValueAnimator.INFINITE);
                k.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        k.end();

                    }
                }, 1000);
                break;
        }
    }
}
