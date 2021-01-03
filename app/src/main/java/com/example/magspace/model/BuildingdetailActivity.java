package com.example.magspace.model;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.magspace.R;

public class BuildingdetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mCenterImage;
    private ImageView mBack;
    /**
     * title
     */
    private TextView mTitle;
    private FrameLayout mBg;
    private ImageView mBottomImage;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildingdetail);
        initView();
    }



    private void initView() {
        mCenterImage = (ImageView) findViewById(R.id.center_image);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mBg = (FrameLayout) findViewById(R.id.bg);
        mBottomImage = (ImageView) findViewById(R.id.bottom_image);



        SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                mBg.setBackground(resource);
            }
        };

        Glide.with(this)
                .load(BuildingDemoActivity.list.get(BuildingDemoActivity.i).logosetting)
                .error(R.drawable.errorimage)
                .placeholder(R.drawable.default_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(simpleTarget);



        mTitle.setText(BuildingDemoActivity.list.get(BuildingDemoActivity.i).title);
            Glide.with(this)
                    .asGif()//加载动态图片，若现有图片为非gif图片，则直接加载错误占位图。
                    .load(BuildingDemoActivity.list.get(BuildingDemoActivity.i).gif)
                    .error(R.drawable.errorimage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mBottomImage);

        Glide.with(this)
                .load(BuildingDemoActivity.list.get(BuildingDemoActivity.i).logo)

                .error(R.drawable.errorimage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mCenterImage);







//        Glide.with(this).load(R.mipmap.gifhome_1242x1782).into(mBottomImage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
