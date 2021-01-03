package com.example.magspace.LevelThird;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Angle4 extends BasePageActivity implements View.OnClickListener {

    private LinearLayout mM1;
    private LinearLayout mM2;
    private LinearLayout mM3;
    private LinearLayout mM4;
    private int i;
    private TextView mT1;
    private TextView mT2;
    private TextView mT3;
    private TextView mT4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_angle4);
        ChangSubtileColor(R.color.colorgreen);
        init("找一找", "", "组织核心概念");
        setmPageNumber("04/06");

        initView();
    }

    private void initView() {
        mM1 = (LinearLayout) findViewById(R.id.m1);
        mM1.setOnClickListener(this);
        mM2 = (LinearLayout) findViewById(R.id.m2);
        mM2.setOnClickListener(this);
        mM3 = (LinearLayout) findViewById(R.id.m3);
        mM3.setOnClickListener(this);
        mM4 = (LinearLayout) findViewById(R.id.m4);
        mM4.setOnClickListener(this);
        mT1 = (TextView) findViewById(R.id.t1);
        mT2 = (TextView) findViewById(R.id.t2);
        mT3 = (TextView) findViewById(R.id.t3);
        mT4 = (TextView) findViewById(R.id.t4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.m1:
                i=1;
                showbottomdialog();
                break;
            case R.id.m2:
                i=2;
                showbottomdialog();
                break;
            case R.id.m3:
                i=3;
                showbottomdialog();
                break;
            case R.id.m4:
                i=4;
                showbottomdialog();
                break;
        }
    }

    void showbottomdialog() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.angle4_bottomdiaglog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels - 80;
        params.bottomMargin = 50;
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了

        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.findViewById(R.id.angle120).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    ToastUtil.getInstance().showToast("答对了！");
                   DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle120));
                    mT1.setText("120°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle120));
                }
            }
        });

        bottomDialog.findViewById(R.id.angle60).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 2) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle60));
                    mT2.setText("60°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle60));
                }
            }
        });

        bottomDialog.findViewById(R.id.angle45).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 4) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle45));
                    mT4.setText("45°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle45));
                }
            }
        });

        bottomDialog.findViewById(R.id.angle90).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 3) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle90));
                    mT3.setText("90°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle90));
                }
            }
        });
        bottomDialog.show();
    }
}
