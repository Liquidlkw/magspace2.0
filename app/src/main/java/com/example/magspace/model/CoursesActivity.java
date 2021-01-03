package com.example.magspace.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.magspace.Base.BaseActivity;
import com.example.magspace.Catalog.MathFirstCatalog;
import com.example.magspace.Catalog.MathSecondCatalog;
import com.example.magspace.MatchPerson.MathPersonActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;

public class CoursesActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 引人入胜的数学
     */
    private Button mBtnFirst;
    /**
     * 我是数学迷
     */
    private Button mBtnSecond;
    /**
     * 数学小能手
     */
    private Button mBtnThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_courses);
        setTitle("课程教学");
        initView();
    }




    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_first:
                AnimUtil.FlipAnimatorXViewShow(mBtnFirst,mBtnFirst,500);
                startActivity(new Intent(CoursesActivity.this, MathFirstCatalog.class));
                break;
            case R.id.btn_second:
                AnimUtil.FlipAnimatorXViewShow(mBtnSecond,mBtnSecond,500);
                startActivity(new Intent(CoursesActivity.this, MathSecondCatalog.class));
                break;
            case R.id.btn_third:
                AnimUtil.FlipAnimatorXViewShow(mBtnThird,mBtnThird,500);
                startActivity(new Intent(CoursesActivity.this, MathPersonActivity.class));
                break;
        }
    }

    private void initView() {


        mBtnFirst = (Button) findViewById(R.id.btn_first);
        mBtnFirst.setOnClickListener(this);
        mBtnSecond = (Button) findViewById(R.id.btn_second);
        mBtnSecond.setOnClickListener(this);
        mBtnThird = (Button) findViewById(R.id.btn_third);
        mBtnThird.setOnClickListener(this);
        AnimUtil.FlipAnimatorXViewShow(mBtnFirst,mBtnFirst,500);
        AnimUtil.FlipAnimatorXViewShow(mBtnSecond,mBtnSecond,500);
        AnimUtil.FlipAnimatorXViewShow(mBtnThird,mBtnThird,500);






    }
}
