package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Rectangular6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mRow1;
    private ImageView mRow2;
    private ImageView mRow3;
    private ImageView mRow4;
    private ImageView mRow5;
    private ImageView mRow6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_rectangular6);
        ChangSubtileColor(R.color.colorblue);
        init("复习", "", "温故知新");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mRow1 = (ImageView) findViewById(R.id.row1);
        mRow1.setOnClickListener(this);
        mRow2 = (ImageView) findViewById(R.id.row2);
        mRow2.setOnClickListener(this);
        mRow3 = (ImageView) findViewById(R.id.row3);
        mRow3.setOnClickListener(this);
        mRow4 = (ImageView) findViewById(R.id.row4);
        mRow4.setOnClickListener(this);
        mRow5 = (ImageView) findViewById(R.id.row5);
        mRow5.setOnClickListener(this);
        mRow6 = (ImageView) findViewById(R.id.row6);
        mRow6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.row1:
                DataUtil.correct(mRow1,R.drawable.book2_section5_page6_img2_right);
                break;
            case R.id.row2:
                DataUtil.error(mRow2);
                break;
            case R.id.row3:
                DataUtil.correct(mRow3,R.drawable.book2_section5_page6_img4_right);
                break;
            case R.id.row4:
                DataUtil.error(mRow4);
                break;
            case R.id.row5:
                DataUtil.correct(mRow5,R.drawable.book2_section5_page6_img6_right);
                break;
            case R.id.row6:
                DataUtil.correct(mRow6,R.drawable.book2_section5_page6_img7_right);
                break;
        }
    }
}
