package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Block6 extends BasePageActivity implements View.OnTouchListener, View.OnClickListener {

    private LinearLayout mBlock6;
    public Block6choosepic currgame;
    public Display display;
    int[] position = new int[2];
    private ImageView mBa1;
    private ImageView mBa2;
    private ImageView mBa3;
    private ImageView mBa4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_block6);
        initScreenDisplay();
//        currgame=Block6choosepic.getInstance(this,display);
        currgame = new Block6choosepic(this, display);
        currgame.setOnTouchListener(this);
        ChangSubtileColor(R.color.colorgreen);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");

        initView();
        addView(currgame);
    }

    private void initView() {
        mBlock6 = (LinearLayout) findViewById(R.id.block6);
        mBa1 = (ImageView) findViewById(R.id.ba1);
        mBa1.setOnClickListener(this);
        mBa2 = (ImageView) findViewById(R.id.ba2);
        mBa2.setOnClickListener(this);
        mBa3 = (ImageView) findViewById(R.id.ba3);
        mBa3.setOnClickListener(this);
        mBa4 = (ImageView) findViewById(R.id.ba4);
        mBa4.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith = mBlock6.getWidth();
            currgame.Viewheight = mBlock6.getHeight();
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
        mBlock6.addView(view, params);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ba1:
                DataUtil.error(mBa1);
                break;
            case R.id.ba2:
                DataUtil.correct(mBa2,R.drawable.book3_section4_page6_img2_right);
                break;
            case R.id.ba3:
                DataUtil.error(mBa3);
                break;
            case R.id.ba4:
                DataUtil.error(mBa4);
                break;
        }
    }
}
