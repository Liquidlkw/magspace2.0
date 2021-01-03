package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.Levelfirst.Number6choosenum;
import com.example.magspace.R;

public class Symmetric1 extends BasePageActivity implements View.OnTouchListener{
    int[] position = new int[2];
    private FrameLayout mSy1;
    public Symmtric1choosename currgame;
    public Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showtoplayout();
        initScreenDisplay();
//        currgame=Number6choosenum.getInstance(this,display);
        currgame= new Symmtric1choosename(this,display);
        currgame.setOnTouchListener(this);
        ChangSubtileColor(R.color.colorgreen);
        setcontentlayout(R.layout.activity_symmetric1);

        init("1.对称", "了解对称线和旋转对称的特征并做出各种图形", "做好准备", "掌握主要概念", "一个具有对称线的图形可以分成两个对等的部分，这条折线叫对称线。\n一个旋转对称的图形可以在不超过360°的范围旋转，他的旋转点和原图形一致。分别在有对称线图形下面写“线”，有旋转对称的图形下面写“点”");
        setmPageNumber("01/06");
        initView();
        addView(currgame);
    }

    private void initView() {
        mSy1 = (FrameLayout) findViewById(R.id.sy1);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mSy1.getWidth();
            currgame.Viewheight=mSy1.getHeight();
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
        mSy1.addView(view,params);
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
