package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Regular3 extends BasePageActivity implements View.OnTouchListener {
    int[] position = new int[2];
    private LinearLayout mRew;
    public Regular3choosename currgame;
    public Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_regular3);
        ChangSubtileColor(R.color.colorgreen);
        initScreenDisplay();
//        currgame=Number6choosenum.getInstance(this,display);
        currgame= new Regular3choosename(this,display);
        currgame.setOnTouchListener(this);
        init("探险", "", "利用MAGSPACE制作正多面体");
        setmPageNumber("03/06");
        initView();
        addView(currgame);
    }

    private void initView() {
        mRew = (LinearLayout) findViewById(R.id.rew);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            currgame.Viewwith = mRew.getWidth();
            currgame.Viewheight = mRew.getHeight();
        }
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        WindowManager wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }

    void addView(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRew.addView(view, params);
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
