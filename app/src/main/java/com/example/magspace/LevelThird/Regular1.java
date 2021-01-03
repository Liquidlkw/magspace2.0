package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.LevelSecond.Rectangular1choosepic;
import com.example.magspace.Levelfirst.Number6choosenum;
import com.example.magspace.R;

public class Regular1 extends BasePageActivity implements View.OnTouchListener {
    int[] position = new int[2];
    private LinearLayout mReg1;
    public Regular1chooseangle currgame;
    public Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_regular1);
        showtoplayout();
        initScreenDisplay();
//        currgame=Number6choosenum.getInstance(this,display);
        currgame= new Regular1chooseangle(this,display);
        currgame.setOnTouchListener(this);
        ChangSubtileColor(R.color.colorgreen);
        init("7.正多面形", "理解正多面体的形状及特点，并找出其面数、顶点数和边数", "做好准备", "掌握主要概念", "计算下面多变形的内角");
        setmPageNumber("01/06");
        initView();
        addView(currgame);
    }

    private void initView() {
        mReg1 = (LinearLayout) findViewById(R.id.reg1);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mReg1.getWidth();
            currgame.Viewheight=mReg1.getHeight();
        }
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager  wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }

    void addView(View view){
        ViewGroup parent=(ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mReg1.addView(view,params);
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
