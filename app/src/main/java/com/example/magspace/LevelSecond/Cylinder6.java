package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.Levelfirst.Viewpoint1chooselid;
import com.example.magspace.R;

public class Cylinder6 extends BasePageActivity implements View.OnTouchListener {

    public Cylinder6choosepic currgame;
    public Display display;
    private LinearLayout mCy;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_cylinder6);
        initScreenDisplay();
//        currgame = Cylinder6choosepic.getInstance(this, display);
        currgame= new Cylinder6choosepic(this,display);
        currgame.setOnTouchListener(this);

        init("复习", "", "将立体图形和对应容积连接起来");
        ChangSubtileColor(R.color.colorblue);
        setmPageNumber("06/06");
        initView();
        addView(currgame);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith = mCy.getWidth();
            currgame.Viewheight = mCy.getHeight();
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
        mCy.addView(view, params);
    }

    private void initView() {
        mCy = (LinearLayout) findViewById(R.id.cy);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(currgame.startx==0&&currgame.starty==0) {
            currgame.getLocationOnScreen(position);
            currgame.startx=position[0];
            currgame.starty=position[1];
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currgame.isdown = true;
            currgame.ismove= false;
            currgame.invalidate();
        }
        currgame.Point_x = (int) event.getRawX();
        currgame.Point_y = (int) event.getRawY();
        return false;
    }
}
