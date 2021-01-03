package com.example.magspace.Levelfirst;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;

public class Number1 extends BasePageActivity implements View.OnClickListener {

    private ImageView mPic1;
    private ImageView mPic2;
    private ImageView mPic3;
    private ImageView mPic4;
    private int i = 0;
    private ImageView mA1;
    private ImageView mA2;
    private ImageView mA3;
    private ImageView mA4;
    private RelativeLayout rootview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_number1);
        initView();
        showtoplayout();
    }

    private void initView() {
        rootview = findViewById(R.id.snakebar_rootview);
        mPic1 = (ImageView) findViewById(R.id.pic1);
        mPic1.setOnClickListener(this);
        mPic2 = (ImageView) findViewById(R.id.pic2);
        mPic2.setOnClickListener(this);
        mPic3 = (ImageView) findViewById(R.id.pic3);
        mPic3.setOnClickListener(this);
        mPic4 = (ImageView) findViewById(R.id.pic4);
        mPic4.setOnClickListener(this);

        mA1 = (ImageView) findViewById(R.id.a1);
        mA2 = (ImageView) findViewById(R.id.a2);
        mA3 = (ImageView) findViewById(R.id.a3);
        mA4 = (ImageView) findViewById(R.id.a4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pic1:
                show2();
                i = 1;
                break;
            case R.id.pic2:
                i = 2;
                show2();
                break;
            case R.id.pic3:
                show2();
                i = 3;
                break;
            case R.id.pic4:
                i = 4;
                show2();
                break;
        }
    }

    private void show2() {

        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_content_circle, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels - 80;
        params.bottomMargin = 50;
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了

        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 2) {
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.b1));
                    mA2.setImageResource(R.drawable.number3);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            bottomDialog.dismiss();
                        }}, 500);
                } else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.error( (ImageView) bottomDialog.findViewById(R.id.b1));
            }
            }
        });

        bottomDialog.findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==4){
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.b2));
                    mA4.setImageResource(R.drawable.number4);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            bottomDialog.dismiss();
                        }}, 500);
                }else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.error( (ImageView) bottomDialog.findViewById(R.id.b2));
                }
            }
        });

        bottomDialog.findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1){
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.b3));
                    mA1.setImageResource(R.drawable.number5);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            bottomDialog.dismiss();
                        }}, 500);
                }else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.error( (ImageView) bottomDialog.findViewById(R.id.b3));
                }

            }
        });


        bottomDialog.findViewById(R.id.b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==3){
                    ToastUtil.getInstance().showToast("答对了！");
                    DataUtil.correctwithoutpic((ImageView) bottomDialog.findViewById(R.id.b4));
                    mA3.setImageResource(R.drawable.number8);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            bottomDialog.dismiss();
                        }}, 500);
                }else {
                    ToastUtil.getInstance().showToast("请仔细思考");
                    DataUtil.error( (ImageView) bottomDialog.findViewById(R.id.b4));
                }
            }
        });
        bottomDialog.show();
    }
}
