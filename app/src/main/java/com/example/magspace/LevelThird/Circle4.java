package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Circle4 extends BasePageActivity {

    private EditText mR1;
    private EditText mR2;
    private EditText mR3;
    private String all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_circle4);
        ChangSubtileColor(R.color.colorgreen);
        init("找一找", "", "组织核心概念");
        setmPageNumber("04/06");
        initView();

    }
    public void addTextChangedListener(final EditText editText, final int reuslt){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

//                if(s.toString().equals(reuslt+""))
//                {
//                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//                    editText.setKeyListener(null);
//                }

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString().equals(reuslt+""))
//                {
//
//                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//                    editText.setKeyListener(null);
//                }else {
//                    editText.getText().clear();
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals(reuslt+""))
                {

                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                    editText.setText(s.toString()+"cm");
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setKeyListener(null);

                }else {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                    if (s.toString().contains("cm"))
                    {

                    }else{
                        editText.getText().clear();
                    }
//

                }



            }

        });
    }

    private void initView() {
        mR1 = (EditText) findViewById(R.id.r1);
        mR2 = (EditText) findViewById(R.id.r2);
        mR3 = (EditText) findViewById(R.id.r3);
        addTextChangedListener(mR1,4);
        addTextChangedListener(mR2,6);
        addTextChangedListener(mR3,7);

    }
}
