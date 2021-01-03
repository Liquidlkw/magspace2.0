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

public class SimilarPic1 extends BasePageActivity implements View.OnTouchListener {

    private LinearLayout mSim;

    public SimilarPic1choosepic currgame;
    public Display display;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showtoplayout();
        initScreenDisplay();
//        currgame=SimilarPic1choosepic.getInstance(this,display);
        currgame= new SimilarPic1choosepic(this,display);
        currgame.setOnTouchListener(this);

        ChangSubtileColor(R.color.colorblue);
        setcontentlayout(R.layout.activity_similar_pic1);
        init("1.类似图形", "通过制造类似图形发展对于图像尺寸和大小的感觉", "做好准备", "掌握主要概念", "相似图像指的是那些具有相同形状和不同大小的图形，请匹配这些相似图像");
        setmPageNumber("01/06");

        initView();
        addView(currgame);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mSim.getWidth();
            currgame.Viewheight=mSim.getHeight();
        }
    }
    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }
    void addView(View view){
        ViewGroup parent=(ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSim.addView(view,params);
    }
    private void initView() {
        mSim = (LinearLayout) findViewById(R.id.sim);
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
