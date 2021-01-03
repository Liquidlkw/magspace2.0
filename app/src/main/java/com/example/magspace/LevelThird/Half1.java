package com.example.magspace.LevelThird;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Half1 extends BasePageActivity implements View.OnClickListener {

    private ImageView mT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_half1);
        showtoplayout();
        ChangSubtileColor(R.color.colorgreen);
        init("8.半正多面体", "给出正多面体截去角后的多面体形状", "做好准备", "掌握主要概念", "画出正多面体顶角截去后的横截面  ");
        setmPageNumber("01/06");

        initView();
    }

    private void initView() {
        mT = (ImageView) findViewById(R.id.t);
        mT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.t:
                showbottomdialog();
                break;
        }
    }

    void showbottomdialog() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.half_bottomdialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels - 80;
        params.bottomMargin = 50;
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了

        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

        bottomDialog.findViewById(R.id.square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtil.error((ImageView) bottomDialog.findViewById(R.id.square));
            }
        });

        bottomDialog.findViewById(R.id.triangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.triangle));
                bottomDialog.dismiss();
                mT.setImageResource(R.drawable.book3_section8_page1_cell2);
            }
        });

        bottomDialog.findViewById(R.id.circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtil.error((ImageView) bottomDialog.findViewById(R.id.circle));
            }
        });
        bottomDialog.show();
    }
}
