package com.example.magspace.Levelfirst;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Find1 extends BasePageActivity implements View.OnTouchListener {

    private LinearLayout mFind1cell;
    public Find1choosepic currgame;
    public Display display;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_find1);
        initScreenDisplay();
//        currgame=Find1choosepic.getInstance(this,display);
        currgame= new Find1choosepic(this,display);
        currgame.setOnTouchListener(this);

        showtoplayout();
        init("8.发现模式", "为了掌握规则，预测并制作不同的模式", "做好准备", "掌握主要概念", "将图案与可以成为图案一部分的图形连接起来");
        setmPageNumber("01/06");
        initView();
        addView(currgame);
    }

    private void initView() {
        mFind1cell = (LinearLayout) findViewById(R.id.find1cell);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mFind1cell.getWidth();
            currgame.Viewheight=mFind1cell.getHeight();
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
        mFind1cell.addView(view,params);
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
