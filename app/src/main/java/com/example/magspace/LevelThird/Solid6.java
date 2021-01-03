package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Solid6 extends BasePageActivity implements View.OnTouchListener, View.OnClickListener {

    public Solid6choosepic currgame;
    public Display display;
    private LinearLayout mSolid6;
    int[] position = new int[2];
    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private ImageView mImage4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_solid6);
        initScreenDisplay();
        //   currgame=Solid6choosepic.getInstance(this,display);
        currgame = new Solid6choosepic(this, display);
        currgame.setOnTouchListener(this);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");

        initView();
        addView(currgame);

    }


    private void initView() {
        mSolid6 = (LinearLayout) findViewById(R.id.solid6);
        mImage1 = (ImageView) findViewById(R.id.image1);
        mImage1.setOnClickListener(this);
        mImage2 = (ImageView) findViewById(R.id.image2);
        mImage2.setOnClickListener(this);
        mImage3 = (ImageView) findViewById(R.id.image3);
        mImage3.setOnClickListener(this);
        mImage4 = (ImageView) findViewById(R.id.image4);
        mImage4.setOnClickListener(this);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith = mSolid6.getWidth();
            currgame.Viewheight = mSolid6.getHeight();
        }
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }

    void addView(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSolid6.addView(view, params);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (currgame.startx == 0 && currgame.starty == 0) {
            currgame.getLocationOnScreen(position);
            currgame.startx = position[0];
            currgame.starty = position[1];
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currgame.isdown = true;
            currgame.ismove = false;
            currgame.invalidate();
        }
        currgame.Point_x = (int) event.getRawX();
        currgame.Point_y = (int) event.getRawY();
        return false;
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
            case R.id.image1:
                DataUtil.error(mImage1);
                break;
            case R.id.image2:
                DataUtil.error(mImage2);
                break;
            case R.id.image3:
                DataUtil.correct(mImage3,R.drawable.book3_section6_page6_img12_right);
                break;
            case R.id.image4:
                DataUtil.error(mImage4);
                break;
        }
    }
}
