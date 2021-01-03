package com.example.magspace.MatchPerson;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.magspace.Base.BaseActivity;
import com.example.magspace.R;

public class MathPersonActivity extends BaseActivity implements View.OnTouchListener {
    int[] position = new int[2];
    public MathPersonhgameview currgame;
    private LinearLayout mMp;
    public Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideback();
        //add in setcontentlayout
        initScreenDisplay();
        currgame= new MathPersonhgameview(this,display);
        currgame.setOnTouchListener(this);
        setContentLayout(R.layout.activity_math_person);
        setTitle("数学小能手");

        initView();
        addView(currgame);

    }

    private void initView() {
        mMp = (LinearLayout) findViewById(R.id.mp);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(currgame.startx==0&&currgame.starty==0) {
            currgame.getLocationOnScreen(position);
            currgame.startx=position[0];
            currgame.starty=position[1];
            Log.i("zh", "onTouch: x"+position[0]+" y "+position[1]);
            Log.i("zh","currgame height"+currgame.Screen_h);
            Log.i("zh","touch y "+(int)event.getRawY());
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currgame.isdown = true;
            currgame.isup=false;
            currgame.invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            currgame.isdown = false;
            currgame.isup=true;
            currgame.invalidate();
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    if(currgame.isback){
                        finish();
                    }
                }}, 100);

        }
        currgame.Point_x = (int) event.getRawX();
        currgame.Point_y = (int) event.getRawY();
        return true;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

        }
    }

    private void initScreenDisplay() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager  wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }

    void addView(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMp.addView(view, params);
    }
}
