package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.Levelfirst.Number6choosenum;
import com.example.magspace.R;

public class MagicSquare1 extends BasePageActivity  implements View.OnTouchListener {

    private LinearLayout mSim;
    public MagicSquare1choosenum currgame;
    public Display display;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_similar_pic1);
        showtoplayout();
        initScreenDisplay();
//        currgame=SimilarPic1choosepic.getInstance(this,display);
        currgame= new MagicSquare1choosenum(this,display);
        currgame.setOnTouchListener(this);
        ChangSubtileColor(R.color.colorblue);
        init("2.魔方", "通过按照固定规律安排数字的魔方活动，锻炼逻辑思维和数学能力", "做好准备", "掌握主要概念", "使用下图的数字卡片使每行、每列数字之和等于圆圈中数字");
        setmPageNumber("01/06");

//        setContentView(R.layout.activity_similar_pic1);
        initView();
        addView(currgame);
    }

    private void initView() {
        mSim = (LinearLayout) findViewById(R.id.sim);
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
        WindowManager  wm = getWindowManager();
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
