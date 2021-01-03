package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Chess4 extends BasePageActivity implements View.OnClickListener {

    private ImageView mI1;
    private ImageView mI2;
    private ImageView mI3;
    private ImageView mI4;
    private ImageView mI5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_chess4);
        init("找一找", "", "组织核心概念");
        ChangSubtileColor(R.color.colorgreen);
        setmPageNumber("04/06");
        initView();
    }

    private void initView() {
        mI1 = (ImageView) findViewById(R.id.i1);
        mI1.setOnClickListener(this);
        mI2 = (ImageView) findViewById(R.id.i2);
        mI2.setOnClickListener(this);
        mI3 = (ImageView) findViewById(R.id.i3);
        mI3.setOnClickListener(this);
        mI4 = (ImageView) findViewById(R.id.i4);
        mI4.setOnClickListener(this);
        mI5 = (ImageView) findViewById(R.id.i5);
        mI5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.i1:
                DataUtil.error(mI1);
                break;
            case R.id.i2:
                DataUtil.correct(mI2,R.drawable.book3_section3_page4_img2_right);
                break;
            case R.id.i3:
                DataUtil.correct(mI3,R.drawable.book3_section3_page4_img3_right);
                break;
            case R.id.i4:
                DataUtil.correct(mI4,R.drawable.book3_section3_page4_img4_right);
                break;
            case R.id.i5:
                DataUtil.correct(mI5,R.drawable.book3_section3_page4_img5_right);
                break;
        }
    }
}
