package com.example.magspace.Levelfirst;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Shape6 extends BasePageActivity implements View.OnClickListener {

    private EditText mB1;
    private EditText mB2;
    private EditText mB3;
    private EditText mB4;
    private EditText mB5;
    private EditText mB6;
    private EditText mB7;
    private EditText mB8;
    private EditText mB9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_shape6);
        init("复习", "", "找出下列各图中包含的图形形状，写出三角形 矩形 圆形的数量");
        setmPageNumber("06/06");
        initView();
        addTextChangedListener(mB1,1);
        addTextChangedListener(mB2,2);
        addTextChangedListener(mB3,2);
        addTextChangedListener(mB4,1);
        addTextChangedListener(mB5,2);
        addTextChangedListener(mB6,2);
        addTextChangedListener(mB7,1);
        addTextChangedListener(mB8,2);
        addTextChangedListener(mB9,4);

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




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.b1:


                break;
            case R.id.b2:
                break;
            case R.id.b3:
                break;
            case R.id.b4:
                break;
            case R.id.b5:
                break;
            case R.id.b6:
                break;
            case R.id.b7:
                break;
            case R.id.b8:
                break;
            case R.id.b9:
                break;
        }
    }

    private void initView() {
        mB1 = (EditText) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (EditText) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mB3 = (EditText) findViewById(R.id.b3);
        mB3.setOnClickListener(this);
        mB4 = (EditText) findViewById(R.id.b4);
        mB4.setOnClickListener(this);
        mB5 = (EditText) findViewById(R.id.b5);
        mB5.setOnClickListener(this);
        mB6 = (EditText) findViewById(R.id.b6);
        mB6.setOnClickListener(this);
        mB7 = (EditText) findViewById(R.id.b7);
        mB7.setOnClickListener(this);
        mB8 = (EditText) findViewById(R.id.b8);
        mB8.setOnClickListener(this);
        mB9 = (EditText) findViewById(R.id.b9);
        mB9.setOnClickListener(this);
    }
}
