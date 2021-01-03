package com.example.magspace.LevelThird;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class Half5 extends BasePageActivity implements View.OnClickListener {

    private ImageView mQ1;
    private ImageView mQ2;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_half5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻", "", "截去正方体各边的一半得到对应的图形");
        setmPageNumber("05/06");
        initView();
    }

    private void initView() {
        mQ1 = (ImageView) findViewById(R.id.q1);
        mQ1.setOnClickListener(this);
        mQ2 = (ImageView) findViewById(R.id.q2);
        mQ2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.q1:
                i=1;
                showbottomdialog();
                break;
            case R.id.q2:
                i=2;
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
                if (i==1) {
                    DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.triangle));
                    bottomDialog.dismiss();
                    mQ1.setImageResource(R.drawable.book3_section8_page1_cell2);
                }else {
                    DataUtil.error((ImageView) bottomDialog.findViewById(R.id.triangle));
                }
            }
        });

        bottomDialog.findViewById(R.id.circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==2){
                    DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.circle));
                    bottomDialog.dismiss();
                    mQ2.setImageResource(R.drawable.book3_section8_page1_cell3);
                }else {
                    DataUtil.error((ImageView) bottomDialog.findViewById(R.id.circle));
                }

            }
        });
        bottomDialog.show();
    }
}
