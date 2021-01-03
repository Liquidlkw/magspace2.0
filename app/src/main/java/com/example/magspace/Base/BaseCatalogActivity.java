package com.example.magspace.Base;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.widget.TextViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

import static androidx.core.widget.TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM;

/**
 * 通过java代码动态的添加目录内容
 * 并暴露添加目录的方法
 * 以及底部的难度选择也需要添加的方法并暴露出去
 * 方便继承的时候使用
 */
public class BaseCatalogActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    public   TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;
    public TextView textView7;
    public TextView textView8;

    /**
     * 引人入胜的数学，启蒙孩子对数学的初步认识
     */
    private TextView mTitleCatalog;
    private LinearLayout mContextCatalog;
    private LinearLayout mBottomLayout;
    private LinearLayout mBottomSelector;
    /**
     * 一\n级
     */
    private Button mOne;
    /**
     * 二\n级
     */
    private Button mTwo;
    /**
     * 三\n级
     */
    private Button mThree;
    private int index ; //级别
    private Animation anim;

    public  static  int i=1 ;

    /**
     * 显示底部选择器
     */
    public void showbottomselector() {
        mBottomSelector.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏底部选择器
     */
    public void hidebottomselector() {
        mBottomSelector.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        setContentLayout(R.layout.activity_catalog);
        initView();
        setTitle("目录");
        mContextCatalog.removeAllViews();

    }

    private void initView() {
        mTitleCatalog = (TextView) findViewById(R.id.title_catalog);
        mContextCatalog = (LinearLayout) findViewById(R.id.context_catalog);
        mBottomSelector = (LinearLayout) findViewById(R.id.bottom_selector);
        mOne = (Button) findViewById(R.id.one);
        mOne.setOnClickListener(this);
        mTwo = (Button) findViewById(R.id.two);
        mTwo.setOnClickListener(this);
        mThree = (Button) findViewById(R.id.three);
        mThree.setOnClickListener(this);
    }


    public void setcataloge(int color, TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTextColor(getResources().getColor(R.color.colortext));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = 8;
            params.topMargin = 8;
            params.weight = 1;
            textView.setTextSize(1,18);
            textView.setPadding(8, 0, 8, 0);
//            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(textView,2,80,2,1);
            TextViewCompat.setAutoSizeTextTypeWithDefaults(textView, AUTO_SIZE_TEXT_TYPE_UNIFORM);
            mContextCatalog.addView(textView, -1, params);
        }
    }


    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
            default:
                break;
                //一级
            case R.id.one:
                i=1;
                mOne.startAnimation(anim);
                mThree.clearAnimation();
                mTwo.clearAnimation();
                mTitleCatalog.setTextColor(getResources().getColor(R.color.colororange));
                mContextCatalog.removeAllViews();
                initone();

                break;
                //二级
            case R.id.two:
                i=2;
                mOne.clearAnimation();
                mThree.clearAnimation();
                mTwo.startAnimation(anim);
                mTitleCatalog.setTextColor(getResources().getColor(R.color.colorblue));
                mContextCatalog.removeAllViews();
                inittwo();
                break;

                //三级
            case R.id.three:
                i=3;
                anim.setFillAfter(true);
                mOne.clearAnimation();
                mTwo.clearAnimation();
                mThree.startAnimation(anim);
                mTitleCatalog.setTextColor(getResources().getColor(R.color.colorgreen));
                mContextCatalog.removeAllViews();
                initthree();
                break;
        }
    }

    private void initthree() {
        mTitleCatalog.setText("需要数学直觉和思考能力的高级活动，这些能力发展了孩子的高级解决问题的能力");
        index = 1;
        textView1 = new TextView(this);
        textView1.setId(R.id.text_view_1);
        textView1.setClickable(true);
        textView1.setText("1.对称");
        textView1.setBackgroundResource(R.drawable.text_background_selector);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView1,500);
//            }}, 100);

        textView1.setOnTouchListener(this);


        textView2 = new TextView(this);
        textView2.setText("2.数学的内角");
        textView2.setId(R.id.text_view_2);
        textView2.setClickable(true);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView2,500);
//            }}, 120);

        textView2.setBackgroundResource(R.drawable.text_background_selector);
        textView2.setOnTouchListener(this);

        textView3 = new TextView(this);
        textView3.setText("3.棋盘");
        textView3.setClickable(true);
        textView3.setId(R.id.text_view_3);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView3,500);
//            }}, 140);

        textView3.setBackgroundResource(R.drawable.text_background_selector);
        textView3.setOnTouchListener(this);

        textView4 = new TextView(this);
        textView4.setText("4.垛性方块");
        textView4.setClickable(true);
        textView4.setId(R.id.text_view_4);

//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView4,500);
//            }}, 160);

        textView4.setBackgroundResource(R.drawable.text_background_selector);
        textView4.setOnTouchListener(this);

        textView5 = new TextView(this);
        textView5.setText("5.圆和π");
        textView5.setClickable(true);
        textView5.setId(R.id.text_view_5);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView5,500);
//            }}, 180);

        textView5.setBackgroundResource(R.drawable.text_background_selector);
        textView5.setOnTouchListener(this);

        textView6 = new TextView(this);
        textView6.setText("6.旋转的固体");
        textView6.setClickable(true);
        textView6.setId(R.id.text_view_6);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView6,500);
//            }}, 200);

        textView6.setBackgroundResource(R.drawable.text_background_selector);
        textView6.setOnTouchListener(this);


        textView7 = new TextView(this);
        textView7.setText("7.一般多面体");
        textView7.setClickable(true);
        textView7.setId(R.id.text_view_7);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView7,500);
//            }}, 220);

        textView7.setBackgroundResource(R.drawable.text_background_selector);
        textView7.setOnTouchListener(this);

        textView8 = new TextView(this);
        textView8.setText("8.半常规多面体");
        textView8.setClickable(true);
        textView8.setId(R.id.text_view_8);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                AnimUtil.setShowAnimation(textView8,500);
//            }}, 240);

        textView8.setBackgroundResource(R.drawable.text_background_selector);
        textView8.setOnTouchListener(this);

        setcataloge(R.color.colorgreen,textView1, textView2, textView3, textView4, textView5,textView6,textView7,textView8);

    }

    public void initone(){
         mTitleCatalog.setText("孩子进行触觉活动的同时学习并且得到乐趣");
         index = 1;
         textView1 = new TextView(this);
         textView1.setId(R.id.text_view_1);
         textView1.setClickable(true);
         textView1.setText("1.数字");
         textView1.setBackgroundResource(R.drawable.text_background_selector);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView1,500);
//             }}, 100);

         textView1.setOnTouchListener(this);


         textView2 = new TextView(this);
         textView2.setText("2.构建形状");
         textView2.setId(R.id.text_view_2);
         textView2.setClickable(true);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView2,500);
//             }}, 120);

         textView2.setBackgroundResource(R.drawable.text_background_selector);
         textView2.setOnTouchListener(this);

         textView3 = new TextView(this);
         textView3.setText("3.比较重量");
         textView3.setClickable(true);
         textView3.setId(R.id.text_view_3);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView3,500);
//             }}, 140);

         textView3.setBackgroundResource(R.drawable.text_background_selector);
         textView3.setOnTouchListener(this);

         textView4 = new TextView(this);
         textView4.setText("4.比较高度");
         textView4.setClickable(true);
         textView4.setId(R.id.text_view_4);

//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView4,500);
//             }}, 160);

         textView4.setBackgroundResource(R.drawable.text_background_selector);
         textView4.setOnTouchListener(this);

         textView5 = new TextView(this);
         textView5.setText("5.平面图形");
         textView5.setClickable(true);
         textView5.setId(R.id.text_view_5);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView5,500);
//             }}, 180);

         textView5.setBackgroundResource(R.drawable.text_background_selector);
         textView5.setOnTouchListener(this);

         textView6 = new TextView(this);
         textView6.setText("6.立体图形");
         textView6.setClickable(true);
         textView6.setId(R.id.text_view_6);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView6,500);
//             }}, 200);

         textView6.setBackgroundResource(R.drawable.text_background_selector);
         textView6.setOnTouchListener(this);


         textView7 = new TextView(this);
         textView7.setText("7.不同视点");
         textView7.setClickable(true);
         textView7.setId(R.id.text_view_7);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView7,500);
//             }}, 220);

         textView7.setBackgroundResource(R.drawable.text_background_selector);
         textView7.setOnTouchListener(this);

         textView8 = new TextView(this);
         textView8.setText("8.找一找模式");
         textView8.setClickable(true);
         textView8.setId(R.id.text_view_8);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView8,500);
//             }}, 240);

         textView8.setBackgroundResource(R.drawable.text_background_selector);
         textView8.setOnTouchListener(this);

         setcataloge(R.color.colororange,textView1, textView2, textView3, textView4, textView5,textView6,textView7,textView8);

    }
     public  void inittwo(){
         mTitleCatalog.setText("通过创造性活动，发展孩子的空间推理能力并且激发他们对数学批判性思考");
         index = 2;
         textView1 = new TextView(this);
         textView1.setId(R.id.text_view_1);
         textView1.setClickable(true);
         textView1.setText("1.相似技术");
         textView1.setBackgroundResource(R.drawable.text_background_selector);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView1,500);
//             }}, 100);

         textView1.setOnTouchListener(this);


         textView2 = new TextView(this);
         textView2.setText("2.魔法方块");
         textView2.setId(R.id.text_view_2);
         textView2.setClickable(true);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView2,500);
//             }}, 120);

         textView2.setBackgroundResource(R.drawable.text_background_selector);
         textView2.setOnTouchListener(this);

         textView3 = new TextView(this);
         textView3.setText("3.除法");
         textView3.setClickable(true);
         textView3.setId(R.id.text_view_3);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView3,500);
//             }}, 140);

         textView3.setBackgroundResource(R.drawable.text_background_selector);
         textView3.setOnTouchListener(this);

         textView4 = new TextView(this);
         textView4.setText("4.魔方和骰子");
         textView4.setClickable(true);
         textView4.setId(R.id.text_view_4);

//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView4,500);
//             }}, 160);

         textView4.setBackgroundResource(R.drawable.text_background_selector);
         textView4.setOnTouchListener(this);

         textView5 = new TextView(this);
         textView5.setText("5.长方体");
         textView5.setClickable(true);
         textView5.setId(R.id.text_view_5);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView5,500);
//             }}, 180);

         textView5.setBackgroundResource(R.drawable.text_background_selector);
         textView5.setOnTouchListener(this);

         textView6 = new TextView(this);
         textView6.setText("6.长方体和椎体");
         textView6.setClickable(true);
         textView6.setId(R.id.text_view_6);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView6,500);
//             }}, 200);

         textView6.setBackgroundResource(R.drawable.text_background_selector);
         textView6.setOnTouchListener(this);


         textView7 = new TextView(this);
         textView7.setText("7.四个四");
         textView7.setClickable(true);
         textView7.setId(R.id.text_view_7);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView7,500);
//             }}, 220);

         textView7.setBackgroundResource(R.drawable.text_background_selector);
         textView7.setOnTouchListener(this);

         textView8 = new TextView(this);
         textView8.setText("8.概率");
         textView8.setClickable(true);
         textView8.setId(R.id.text_view_8);
//         new Handler().postDelayed(new Runnable(){
//             public void run() {
//                 AnimUtil.setShowAnimation(textView8,500);
//             }}, 240);

         textView8.setBackgroundResource(R.drawable.text_background_selector);
         textView8.setOnTouchListener(this);

         setcataloge(R.color.colorblue,textView1, textView2, textView3, textView4, textView5,textView6,textView7,textView8);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    protected void onResume() {
        super.onResume();
        if(DataUtil.ismusicplay&&DataUtil.backmusic!=null)
            DataUtil.backmusic.start();

        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

}
