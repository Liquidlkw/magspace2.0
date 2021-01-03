package com.example.magspace.LevelThird;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Angle6 extends BasePageActivity implements View.OnClickListener {

    private TextView mD1;
    private TextView mD2;
    private TextView mD3;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_angle6);
        ChangSubtileColor(R.color.colorgreen);
        init("复习", "", "10个MAGSPACE的五边形链接在一起如下图所示。看下面的图片并测量一个规则五边形的内角。");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mD1 = (TextView) findViewById(R.id.d1);
        mD1.setOnClickListener(this);
        mD2 = (TextView) findViewById(R.id.d2);
        mD2.setOnClickListener(this);
        mD3 = (TextView) findViewById(R.id.d3);
        mD3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.d1:
                i=1;
                showbottomdialog();
                break;
            case R.id.d2:
                i=2;
                showbottomdialog();
                break;
            case R.id.d3:
                i=3;
                showbottomdialog();
                break;
        }
    }

    void showbottomdialog() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.angle6_bottomdialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels - 80;
        params.bottomMargin = 50;
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了

        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);


        bottomDialog.findViewById(R.id.angle108).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 3) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle108));
                    mD3.setText("108°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle108));
                }
            }
        });

        bottomDialog.findViewById(R.id.angle72).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 2) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle72));
                    mD2.setText("72°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle72));
                }
            }
        });

        bottomDialog.findViewById(R.id.angle36).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 3) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.textcorrect(bottomDialog.findViewById(R.id.angle36));
                    mD1.setText("36°");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            bottomDialog.dismiss();
                        }
                    }, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.texterror(bottomDialog.findViewById(R.id.angle36));
                }
            }
        });
        bottomDialog.show();
    }
}
