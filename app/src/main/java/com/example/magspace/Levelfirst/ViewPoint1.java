package com.example.magspace.Levelfirst;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class ViewPoint1 extends BasePageActivity implements View.OnTouchListener {

    private LinearLayout mViewPointcell;
    public Viewpoint1chooselid currgame;
    public Display display;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_view_point1);
        initScreenDisplay();
//        currgame=Viewpoint1chooselid.getInstance(this,display);
        currgame= new Viewpoint1chooselid(this,display);
        currgame.setOnTouchListener(this);

        // add in here
        showtoplayout();
        init("7.不同视点", "通过不同的角度去看一个物体，可以看到不同的视觉", "做好准备", "掌握主要概念", "选择每个容器对应的盖子");
        setmPageNumber("01/06");
        initView();
        addView(currgame);

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mViewPointcell.getWidth();
            currgame.Viewheight=mViewPointcell.getHeight();
        }
    }
    private void initView() {
        mViewPointcell =  findViewById(R.id.viewPointcell);
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
        mViewPointcell.addView(view,params);
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
