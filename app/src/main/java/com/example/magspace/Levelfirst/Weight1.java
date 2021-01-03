package com.example.magspace.Levelfirst;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.Dialog.T;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Weight1 extends BasePageActivity  {

    private RadioGroup mQ1;
    private RadioGroup mQ2;
    /**
     * 重
     */
    private RadioButton mA1;
    /**
     * 轻
     */
    private RadioButton mA2;
    /**
     * 重
     */
    private RadioButton mA3;
    /**
     * 轻
     */
    private RadioButton mA4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_weight1);
        showtoplayout();
        init("3.重量比较", "利用天平定量比较不同对象的重量并通过实践进行重量估计", "做好准备", "掌握主要概念", "比较对象的重量");
        setmPageNumber("01/06");
        initView();
    }

    private void initView() {
        mQ1 = (RadioGroup) findViewById(R.id.q1);
        mQ1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (mA2.getId()==checkedId)
                    {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        com.example.magspace.Dialog.T.showAnimSuccessToast(Weight1.this,"正确！");
                    }else {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                        com.example.magspace.Dialog.T.showAnimErrorToast(Weight1.this,"请仔细思考！");
                    }
            }
        });
        mQ2 = (RadioGroup) findViewById(R.id.q2);
        mQ2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mA3.getId()==checkedId)
                {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                        com.example.magspace.Dialog.T.showAnimSuccessToast(Weight1.this,"正确！");

                }else {
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                    com.example.magspace.Dialog.T.showAnimErrorToast(Weight1.this,"请仔细思考！");

                }
            }
        });


        mA1 = (RadioButton) findViewById(R.id.a1);
        mA2 = (RadioButton) findViewById(R.id.a2);

        mA3 = (RadioButton) findViewById(R.id.a3);
        mA4 = (RadioButton) findViewById(R.id.a4);

    }


}
