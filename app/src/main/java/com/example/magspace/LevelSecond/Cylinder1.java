package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Cylinder1 extends BasePageActivity {

    private EditText mB1;
    private EditText mB2;

    private EditText mB4;
    private EditText mB5;
    private EditText mB7;
    private EditText mB8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_cylinder1);
        setmPageNumber("01/06");
        ChangSubtileColor(R.color.colorblue);
        showtoplayout();
        init("6.柱体和椎体", "使用平面图形做一个柱体和椎体，理解他们的特点", "做好准备", "掌握主要概念", "数出顶点数并说出图形的名字");
        initView();
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

    private void initView() {
        mB1 = (EditText) findViewById(R.id.b1);
        mB2 = (EditText) findViewById(R.id.b2);
        mB4 = (EditText) findViewById(R.id.b4);
        mB5 = (EditText) findViewById(R.id.b5);
        mB7 = (EditText) findViewById(R.id.b7);
        mB8 = (EditText) findViewById(R.id.b8);

        addTextChangedListener(mB1, 3);
        addTextChangedListener(mB2, 3);
        addTextChangedListener(mB4, 4);
        addTextChangedListener(mB5, 4);
        addTextChangedListener(mB7, 5);
        addTextChangedListener(mB8, 5);





    }
}
