package com.example.magspace.Levelfirst;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Number4 extends BasePageActivity implements View.OnTouchListener {
    int[] position1 = new int[2];
    int[] position2 = new int[2];
    private LinearLayout mPageNumber4cell1;
    private LinearLayout mPageNumber4cell2;
    public Number4choosenum1 currgame1;
    public Number4choosenum2 currgame2;
    public Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_number4);
        hidetoplayout();
        initScreenDisplay();
//        currgame1=Number4choosenum1.getInstance(this,display);
        currgame1= new Number4choosenum1(this,display);
        currgame1.setOnTouchListener(this);
//        currgame2=Number4choosenum2.getInstance(this,display);
        currgame2= new Number4choosenum2(this,display);
        currgame2.setOnTouchListener(this);

        //add in here
        init("找一找", "", "组织核心概念");
        setmPageNumber("04/06");
        initView();
        addcell(currgame1,currgame2);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame1.Viewwith=mPageNumber4cell1.getWidth();
            currgame1.Viewheight=mPageNumber4cell1.getHeight();
            currgame2.Viewwith=mPageNumber4cell1.getWidth();
            currgame2.Viewheight=mPageNumber4cell1.getHeight();
        }
    }
    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }


    private void initView() {
        mPageNumber4cell1 = (LinearLayout) findViewById(R.id.page_number4cell1);
        mPageNumber4cell2 = (LinearLayout) findViewById(R.id.page_number4cell2);
    }

    void addcell(View view1,View view2){
        ViewGroup parent1=(ViewGroup) view1.getParent();
        ViewGroup parent2=(ViewGroup) view2.getParent();
        if(parent1!=null){
            parent1.removeAllViews();
        }
        if(parent2!=null){
            parent2.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPageNumber4cell1.addView(view1,params);
        mPageNumber4cell2.addView(view2,params);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(currgame1.startx==0&&currgame1.starty==0) {
            currgame1.getLocationOnScreen(position1);
            currgame1.startx=position1[0];
            currgame1.starty=position1[1];
        }
        if(currgame2.startx==0&&currgame2.starty==0) {
            currgame2.getLocationOnScreen(position2);
            currgame2.startx=position2[0];
            currgame2.starty=position2[1];
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currgame1.isdown = true;
            currgame1.ismove= false;
            currgame1.invalidate();
            currgame2.isdown = true;
            currgame2.ismove= false;
            currgame2.invalidate();
        }
        currgame1.Point_x = (int) event.getRawX();
        currgame1.Point_y = (int) event.getRawY();
        currgame2.Point_x = (int) event.getRawX();
        currgame2.Point_y = (int) event.getRawY();
        return false;
    }
}
