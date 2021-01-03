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

public class Rectangular1 extends BasePageActivity implements View.OnTouchListener {

    public Rectangular1choosepic currgame;
    public Display display;
    private LinearLayout mRec;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_rectangular1);
        initScreenDisplay();
//        currgame=Rectangular1choosepic.getInstance(this,display);
        currgame= new Rectangular1choosepic(this,display);
        currgame.setOnTouchListener(this);
        showtoplayout();
        init("5.长方体", "学习长方体容积的特征并掌握长方体、立方体的相似点、不同点", "做好准备", "掌握主要概念", "这些容积可以做成盒子，连接这些容积构成对应的盒子");
        setmPageNumber("01/06");
        initView();
        addView(currgame);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mRec.getWidth();
            currgame.Viewheight=mRec.getHeight();
        }
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager  wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }



    private void initView() {
        mRec = (LinearLayout) findViewById(R.id.rec);
    }

    void addView(View view){
        ViewGroup parent=(ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRec.addView(view,params);
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
