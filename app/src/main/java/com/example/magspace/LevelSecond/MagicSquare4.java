package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class MagicSquare4 extends BasePageActivity implements View.OnClickListener {

    private ImageView mDigit2;
    private ImageView mDigit3;
    private ImageView mDigit4;
    private ImageView mDigit5;
    private ImageView mDigit6;
    /**
     * 3
     */
    private TextView mNumber3;
    /**
     * 4
     */
    private TextView mNumber4;
    /**
     * 5
     */
    private TextView mNumber5;
    /**
     * 6
     */
    private TextView mNumber6;
    /**
     * 7
     */
    private TextView mNumber7;

    private int i;
    private int k;
    private ImageView mD1;
    private ImageView mD2;
    private ImageView mD3;
    private FrameLayout mF1;
    private FrameLayout mF2;
    private TextView mN1;
    private TextView mN2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_magic_square4);
        ChangSubtileColor(R.color.colorblue);
        init("找一找", "", "使用给定卡片，排列数字使每行、每列三个三个数字和等于圆圈中的数字");
        setmPageNumber("04/06");
        initView();
    }

    private void initView() {
        mDigit2 = (ImageView) findViewById(R.id.digit2);
        mDigit2.setOnClickListener(this);
        mDigit3 = (ImageView) findViewById(R.id.digit3);
        mDigit3.setOnClickListener(this);
        mDigit4 = (ImageView) findViewById(R.id.digit4);
        mDigit4.setOnClickListener(this);
        mDigit5 = (ImageView) findViewById(R.id.digit5);
        mDigit5.setOnClickListener(this);
        mDigit6 = (ImageView) findViewById(R.id.digit6);
        mDigit6.setOnClickListener(this);
        mNumber3 = (TextView) findViewById(R.id.number3);
        mNumber3.setOnClickListener(this);
        mNumber4 = (TextView) findViewById(R.id.number4);
        mNumber4.setOnClickListener(this);
        mNumber5 = (TextView) findViewById(R.id.number5);
        mNumber5.setOnClickListener(this);
        mNumber6 = (TextView) findViewById(R.id.number6);
        mNumber6.setOnClickListener(this);
        mNumber7 = (TextView) findViewById(R.id.number7);
        mNumber7.setOnClickListener(this);
        mD1 = (ImageView) findViewById(R.id.d1);
        mD1.setOnClickListener(this);
        mD2 = (ImageView) findViewById(R.id.d2);
        mD2.setOnClickListener(this);
        mD3 = (ImageView) findViewById(R.id.d3);
        mD3.setOnClickListener(this);
        mF1 = (FrameLayout) findViewById(R.id.f1);
        mF1.setOnClickListener(this);
        mF2 = (FrameLayout) findViewById(R.id.f2);
        mF2.setOnClickListener(this);
        mN1 = (TextView) findViewById(R.id.n1);
        mN2 = (TextView) findViewById(R.id.n2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.digit2:
                if (i == 1) {
                    DataUtil.error(mDigit2);
                }
                if (i == 2) {
                    DataUtil.correctwithoutpic(mDigit2);
                }
                if (i == 3) {
                    DataUtil.error(mDigit2);
                }

                break;
            case R.id.digit3:
                if (i == 1) {
                    DataUtil.correctwithoutpic(mDigit3);
                    mD1.setImageResource(R.drawable.digit3);
                }
                if (i == 2) {
                    DataUtil.error(mDigit3);
                }
                if (i == 3) {
                    DataUtil.error(mDigit3);
                }
                break;
            case R.id.digit4:
                if (i == 1) {
                    DataUtil.error(mDigit4);

                }
                if (i == 2) {
                    DataUtil.correctwithoutpic(mDigit4);
                    mD2.setImageResource(R.drawable.digit4);
                }
                if (i == 3) {
                    DataUtil.error(mDigit3);
                }
                break;
            case R.id.digit5:

                if (i == 1) {
                    DataUtil.error(mDigit5);

                }
                if (i == 2) {
                    DataUtil.error(mDigit5);
                }
                if (i == 3) {
                    DataUtil.correctwithoutpic(mDigit5);
                    mD3.setImageResource(R.drawable.digit5);
                }

                break;
            case R.id.digit6:
                if (i == 1) {
                    DataUtil.error(mDigit6);
                }
                if (i == 2) {
                    DataUtil.error(mDigit6);
                }
                if (i == 3) {
                    DataUtil.error(mDigit6);
                }
                break;
            case R.id.number3:
                if (k == 1) {
                    DataUtil.error(mNumber3);
                }
                if (k == 2) {
                    DataUtil.error(mNumber3);
                }
                break;
            case R.id.number4:
                if (k == 1) {
                    DataUtil.correctwithoutpic(mNumber4);
                    mN1.setText("4");

                }
                if (k == 2) {
                    DataUtil.error(mNumber4);
                }
                break;
            case R.id.number5:
                if (k == 1) {
                    DataUtil.error(mNumber5);
                }
                if (k == 2) {
                    DataUtil.error(mNumber5);
                }
                break;


            case R.id.number6:
                if (k == 1) {
                    DataUtil.error(mNumber6);
                }
                if (k == 2) {
                    DataUtil.error(mNumber6);
                }
                break;

            case R.id.number7:
                if (k == 1) {
                    DataUtil.error(mNumber7);
                }
                if (k == 2) {
                    DataUtil.correctwithoutpic(mNumber7);
                    mN2.setText("7");
                }
                break;
            case R.id.d1:
                mD1.setAlpha(0.5f);
                mD3.setAlpha(1f);
                ;
                mD2.setAlpha(1f);
                ;
                i = 1;
                break;
            case R.id.d2:
                mD2.setAlpha(0.5f);
                mD1.setAlpha(1f);
                mD3.setAlpha(1f);
                ;
                i = 2;
                break;
            case R.id.d3:
                mD3.setAlpha(0.5f);
                mD1.setAlpha(1f);
                ;
                mD2.setAlpha(1f);
                ;
                i = 3;
                break;
            case R.id.f1:
                mF1.setAlpha(0.5f);
                mF2.setAlpha(1f);
                k = 1;
                break;
            case R.id.f2:
                mF2.setAlpha(0.5f);
                mF1.setAlpha(1f);
                k = 2;
                break;
        }
    }
}
