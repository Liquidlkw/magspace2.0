package com.example.magspace.Levelfirst;

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

public class ViewPoint6 extends BasePageActivity implements View.OnClickListener {

    private ImageView mW1;
    private ImageView mW2;
    private ImageView mW3;
    private ImageView mW4;
    private ImageView mW5;
    private ImageView mW6;
    private Dialog dialog;

      public Number6choosenum currgame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_view_point6);
        init("复习", "", "找出下图中相应的视角图案");
        setmPageNumber("06/06");
        initView();
    }

    private void initView() {
        mW1 = (ImageView) findViewById(R.id.w1);
        mW1.setOnClickListener(this);
        mW2 = (ImageView) findViewById(R.id.w2);
        mW2.setOnClickListener(this);
        mW3 = (ImageView) findViewById(R.id.w3);
        mW3.setOnClickListener(this);
        mW4 = (ImageView) findViewById(R.id.w4);
        mW4.setOnClickListener(this);
        mW5 = (ImageView) findViewById(R.id.w5);
        mW5.setOnClickListener(this);
        mW6 = (ImageView) findViewById(R.id.w6);
        mW6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.w1:
                dialog = showdialog();
               onDialogClick(mW1,R.drawable.book1_section7_page6_cell1,
                       dialog.findViewById(R.id.square)
                       , dialog.findViewById(R.id.triangle)
                       ,dialog.findViewById(R.id.circle));
                break;
            case R.id.w2:
                dialog = showdialog();
                onDialogClick(mW2,R.drawable.book1_section7_page6_cell1,
                        dialog.findViewById(R.id.square)
                        , dialog.findViewById(R.id.triangle)
                        ,dialog.findViewById(R.id.circle));

                break;
            case R.id.w3:
                dialog = showdialog();
                onDialogClick(mW3,R.drawable.book1_section7_page6_cell3,
                        dialog.findViewById(R.id.circle)
                        , dialog.findViewById(R.id.triangle)
                        ,dialog.findViewById(R.id.square));

                break;
            case R.id.w4:
                dialog = showdialog();
                onDialogClick(mW4,R.drawable.book1_section7_page6_cell2,
                        dialog.findViewById(R.id.triangle)
                        , dialog.findViewById(R.id.circle)
                        ,dialog.findViewById(R.id.square));

                break;
            case R.id.w5:
                dialog = showdialog();
                onDialogClick(mW5,R.drawable.book1_section7_page6_cell3,
                        dialog.findViewById(R.id.circle)
                        , dialog.findViewById(R.id.triangle)
                        ,dialog.findViewById(R.id.square));

                break;
            case R.id.w6:
                dialog = showdialog();
                onDialogClick(mW6,R.drawable.book1_section7_page6_cell3,
                        dialog.findViewById(R.id.circle)
                        , dialog.findViewById(R.id.triangle)
                        ,dialog.findViewById(R.id.square));

                break;
        }
    }



Dialog showdialog(){
    final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
    View contentView = LayoutInflater.from(this).inflate(R.layout.viewpoint_bottomdialog, null);
    bottomDialog.setContentView(contentView);
    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
    ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
    layoutParams.width = getResources().getDisplayMetrics().widthPixels - 80;
    params.bottomMargin = 50;
    contentView.setLayoutParams(params);
    bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
    //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了

    bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    return bottomDialog;

}

void onDialogClick(ImageView current,int id,ImageView correctimageView,ImageView error1,ImageView erroe2 ){
    dialog.show();
    correctimageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataUtil.correctwithoutpic(correctimageView);
            current.setImageResource(id);
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    dialog.dismiss();
                }}, 1000);
//
        }
    });
    error1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataUtil.error(error1);
        }
    });

    erroe2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataUtil.error(erroe2);
        }
    });

}
}
