package com.example.magspace.LevelThird;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Symmetric5 extends BasePageActivity implements View.OnClickListener {

    /**
     * O
     */
    private TextView mLetter1;
    /**
     * H
     */
    private TextView mLetter2;
    /**
     * M
     */
    private TextView mLetter3;
    /**
     * A
     */
    private TextView mLetter5;
    /**
     * G
     */
    private TextView mLetter6;
    /**
     * S
     */
    private TextView mLetter7;
    /**
     * P
     */
    private TextView mLetter8;
    /**
     * A
     */
    private TextView mLetter9;
    /**
     * C
     */
    private TextView mLetter10;
    /**
     * E
     */
    private TextView mLetter11;
    /**
     * 对称线：
     */
    private TextView mQ1;
    /**
     * 旋转对称：
     */
    private TextView mQ2;
    /**
     * 对称线和旋转对称：
     */
    private TextView mQ3;
    private int i;
    private String result1 = "";
    private String result2 = "";
    private String result3 = "";
    private TextView mResult1;
    private TextView mResult2;
    private TextView mResult3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_symmetric5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻", "", "找对称字母");
        setmPageNumber("05/06");
        initView();
    }

    private void initView() {
        mLetter1 = (TextView) findViewById(R.id.letter1);
        mLetter1.setOnClickListener(this);
        mLetter2 = (TextView) findViewById(R.id.letter2);
        mLetter2.setOnClickListener(this);
        mLetter3 = (TextView) findViewById(R.id.letter3);
        mLetter3.setOnClickListener(this);
        mLetter5 = (TextView) findViewById(R.id.letter5);
        mLetter5.setOnClickListener(this);
        mLetter6 = (TextView) findViewById(R.id.letter6);
        mLetter6.setOnClickListener(this);
        mLetter7 = (TextView) findViewById(R.id.letter7);
        mLetter7.setOnClickListener(this);
        mLetter8 = (TextView) findViewById(R.id.letter8);
        mLetter8.setOnClickListener(this);
        mLetter9 = (TextView) findViewById(R.id.letter9);
        mLetter9.setOnClickListener(this);
        mLetter10 = (TextView) findViewById(R.id.letter10);
        mLetter10.setOnClickListener(this);
        mLetter11 = (TextView) findViewById(R.id.letter11);
        mLetter11.setOnClickListener(this);
        mQ1 = (TextView) findViewById(R.id.q1);
        mQ1.setOnClickListener(this);
        mQ2 = (TextView) findViewById(R.id.q2);
        mQ2.setOnClickListener(this);
        mQ3 = (TextView) findViewById(R.id.q3);
        mQ3.setOnClickListener(this);
        mResult1 = (TextView) findViewById(R.id.result1);
        mResult2 = (TextView) findViewById(R.id.result2);
        mResult3 = (TextView) findViewById(R.id.result3);
    }

    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
            default:
                break;
            case R.id.letter1:
                if (i == 1) {
                    if (result1.contains("O")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter1);
                        result1 += "O";
                        mResult1.setText(result1);
                    }
                }
                if (i == 2) {
                    if (result2.contains("O")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter1);
                        result2 += "O";
                        mResult2.setText(result2);
                    }

                }
                if (i == 3) {
                    if (result3.contains("O")) {
                    break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter1);
                        result3 += "O";
                        mResult3.setText(result3);
                    }

                }
                break;
            case R.id.letter2:
                if (i == 1) {

                    if (result1.contains("H")) {
                    break;
                    }else {

                        DataUtil.correctwithoutpic(mLetter2);
                        result1 += "H";
                        mResult1.setText(result1);
                    }



                }
                if (i == 2) {

                    if (result2.contains("H")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter2);
                        result2 += "H";
                        mResult2.setText(result2);
                    }


                }
                if (i == 3) {
                    if (result3.contains("H")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter2);
                        result3 += "H";
                        mResult3.setText(result3);
                    }


                }
                break;
            case R.id.letter3:
                if (i == 1) {
                    if (result1.contains("M")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter3);
                        result1 += "M";
                        mResult1.setText(result1);
                    }

                }
                if (i == 2) {
                    DataUtil.error(mLetter3);

                }
                if (i == 3) {
                    DataUtil.error(mLetter3);

                }
                break;
            case R.id.letter5:
                if (i == 1) {
                    if (result1.contains("A")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter5);
                        result1 += "A";
                        mResult1.setText(result1);
                    }
                }
                if (i == 2) {
                    DataUtil.error(mLetter5);
                }
                if (i == 3) {
                    DataUtil.error(mLetter5);
                }
                break;
            case R.id.letter6:
                if (i == 1) {
                    DataUtil.error(mLetter6);
                }
                if (i == 2) {
                    DataUtil.error(mLetter6);
                }
                if (i == 3) {
                    DataUtil.error(mLetter6);
                }
                break;
            case R.id.letter7:
                if (i == 1) {
                    DataUtil.error(mLetter7);
                }
                if (i == 2) {
                    if (result2.contains("S")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter7);
                        result2 += "S";
                        mResult2.setText(result2);
                    }

                }
                if (i == 3) {
                    DataUtil.error(mLetter7);
                }
                break;
            case R.id.letter8:
                if (i == 1) {
                    DataUtil.error(mLetter8);
                }
                if (i == 2) {
                    DataUtil.error(mLetter8);
                }
                if (i == 3) {
                    DataUtil.error(mLetter8);
                }
                break;
            case R.id.letter9:
                if (i == 1) {
                    if (result1.contains("A")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter9);
                        result1 += "A";
                        mResult1.setText(result1);

                    }

                }
                if (i == 2) {
                    DataUtil.error(mLetter9);
                }
                if (i == 3) {
                    DataUtil.error(mLetter9);
                }
                break;
            case R.id.letter10:
                if (i == 1) {
                    DataUtil.error(mLetter10);
                }
                if (i == 2) {
                    if (result2.contains("S")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter10);
                        result2 += "S";
                        mResult2.setText(result2);
                    }

                }
                if (i == 3) {
                    DataUtil.error(mLetter10);
                }
                break;
            case R.id.letter11:
                if (i == 1) {
                    if (result1.contains("E")){
                        break;
                    }else {
                        DataUtil.correctwithoutpic(mLetter11);
                        result1 += "E";
                        mResult1.setText(result1);
                    }

                }
                if (i == 2) {
                    DataUtil.error(mLetter11);
                }
                if (i == 3) {
                    DataUtil.error(mLetter11);
                }
                break;
            case R.id.q1:
                i = 1;
                mQ1.setBackgroundColor(Color.GREEN);
                mQ2.setBackgroundColor(Color.TRANSPARENT);
                mQ3.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.q2:
                i = 2;
                mQ2.setBackgroundColor(Color.GREEN);
                mQ1.setBackgroundColor(Color.TRANSPARENT);
                mQ3.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.q3:
                i = 3;
                mQ3.setBackgroundColor(Color.GREEN);
                mQ1.setBackgroundColor(Color.TRANSPARENT);
                mQ2.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
    }
}
