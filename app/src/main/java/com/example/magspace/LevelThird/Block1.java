package com.example.magspace.LevelThird;

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

public class Block1 extends BasePageActivity implements View.OnTouchListener {

    private LinearLayout mBlock;
    public Block1choosepic currgame;
    public Display display;
    int[] position = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_block1);
        initScreenDisplay();
//        currgame=Block1choosepic.getInstance(this,display);
        currgame= new Block1choosepic(this,display);
        currgame.setOnTouchListener(this);
        ChangSubtileColor(R.color.colorgreen);
        showtoplayout();
        init("2.堆叠立方体", "检查由堆叠立方体构建的多种图形，并且在平面上展示该图形。", "做好准备", "掌握主要概念", "由堆叠立方体构建的不同立方体图形如下图所示，将相同的图形连接起来");
        setmPageNumber("01/06");
        initView();
        addView(currgame);
    }

    private void initView() {
        mBlock = (LinearLayout) findViewById(R.id.block);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mBlock.getWidth();
            currgame.Viewheight=mBlock.getHeight();
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
        mBlock.addView(view,params);
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
