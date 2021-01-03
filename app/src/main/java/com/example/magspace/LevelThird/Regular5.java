package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Regular5 extends BasePageActivity {

    private EditText mE1;
    private EditText mE5;
    private EditText mE9;
    private EditText mE2;
    private EditText mE6;
    private EditText mE10;
    private EditText mE3;
    private EditText mE7;
    private EditText mE11;
    private EditText mE4;
    private EditText mE8;
    private EditText mE12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_regular5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻", "", "给出各正多面体的面数、顶点数和边数。");
        setmPageNumber("05/06");
        initView();
    }

    private void initView() {
        mE1 = (EditText) findViewById(R.id.e1);
        mE5 = (EditText) findViewById(R.id.e5);
        mE9 = (EditText) findViewById(R.id.e9);
        mE2 = (EditText) findViewById(R.id.e2);
        mE6 = (EditText) findViewById(R.id.e6);
        mE10 = (EditText) findViewById(R.id.e10);
        mE3 = (EditText) findViewById(R.id.e3);
        mE7 = (EditText) findViewById(R.id.e7);
        mE11 = (EditText) findViewById(R.id.e11);
        mE4 = (EditText) findViewById(R.id.e4);
        mE8 = (EditText) findViewById(R.id.e8);
        mE12 = (EditText) findViewById(R.id.e12);


        addTextChangedListener(mE1,6);
        addTextChangedListener(mE2,8);
        addTextChangedListener(mE3,12);
        addTextChangedListener(mE4,20);

        addTextChangedListener(mE5,8);
        addTextChangedListener(mE6,6);
        addTextChangedListener(mE7,20);
        addTextChangedListener(mE8,12);

        addTextChangedListener(mE9,12);
        addTextChangedListener(mE10,12);
        addTextChangedListener(mE11,30);
        addTextChangedListener(mE12,30);
    }

    public void addTextChangedListener(final EditText editText, final int reuslt){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


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
                    if (s.toString().trim().length()>=2) {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        editText.getText().clear();
                    }

                }


            }

        });
    }
}
