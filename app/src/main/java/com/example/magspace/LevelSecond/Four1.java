package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Four1 extends BasePageActivity {

    private EditText mE1;
    private EditText mE6;
    private EditText mE2;
    private EditText mE7;
    private EditText mE3;
    private EditText mE8;
    private EditText mE4;
    private EditText mE9;
    private EditText mE5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_four1);
        showtoplayout();
        ChangSubtileColor(R.color.colorblue);
        init("7.分数", "通过使用运算符和括弧计算数字的活动了解数学符号和运算符", "做好准备", "掌握主要概念", "四个四是一个表达式，这个表达式使用4个4和数学符号，用正确的数字填空");
        setmPageNumber("01/06");
        initView();
    }

    private void initView() {
        mE1 = (EditText) findViewById(R.id.e1);
        mE6 = (EditText) findViewById(R.id.e6);
        mE2 = (EditText) findViewById(R.id.e2);
        mE7 = (EditText) findViewById(R.id.e7);
        mE3 = (EditText) findViewById(R.id.e3);
        mE8 = (EditText) findViewById(R.id.e8);
        mE4 = (EditText) findViewById(R.id.e4);
        mE9 = (EditText) findViewById(R.id.e9);
        mE5 = (EditText) findViewById(R.id.e5);

        addTextChangedListener(mE1,1);
        addTextChangedListener(mE2,2);
        addTextChangedListener(mE3,3);
        addTextChangedListener(mE4,4);
        addTextChangedListener(mE5,5);
        addTextChangedListener(mE6,6);
        addTextChangedListener(mE7,7);
        addTextChangedListener(mE8,8);
        addTextChangedListener(mE9,9);







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
