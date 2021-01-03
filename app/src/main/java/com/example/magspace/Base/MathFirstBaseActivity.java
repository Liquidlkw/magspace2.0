package com.example.magspace.Base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.magspace.R;

import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;
import com.example.magspace.Utils.ScreenUtil;

public abstract class MathFirstBaseActivity extends AppCompatActivity implements View.OnClickListener {
    public int currentindex=1;//当前关卡
    public int [] leftnumbers;//关卡剩余数
    public String [] subtitles;//副标题
    public  String [] descriptions;//说明


    private ImageView mMathFirstBaseBack;
    /**
     * 找出图形
     */
    private TextView mMathFirstBaseTitle;
    /**
     * 四边形
     */
    private TextView mMathFirstBaseSubtitle;
    /**
     * 剩余
     */
    private TextView mShengyv;
    public ImageView mScore;
    /**
     * 01
     */
    private TextView mLevel;
    /**
     * 引人入胜的数学 来自MAGSPACE
     */
    private TextView mFrom;
    public ImageView mArrowLeft;
    public ImageView mArrowRight;
    private FrameLayout mContextMathfirst;
    /**
     * 找出所有MAGSPACE四边形部件
     */
    private TextView mDescription;
    private View preview;
    private RelativeLayout mRv;
    private ViewGroup.LayoutParams para;
    private int index ;//关卡数
    int flag =0 ;
    public Display display;

    //初始化 leftnumbers 、subtitles、descriptions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_first_base);
        initScreenDisplay();
        initView();
        initData();
        setlevel(index);
        Log.i("lkw", "onCreate: ");
    }

    public void initGame(View view,int number,int index) {
        setlevel(index);
        showshengyv();
        setdescription(descriptions[index-1]);
        setsubtitle(subtitles[index-1]);
        currentindex=index;
        showarrow();
        setContentLayout(view,index);
        setmScore(GameUtil.getcurrentnumber(number));
        if (index==1) hideleftarrow();
        if (index==6) hiderightarrow();
    }
    public void initGame(View view,int index) {
        setlevel(index);
        hideshengyv();
        setdescription(descriptions[index-1]);
        setsubtitle(subtitles[index-1]);
        currentindex=index;
        showarrow();
        setContentLayout(view,index);
        if (index==1) hideleftarrow();
        if (index==6) hiderightarrow();
    }
    public  abstract void initData();
    private void initScreenDisplay() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager  wm = getWindowManager();
        display = wm.getDefaultDisplay();
    }

    /**
     * 设置title下面内容区域的内容
     * @param view  关卡内容
     * @param index 关卡数 第一关就是1
     */
    public void setContentLayout(final View view , int index) {
        this.index = index;
       mContextMathfirst.removeView(preview);
         //再添加新的子view
        ViewGroup parent=(ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContextMathfirst.addView(view, 0, params);
        preview = view;

    }

    private void initView() {
        int height = ScreenUtil.getScreenHeight(this);
        int width = ScreenUtil.getScreenWidth(this);
        mMathFirstBaseBack =  findViewById(R.id.math_first_base_back);
        mMathFirstBaseBack.setOnClickListener(this);
        para =mMathFirstBaseBack.getLayoutParams();
        para.height=height/10;
        para.width=width/12;
        mMathFirstBaseBack.setLayoutParams(para);
        mMathFirstBaseTitle = (TextView) findViewById(R.id.math_first_base_title);
        mMathFirstBaseSubtitle = (TextView) findViewById(R.id.math_first_base_subtitle);


        mShengyv = (TextView) findViewById(R.id.shengyv);
        mScore = (ImageView) findViewById(R.id.score);
        mLevel = (TextView) findViewById(R.id.level);
        mFrom = (TextView) findViewById(R.id.from);
        mArrowLeft = findViewById(R.id.arrow_left);
        mArrowLeft.setOnClickListener(this);

        mArrowRight =  findViewById(R.id.arrow_right);
        mArrowRight.setOnClickListener(this);
        mContextMathfirst = (FrameLayout) findViewById(R.id.context_mathfirst);

        mDescription = (TextView) findViewById(R.id.description);

        mRv = (RelativeLayout) findViewById(R.id.rv);
        para = mRv.getLayoutParams();
        para.height=height/10;
        mRv.setLayoutParams(para);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lkw", "onStart: ");
    }
    protected void onResume() {
        super.onResume();

//        setContentView(R.layout.activity_math_first_base);
//        initView();
//        initData();
//        setlevel(index);
        if(DataUtil.ismusicplay&&DataUtil.backmusic!=null)
            DataUtil.backmusic.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lkw", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lkw", "onStop: ");
//        mContextMathfirst.removeAllViews();
        flag=1;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lkw", "onRestart: "+flag);
    }

    public void setmScore(int score) {
        mScore.setImageResource(score);
    }


    /**
     * 隐藏右箭头
     */
    public void hiderightarrow() {
        mArrowRight.setVisibility(View.GONE);
    }

    /**
     * 隐藏左箭头
     */
    public void hideleftarrow() {
        mArrowLeft.setVisibility(View.GONE);
    }

    /**
     * show箭头
     */
    public void showarrow() {
        mArrowLeft.setVisibility(View.VISIBLE);
        mArrowRight.setVisibility(View.VISIBLE);
    }

    /**
     * 设置说明
     *
     * @param description
     */
    public void setdescription(String description) {
        mDescription.setText(description);
    }

    /**
     * 设置副标题
     *
     * @param subtitle
     */
    public void setsubtitle(String subtitle) {
        mMathFirstBaseSubtitle.setText(subtitle);
    }

    /**
     * 设置主标题
     * @param title
     */
    public void settitle(String title) {
        mMathFirstBaseTitle.setText(title);
    }
    public void settitlebackground(int color){
        mMathFirstBaseTitle.setBackgroundResource(color);
    }
    /**
     * 设置左下角的关数
     *
     * @param level 传入 01 02 保证一致性
     */
    public void setlevel(int level) {
        mLevel.setText("0" + level);
    }

    /**
     * 隐藏右上角的剩余
     */
    public void hideshengyv() {
        mShengyv.setVisibility(View.INVISIBLE);
    }
    /**
     * 显示右上角的剩余
     */
    public void showshengyv() {
        mShengyv.setVisibility(View.VISIBLE);
    }
    /**
     * 设置左下角内容
     *
     * @param form 左下角【引人入胜的数学 来自MAGSPACE】的内容
     */
    public void setform(String form) {
        mFrom.setText(form);
    }



    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
            default:
                break;
            case R.id.math_first_base_back:
                finish();
                break;
            case R.id.arrow_left:
                break;
            case R.id.arrow_right:
                break;
        }
    }

}
