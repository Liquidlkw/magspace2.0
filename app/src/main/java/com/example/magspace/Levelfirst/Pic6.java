package com.example.magspace.Levelfirst;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Pic6 extends BasePageActivity implements View.OnTouchListener {
    int[] position = new int[2];
    private LinearLayout mPic6cell;

    public Pic6choosepic currgame;
    public Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_pic6);
        initScreenDisplay();
//        currgame=Pic6choosepic.getInstance(this,display);
        currgame= new Pic6choosepic(this,display);
        currgame.setOnTouchListener(this);

        init("复习", "", "看图形回答下面的问题");
        setmPageNumber("06/06");

        initView();
        addView(currgame);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith=mPic6cell.getWidth();
            currgame.Viewheight=mPic6cell.getHeight();
        }
    }

    private void initView() {
        mPic6cell = (LinearLayout) findViewById(R.id.pic6cell);
    }
    void addView(View view){
        ViewGroup parent=(ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPic6cell.addView(view,params);
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager wm = getWindowManager();
        display = wm.getDefaultDisplay();
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
