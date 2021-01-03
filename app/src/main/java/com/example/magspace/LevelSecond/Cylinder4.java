package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Cylinder4 extends BasePageActivity {

    private EditText mEd1;
    private EditText mEd4;
    private EditText mEd5;
    private EditText mEd2;
    private EditText mEd3;
    private EditText mEd7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_cylinder4);
        ChangSubtileColor(R.color.colorblue);
        init("找一找", "", "使用MAGSPACE做出如下立体图形，并补充表格");
        setmPageNumber("04/06");
        initView();
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
        mEd1 = (EditText) findViewById(R.id.ed1);
        mEd4 = (EditText) findViewById(R.id.ed4);
        mEd5 = (EditText) findViewById(R.id.ed5);
        mEd2 = (EditText) findViewById(R.id.ed2);

        mEd3 = (EditText) findViewById(R.id.ed3);
        mEd7 = (EditText) findViewById(R.id.ed7);

        addTextChangedListener(mEd1,5);
        addTextChangedListener(mEd2,8);
        addTextChangedListener(mEd3,5);
        addTextChangedListener(mEd4,7);
        addTextChangedListener(mEd5,7);
        addTextChangedListener(mEd7,7);
    }
}
