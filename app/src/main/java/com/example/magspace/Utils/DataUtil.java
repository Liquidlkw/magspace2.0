package com.example.magspace.Utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magspace.Levelfirst.Number1;
import com.example.magspace.MainActivity;
import com.example.magspace.MyApplication;
import com.example.magspace.R;

public class DataUtil {
    //背景音乐music和音效voice的全局变量
    public static boolean ismusicplay;
    public static boolean isvoiceplay;
    public static MediaPlayer backmusic;
    public static SoundPool soundPool;

    public static  void initmuscic(){
        //背景音乐
        backmusic = DataUtil.backmusic.create(MyApplication.myContext, R.raw.main_bg_voice);
        backmusic.setVolume(0.5f,0.5f);
        //音效 1是？ 2是选择了已经选中的区域 3是出错 4是过关 5是按键 6是选中正确区域 7是搭建演示点击按钮
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
        soundPool.load(MyApplication.myContext,R.raw.beep,1);
        soundPool.load(MyApplication.myContext,R.raw.choosed,1);
        soundPool.load(MyApplication.myContext,R.raw.error,1);
        soundPool.load(MyApplication.myContext,R.raw.great,1);
        soundPool.load(MyApplication.myContext,R.raw.main_btn_voice,1);
        soundPool.load(MyApplication.myContext,R.raw.right,1);
        soundPool.load(MyApplication.myContext,R.raw.stage_voice,1);

    }

    /**
     * 正确的动画和音效
     * @param view
     * @param id 需要替换为点击正确的图形
     */
    public static void correct(final ImageView view, final int id){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
        final ObjectAnimator animator = AnimUtil.tada(view);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.end();
                view.setImageResource(id);
            }
        }, 1000);

    }

    public static void correctwithoutpic(final ImageView view){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
        final ObjectAnimator animator = AnimUtil.tada(view);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.end();
            }
        }, 1000);

    }

    public static void correctwithoutpic(final TextView view){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
        final ObjectAnimator animator = AnimUtil.tada(view);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.end();
            }
        }, 1000);

    }

    public static void textcorrect(final TextView view){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
        final ObjectAnimator animator = AnimUtil.tada(view);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.end();
            }
        }, 1000);

    }
    public static void error(ImageView view){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
        final ObjectAnimator  nopeAnimator = AnimUtil.nope(view);
        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        nopeAnimator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nopeAnimator.end();
            }
        }, 1000);
    }

    public static void error(TextView view){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
        final ObjectAnimator  nopeAnimator = AnimUtil.nope(view);
        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        nopeAnimator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nopeAnimator.end();
            }
        }, 1000);
    }

    public static void texterror(TextView view){
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
        final ObjectAnimator  nopeAnimator = AnimUtil.nope(view);
        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        nopeAnimator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nopeAnimator.end();
            }
        }, 1000);
    }


}
