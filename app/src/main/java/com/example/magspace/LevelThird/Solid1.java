package com.example.magspace.LevelThird;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Solid1 extends BasePageActivity implements View.OnClickListener {

    private ImageView mI1;
    private ImageView mI2;
    private ImageView mI3;
    private ImageView mI4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_solid1);
        showtoplayout();
        ChangSubtileColor(R.color.colorgreen);
        init("6.旋转体", "理解旋转体的特点和原理，能够制作各种类型的旋转体", "做好准备", "掌握主要概念", "找出顶部不同的一个集合图形");
        setmPageNumber("01/06");
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
                DataUtil.error(mI2);
                break;
            case R.id.i3:
                DataUtil.error(mI3);
                break;
            case R.id.i4:
                DataUtil.correct(mI4,R.drawable.book3_section6_page1_img4_right);
                break;
        }
    }
}
