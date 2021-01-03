package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.Levelfirst.Viewpoint1chooselid;
import com.example.magspace.R;

public class SimilarPic6 extends BasePageActivity implements View.OnTouchListener {

    private LinearLayout mSim6;

    public SimilarPic6choosepic currgame;
    public Display display;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_similar_pic6);
        initScreenDisplay();
        //currgame=SimilarPic6choosepic.getInstance(this,display);
        currgame= new SimilarPic6choosepic(this,display);
        currgame.setOnTouchListener(this);

        ChangSubtileColor(R.color.colorblue);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");
        initView();
        currgame.getLocationOnScreen(position);
        addView(currgame);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mSim6.getWidth();
            currgame.Viewheight=mSim6.getHeight();
        }
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }


    private void initView() {
        mSim6 = (LinearLayout) findViewById(R.id.sim6);
    }

    void addView(View view){
        ViewGroup parent=(ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSim6.addView(view,params);
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
