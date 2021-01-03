package com.example.magspace.Levelfirst;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.Dialog.T;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Weight4 extends BasePageActivity implements View.OnClickListener {

    private ImageView mP1;
    private ImageView mP2;
    private ImageView mP3;
    private ImageView mP4;
    private ImageView mP5;
    private ImageView mP6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_weight4);
        setmPageNumber("04/06");
        init("找一找", "", "利用天平找出更重的一边");
        initView();
    }

    private void initView() {
        mP1 = (ImageView) findViewById(R.id.p1);
        mP1.setOnClickListener(this);
        mP2 = (ImageView) findViewById(R.id.p2);
        mP2.setOnClickListener(this);
        mP3 = (ImageView) findViewById(R.id.p3);
        mP3.setOnClickListener(this);
        mP4 = (ImageView) findViewById(R.id.p4);
        mP4.setOnClickListener(this);
        mP5 = (ImageView) findViewById(R.id.p5);
        mP5.setOnClickListener(this);
        mP6 = (ImageView) findViewById(R.id.p6);
        mP6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.p1:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                T.showAnimErrorToast(this,"可惜~");
                break;
            case R.id.p2:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
               T.showAnimSuccessToast(this,"答对了！");
                mP2.setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
                break;
            case R.id.p3:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                T.showAnimSuccessToast(this,"答对了！");
                mP3.setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
                break;
            case R.id.p4:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                T.showAnimErrorToast(this,"可惜~");
                break;
            case R.id.p5:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                T.showAnimSuccessToast(this,"答对了！");
                mP5.setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
                break;
            case R.id.p6:
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                T.showAnimErrorToast(this,"可惜~");
                break;
        }
    }
}
