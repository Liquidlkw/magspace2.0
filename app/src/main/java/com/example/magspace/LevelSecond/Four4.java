package com.example.magspace.LevelSecond;

import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;

public class Four4 extends BasePageActivity implements View.OnClickListener {

    /**
     * 1
     */
    private TextView mCellnum1;
    /**
     * 2
     */
    private TextView mCellnum2;
    /**
     * 3
     */
    private TextView mCellnum3;
    /**
     * 6
     */
    private TextView mCellnum4;
    /**
     * x
     */
    private TextView mCellnum5;
    /**
     * ÷
     */
    private TextView mCellnum6;
    /**
     * =
     */
    private TextView mCellnum7;
    /**
     *
     */
    private TextView mQe1;
    /**
     *
     */
    private TextView mQe5;
    /**
     *
     */
    private TextView mQe2;
    /**
     *
     */
    private TextView mQe6;
    /**
     *
     */
    private TextView mQe3;
    /**
     *
     */
    private TextView mQe7;
    /**
     *
     */
    private TextView mQe4;
    /**
     *
     */
    private TextView mQe8;

    /**
     *
     */
    private TextView mEq1;
    /**
     *
     */
    private TextView mEq3;
    /**
     *
     */
    private TextView mEq2;
    /**
     *
     */
    private TextView mEq4;

    private  int i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_four4);
        ChangSubtileColor(R.color.colorblue);
        init("找一找", "", "组织核心概念");
        setmPageNumber("04/06");

        initView();
    }

    private void initView() {
        mCellnum1 = (TextView) findViewById(R.id.cellnum1);
        mCellnum1.setOnClickListener(this);
        mCellnum2 = (TextView) findViewById(R.id.cellnum2);
        mCellnum2.setOnClickListener(this);
        mCellnum3 = (TextView) findViewById(R.id.cellnum3);
        mCellnum3.setOnClickListener(this);
        mCellnum4 = (TextView) findViewById(R.id.cellnum4);
        mCellnum4.setOnClickListener(this);


        mQe1 = (TextView) findViewById(R.id.qe1);
        mQe1.setOnClickListener(this);
        mQe5 = (TextView) findViewById(R.id.qe5);
        mQe5.setOnClickListener(this);
        mQe2 = (TextView) findViewById(R.id.qe2);
        mQe2.setOnClickListener(this);
        mQe6 = (TextView) findViewById(R.id.qe6);
        mQe6.setOnClickListener(this);
        mQe3 = (TextView) findViewById(R.id.qe3);
        mQe3.setOnClickListener(this);
        mQe7 = (TextView) findViewById(R.id.qe7);
        mQe7.setOnClickListener(this);
        mQe4 = (TextView) findViewById(R.id.qe4);
        mQe4.setOnClickListener(this);
        mQe8 = (TextView) findViewById(R.id.qe8);
        mQe8.setOnClickListener(this);






        mEq1 = (TextView) findViewById(R.id.eq1);
        mEq1.setOnClickListener(this);
        mEq3 = (TextView) findViewById(R.id.eq3);
        mEq3.setOnClickListener(this);
        mEq2 = (TextView) findViewById(R.id.eq2);
        mEq2.setOnClickListener(this);
        mEq4 = (TextView) findViewById(R.id.eq4);
        mEq4.setOnClickListener(this);
    }

    void switchi(){
        switch (i)
        {

            case 1:
                mQe1.setText("3");
               DataUtil.correctwithoutpic(mCellnum3);

                break;

            case 2:
                mQe2.setText("6");
                DataUtil.correctwithoutpic(mCellnum4);

                break;
            case 3:
                mQe3.setText("6");
                DataUtil.correctwithoutpic(mCellnum4);

                break;
            case 4:
                mQe4.setText("3");
                DataUtil.correctwithoutpic(mCellnum3);

                break;

            case 5:
                mQe5.setText("6");
                DataUtil.correctwithoutpic(mCellnum4);

                break;
            case 6:
                mQe6.setText("3");
                DataUtil.correctwithoutpic(mCellnum3);

                break;

            case 7:
                mQe7.setText("2");
                DataUtil.correctwithoutpic(mCellnum2);

                break;

            case 8:
                mQe8.setText("1");
                DataUtil.correctwithoutpic(mCellnum1);

                break;

            case 9:
                mEq1.setText("2");
                DataUtil.correctwithoutpic(mCellnum2);

                break;


            case 10:
                mEq2.setText("1");
                DataUtil.correctwithoutpic(mCellnum1);

                break;


            case 11:
                mEq3.setText("1");
                DataUtil.correctwithoutpic(mCellnum1);

                break;


            case 12:
                mEq4.setText("2");
                DataUtil.correctwithoutpic(mCellnum2);

                break;

        }
    }
    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
            default:
                break;
            case R.id.cellnum1:

                switchi();

                break;
            case R.id.cellnum2:

                switchi();

                break;
            case R.id.cellnum3:

                switchi();

                break;
            case R.id.cellnum4:

                switchi();
                break;

            case R.id.qe1:
                i=1;
                AnimUtil.setShowAnimation(mQe1,500);
                switchi();


                break;
            case R.id.qe5:
                i=5;
                AnimUtil.setShowAnimation(mQe5,500);
                switchi();

                break;
            case R.id.qe2:
                i=2;
                AnimUtil.setShowAnimation(mQe2,500);
                switchi();

                break;
            case R.id.qe6:
                i=6;
                AnimUtil.setShowAnimation(mQe6,500);
                switchi();
                break;
            case R.id.qe3:
                i=3;
                AnimUtil.setShowAnimation(mQe3,500);
                switchi();
                break;
            case R.id.qe7:
                i=7;
                AnimUtil.setShowAnimation(mQe7,500);
                switchi();
                break;
            case R.id.qe4:
                i=4;
                AnimUtil.setShowAnimation(mQe4,500);
                switchi();

                break;
            case R.id.qe8:
                i=8;
                AnimUtil.setShowAnimation(mQe8,500);
                switchi();

                break;

            case R.id.eq1:
                i=9;
                AnimUtil.setShowAnimation(mEq1,500);
                switchi();
                break;
            case R.id.eq3:
                i=11;
                AnimUtil.setShowAnimation(mEq3,500);
                switchi();

                break;
            case R.id.eq2:
                i=10;
                AnimUtil.setShowAnimation(mEq2,500);
                switchi();

                break;
            case R.id.eq4:
                i=12;
                AnimUtil.setShowAnimation(mEq4,500);
                switchi();
                break;
        }
    }
}
