package com.example.magspace.Base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class BasePageActivity extends AppCompatActivity  {

    /**
     * 1.数字
     */
    private AppCompatTextView mPageTitle;
    /**
     * 认识数字，培养孩子对数学的初步认识
     */
    private AppCompatTextView mPageDescription;
    private LinearLayout mTopLayout;
    /**
     * 做好准备
     */
    private AppCompatTextView mRedText;
    /**
     * 掌握主要概念
     */
    private AppCompatTextView mBacktitle;
    /**
     * 根据下图的水果种类，写出相应的数字
     */
    private AppCompatTextView mSubDescription;
    private FrameLayout mPageContext;
    public ImageView mPageExit;
    /**
     * 01/06
     */
    private TextView mPageNumber;

    public void ChangSubtileColor(int id)
    {
        mRedText.setTextColor(getResources().getColor(id));
    }

    public void setmPageNumber(String str){
        mPageNumber.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_page);
        initView();
        hidetoplayout();
        mPageContext.removeAllViews();
    }

    private void initView() {
        mPageTitle = (AppCompatTextView) findViewById(R.id.page_title);
        mPageDescription = (AppCompatTextView) findViewById(R.id.page_description);
        mTopLayout = (LinearLayout) findViewById(R.id.top_layout);
        mRedText = (AppCompatTextView) findViewById(R.id.red_text);
        mBacktitle = (AppCompatTextView) findViewById(R.id.backtitle);
        mSubDescription = (AppCompatTextView) findViewById(R.id.sub_description);
        mPageContext =  findViewById(R.id.page_context);
        mPageExit = (ImageView) findViewById(R.id.page_exit);
        mPageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPageNumber = (TextView) findViewById(R.id.page_number);
    }

    public void init(String title, String des,String redtext,String blacktext,String subdes) {
        mPageTitle.setText(title);
        mPageDescription.setText(des);
        mRedText.setText(redtext);
        mBacktitle.setText(blacktext);
        mSubDescription.setText(subdes);
    }
     public void hidetoplayout(){
        mTopLayout.setVisibility(View.GONE);
     }
     public  void showtoplayout(){
         mTopLayout.setVisibility(View.VISIBLE);
     }
     public  void setcontentlayout(int layoutId){
         LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View contentView = inflater.inflate(layoutId,null);
         ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
         mPageContext.addView(contentView,params);
     }
    public void init(String redtext,String blacktext,String subdes) {
        mRedText.setText(redtext);
        mBacktitle.setText(blacktext);
        mSubDescription.setText(subdes);
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
