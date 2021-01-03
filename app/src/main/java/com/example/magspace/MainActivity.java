package com.example.magspace;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.magspace.Dialog.Option;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.model.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView buid;
    private ImageView course;
    private ImageView product;
    private ImageView control;
    private ImageView mImageView4;

    /**
     * 视频展示
     */
    private ImageView avi;
    /**
     * 商城
     */
    private ImageView shop;
    private ImageView mImageView2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);//恢复原有的样式
        //背景音效
        DataUtil.ismusicplay = true;
        DataUtil.isvoiceplay = true;
        DataUtil.initmuscic();
        DataUtil.backmusic.start();
        DataUtil.backmusic.setLooping(true);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        buid = (ImageView) findViewById(R.id.imageView6);
//        para = buid.getLayoutParams();
//        para.height = ScreenUtil.getScreenHeight(this) / 7;
//        para.width = ScreenUtil.getScreenWidth(this) / 7;
//        buid.setLayoutParams(para);
        buid.setOnClickListener(this);


//        tranlate = AnimationUtils.loadAnimation(this, R.anim.translate_plane);
//        plane.startAnimation(tranlate);


//        para = car.getLayoutParams();
//        para.width = ScreenUtil.getScreenWidth(this) / 2;
//        car.setLayoutParams(para);
//        tranlate1 = AnimationUtils.loadAnimation(this, R.anim.translate_car);
//        car.startAnimation(tranlate1);



//        tranlate2 = AnimationUtils.loadAnimation(this, R.anim.translate_robot);
//        robot.startAnimation(tranlate2);
        course = (ImageView) findViewById(R.id.imageView7);
//        para = course.getLayoutParams();
//        para.height = ScreenUtil.getScreenHeight(this) / 7;
//        para.width = ScreenUtil.getScreenWidth(this) / 7;
//        course.setLayoutParams(para);
        course.setOnClickListener(this);
        product = (ImageView) findViewById(R.id.imageView8);
//        para = product.getLayoutParams();
//        para.height = ScreenUtil.getScreenHeight(this) / 7;
//        para.width = ScreenUtil.getScreenWidth(this) / 7;
//        product.setLayoutParams(para);

        product.setOnClickListener(this);
        control = (ImageView) findViewById(R.id.imageView9);
        product = (ImageView) findViewById(R.id.imageView8);
//        para = control.getLayoutParams();
//        para.height = ScreenUtil.getScreenHeight(this) / 7;
//        para.width = ScreenUtil.getScreenWidth(this) / 7;
//        control.setLayoutParams(para);
        control.setOnClickListener(this);
        /**
         * 设置
         */
        mImageView4 = (ImageView) findViewById(R.id.imageView4);
        mImageView4.setOnClickListener(this);
        mImageView4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mImageView4.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mImageView4.clearColorFilter();
                }
                return false;
            }
        });


        avi = (ImageView) findViewById(R.id.button3);
//        para = avi.getLayoutParams();
//        para.height = ScreenUtil.getScreenHeight(this) / 7;
//        para.width = ScreenUtil.getScreenWidth(this) / 7;
//        avi.setLayoutParams(para);
        avi.setOnClickListener(this);
        shop = (ImageView) findViewById(R.id.imageView5);
        shop.setOnClickListener(this);
//        mImageView = (ImageView) findViewById(R.id.imageView);
//        mImageView.setOnClickListener(this);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView2.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
        switch (v.getId()) {
            default:
                break;
            case R.id.imageView6:
                //搭建演示
                buid.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                startActivity(new Intent(MainActivity.this, BuildingDemoActivity.class));
                break;
            case R.id.imageView7:
                //课程教学
                course.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                startActivity(new Intent(MainActivity.this, CoursesActivity.class));
                break;
            case R.id.imageView8:
                //产品展示
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
                product.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.imageView9:
                //控制天地...待开发
                control.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                startActivity(new Intent(MainActivity.this, BluechoiceActivity.class));
                //Intent intent = new Intent(MainActivity.this,BlueMainActivity.class);
                //startActivity(intent);
                control.clearColorFilter();
                break;
            case R.id.imageView4:
                //设置
                Option optiondialog = new Option(MainActivity.this, R.style.Dialog);
                optiondialog.show();
                break;
            case R.id.button3:
                //视频展示
                avi.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                startActivity(new Intent(MainActivity.this, AviActivity.class));
                break;
            case R.id.imageView5:
                //商城
                shop.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                Uri uri = Uri.parse("https://magspace.tmall.com/?spm=a220o.1000855.1997427721.d4918089.6c956032cq0ixO");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent1);
                break;
            case R.id.imageView2:
                startActivity(new Intent(MainActivity.this,AboutMe.class));
                break;

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        buid.clearColorFilter();
        course.clearColorFilter();
        product.clearColorFilter();
        avi.clearColorFilter();
        shop.clearColorFilter();

    }

    @Override
    protected void onResume() {
        super.onResume();
        buid.clearColorFilter();
        course.clearColorFilter();
        product.clearColorFilter();
        avi.clearColorFilter();
        shop.clearColorFilter();
        if (DataUtil.ismusicplay && DataUtil.backmusic != null)
            DataUtil.backmusic.start();

        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }



}
