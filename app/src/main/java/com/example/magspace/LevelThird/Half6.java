package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Half6 extends BasePageActivity {

    private EditText mEd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_half6);
        ChangSubtileColor(R.color.colorgreen);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");
        initView();
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

                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        editText.getText().clear();


                }


            }

        });
    }
    private void initView() {
        mEd1 = (EditText) findViewById(R.id.ed1);
        addTextChangedListener(mEd1,5);
    }
}
