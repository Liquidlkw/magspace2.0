package com.example.magspace.Levelfirst;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Pic1 extends BasePageActivity {

    private EditText mPic1;
    private EditText mPic2;
    private EditText mPic3;
    private EditText mPic4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_pic1);
        showtoplayout();
        init("5.平面图形", "理解通过不同方法完成给定图形，不同方法制作一个图形", "做好准备", "掌握主要概念", "用以下图形块完全覆盖给定图形时的最多和最少图形块");
        initView();
        initData();
    }

    private void initData() {
        addTextChangedListener(mPic1,6);
        addTextChangedListener(mPic2,2);
        addTextChangedListener(mPic3,4);
        addTextChangedListener(mPic4,2);
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
    private void initView() {
        mPic1 = (EditText) findViewById(R.id.pic1);
        mPic2 = (EditText) findViewById(R.id.pic2);
        mPic3 = (EditText) findViewById(R.id.pic3);
        mPic4 = (EditText) findViewById(R.id.pic4);
    }
}
