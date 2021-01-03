package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class MagicSquare6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mDi2;
    private ImageView mDi3;
    private ImageView mDi4;
    private ImageView mDi5;
    private ImageView mDi6;
    private ImageView mM1;
    private ImageView mM2;
    private ImageView mM3;
    private int i;
    private EditText mT1;
    private EditText mT2;
    private EditText mT3;
    private EditText mT4;
    private EditText mT5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_magic_square6);
        ChangSubtileColor(R.color.colorblue);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");
        initView();

    }

    private void initView() {
        mDi2 = (ImageView) findViewById(R.id.di2);
        mDi2.setOnClickListener(this);
        mDi3 = (ImageView) findViewById(R.id.di3);
        mDi3.setOnClickListener(this);
        mDi4 = (ImageView) findViewById(R.id.di4);
        mDi4.setOnClickListener(this);
        mDi5 = (ImageView) findViewById(R.id.di5);
        mDi5.setOnClickListener(this);
        mDi6 = (ImageView) findViewById(R.id.di6);
        mDi6.setOnClickListener(this);
        mM1 = (ImageView) findViewById(R.id.m1);
        mM1.setOnClickListener(this);
        mM2 = (ImageView) findViewById(R.id.m2);
        mM2.setOnClickListener(this);
        mM3 = (ImageView) findViewById(R.id.m3);
        mM3.setOnClickListener(this);
        mT1 = (EditText) findViewById(R.id.t1);
        mT2 = (EditText) findViewById(R.id.t2);
        mT3 = (EditText) findViewById(R.id.t3);
        mT4 = (EditText) findViewById(R.id.t4);
        mT5 = (EditText) findViewById(R.id.t5);

       addTextChangedListener(mT1,8);
        addTextChangedListener(mT2,5);
        addTextChangedListener(mT3,9);
        addTextChangedListener(mT4,6);
        addTextChangedListener(mT5,4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.di2:
                if (i == 1) {
                    DataUtil.error(mDi2);
                }
                if (i == 2) {
                    DataUtil.error(mDi2);
                }
                if (i == 3) {
                    DataUtil.correctwithoutpic(mDi2);
                    mM3.setImageResource(R.drawable.digit2);
                }
                break;
            case R.id.di3:
                if (i == 1) {
                    DataUtil.error(mDi3);
                }
                if (i == 2) {
                    DataUtil.error(mDi3);
                }
                if (i == 3) {
                    DataUtil.error(mDi3);
                }
                break;
            case R.id.di4:
                if (i == 1) {
                    DataUtil.correctwithoutpic(mDi4);
                    mM1.setImageResource(R.drawable.digit4);
                }
                if (i == 2) {
                    DataUtil.error(mDi4);
                }
                if (i == 3) {
                    DataUtil.error(mDi4);
                }
                break;
            case R.id.di5:
                if (i == 1) {
                    DataUtil.error(mDi5);
                }
                if (i == 2) {
                    DataUtil.error(mDi5);
                }
                if (i == 3) {
                    DataUtil.error(mDi5);
                }
                break;
            case R.id.di6:
                if (i == 1) {
                    DataUtil.error(mDi6);
                }
                if (i == 2) {
                    DataUtil.correctwithoutpic(mDi6);
                    mM2.setImageResource(R.drawable.digit6);
                }
                if (i == 3) {
                    DataUtil.error(mDi6);
                }
                break;
            case R.id.m1:
                mM1.setAlpha(0.5f);
                mM2.setAlpha(1f);
                mM3.setAlpha(1f);
                i = 1;
                break;
            case R.id.m2:
                mM2.setAlpha(0.5f);
                mM1.setAlpha(1f);
                mM3.setAlpha(1f);
                i = 2;
                break;
            case R.id.m3:
                mM3.setAlpha(0.5f);
                mM2.setAlpha(1f);
                mM1.setAlpha(1f);
                i = 3;
                break;
        }


    }

    public void addTextChangedListener(final EditText editText, final int reuslt){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if(s.toString().equals(reuslt+""))
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);
                }

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(reuslt+""))
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);
                }else {
                    editText.getText().clear();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals(reuslt+""))
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);
                }else {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                    editText.getText().clear();

                }


            }

        });
    }
}
