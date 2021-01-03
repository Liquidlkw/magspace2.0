package com.example.magspace.LevelThird;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Regular6 extends BasePageActivity implements View.OnClickListener {

    /**
     * A.正四面体
     */
    private TextView mTextView1;
    /**
     * B.立方体
     */
    private TextView mTextView2;
    /**
     * C.正八面体
     */
    private TextView mTextView3;
    /**
     * D.正十二面体
     */
    private TextView mTextView4;
    /**
     * E.真二十面体
     */
    private TextView mTextView5;
    private EditText mE1;
    private EditText mE2;
    private EditText mE3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_regular6);
        ChangSubtileColor(R.color.colorgreen);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mTextView1 = (TextView) findViewById(R.id.text_view_1);
        mTextView1.setOnClickListener(this);
        mTextView2 = (TextView) findViewById(R.id.text_view_2);
        mTextView2.setOnClickListener(this);
        mTextView3 = (TextView) findViewById(R.id.text_view_3);
        mTextView3.setOnClickListener(this);
        mTextView4 = (TextView) findViewById(R.id.text_view_4);
        mTextView4.setOnClickListener(this);
        mTextView5 = (TextView) findViewById(R.id.text_view_5);
        mTextView5.setOnClickListener(this);
        mE1 = (EditText) findViewById(R.id.e1);
        mE2 = (EditText) findViewById(R.id.e2);
        mE3 = (EditText) findViewById(R.id.e3);

        addTextChangedListener(mE1,8);
        addTextChangedListener(mE2,6);
        addTextChangedListener(mE3,12);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.text_view_1:
                DataUtil.correctwithoutpic(mTextView1);
                mTextView1.setBackgroundColor(Color.GREEN);
                break;
            case R.id.text_view_2:
                DataUtil.error(mTextView2);
                break;
            case R.id.text_view_3:
                DataUtil.correctwithoutpic(mTextView3);
                mTextView3.setBackgroundColor(Color.GREEN);
                break;
            case R.id.text_view_4:
                DataUtil.error(mTextView4);
                break;
            case R.id.text_view_5:
                DataUtil.correctwithoutpic(mTextView5);
                mTextView5.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public void addTextChangedListener(final EditText editText, final int reuslt) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.toString().equals(reuslt + "")) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(reuslt + "")) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);
                } else {
                    editText.getText().clear();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(reuslt + "")) {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);
                } else {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                    editText.getText().clear();

                }


            }

        });
    }
}
