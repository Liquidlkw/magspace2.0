package com.example.magspace.LevelSecond;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Possibility1 extends BasePageActivity implements View.OnClickListener {

    private ImageView mC1;
    private ImageView mC2;
    private ImageView mC3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_possibility1);
        showtoplayout();
        ChangSubtileColor(R.color.colorblue);
        init("8.可能性", "经常了解可能发生的结果从而提高直接的概率", "做好准备", "掌握主要概念", "那个转盘最有可能转向蓝色？");
        setmPageNumber("01/06");

        initView();
    }

    private void initView() {
        mC1 = (ImageView) findViewById(R.id.c1);
        mC1.setOnClickListener(this);
        mC2 = (ImageView) findViewById(R.id.c2);
        mC2.setOnClickListener(this);
        mC3 = (ImageView) findViewById(R.id.c3);
        mC3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.c1:
                DataUtil.error(mC1);
                break;
            case R.id.c2:
                DataUtil.error(mC2);
                break;
            case R.id.c3:
                DataUtil.correct(mC3,R.drawable.book2_section8_page1_img3_right);
                break;
        }
    }
}
