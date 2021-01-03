package com.example.magspace.Catalog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.magspace.Base.BaseCatalogActivity;
import com.example.magspace.CreatePic.CreatePicStage;
import com.example.magspace.FindPic.FindPicStage;
import com.example.magspace.KnowNumber.KnowNumberStage;
import com.example.magspace.R;
import com.example.magspace.SymmertyPic.SymmertyPicStage;
import com.example.magspace.ThreeDPic.ThreeDStage;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;


public class MathFirstCatalog extends BaseCatalogActivity implements View.OnTouchListener{


    private  TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView1 = new TextView(this);
        textView1.setId(R.id.text_view_1);
        textView1.setClickable(true);
        textView1.setText("1.找出图形");
        textView1.setBackgroundResource(R.drawable.text_background_selector);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                AnimUtil.setShowAnimation(textView1,500);
            }}, 100);

        textView1.setOnTouchListener(this);


        textView2 = new TextView(this);
        textView2.setText("2.创造新图形");
        textView2.setId(R.id.text_view_2);
        textView2.setClickable(true);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                AnimUtil.setShowAnimation(textView2,500);
            }}, 200);

        textView2.setBackgroundResource(R.drawable.text_background_selector);
        textView2.setOnTouchListener(this);

        textView3 = new TextView(this);
        textView3.setText("3.认识--数字");
        textView3.setClickable(true);
        textView3.setId(R.id.text_view_3);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                AnimUtil.setShowAnimation(textView3,500);
            }}, 300);

        textView3.setBackgroundResource(R.drawable.text_background_selector);
        textView3.setOnTouchListener(this);

        textView4 = new TextView(this);
        textView4.setText("4.图形与对称");
        textView4.setClickable(true);
        textView4.setId(R.id.text_view_4);

        new Handler().postDelayed(new Runnable(){
            public void run() {
                AnimUtil.setShowAnimation(textView4,500);
            }}, 400);

        textView4.setBackgroundResource(R.drawable.text_background_selector);
        textView4.setOnTouchListener(this);

        textView5 = new TextView(this);
        textView5.setText("5.三维图形");
        textView5.setClickable(true);
        textView5.setId(R.id.text_view_5);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                AnimUtil.setShowAnimation(textView5,500);
            }}, 500);

        textView5.setBackgroundResource(R.drawable.text_background_selector);
        textView5.setOnTouchListener(this);

        setcataloge(R.color.colororange,textView1, textView2, textView3, textView4, textView5);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (DataUtil.isvoiceplay&&event.getAction() == MotionEvent.ACTION_UP)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
        //找出图形
        case R.id.text_view_1:

            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathFirstCatalog.this, FindPicStage.class));
                    }
                }, 300);
            }
            break;
        //创造新图形
        case R.id.text_view_2:
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathFirstCatalog.this, CreatePicStage.class));
                    }
                }, 300);
            }
            break;
        //找出数字
        case R.id.text_view_3:
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathFirstCatalog.this, KnowNumberStage.class));
                    }
                }, 300);
            }
            break;
        //图形与对称
        case R.id.text_view_4:
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathFirstCatalog.this, SymmertyPicStage.class));
                    }
                }, 300);
            }
            break;
        //三维图形
        case R.id.text_view_5:
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathFirstCatalog.this, ThreeDStage.class));
                    }
                }, 300);
            }
            break;
    }
        return false;
}
}
