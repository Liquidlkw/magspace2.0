package com.example.magspace.FindPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Findpicturegame2 extends FindpicturegameFather {

    public static Findpicturegame2 instance;

    public static  Findpicturegame2 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Findpicturegame2(context, display);
        }
        return instance;
    }

    public Findpicturegame2(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display)
    {
        super.init(display);
        checkanimator = ObjectAnimator.ofFloat(Findpicturegame2.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        index=2;
        regions=new Touchregion[8];
        rects= new Rect[4];
        rects[0]=new Rect((int)(77*onep_screen_w),(int)(91*onep_screen_h),(int)(81*onep_screen_w),(int)(95*onep_screen_h));
        rects[1]=new Rect((int)(83*onep_screen_w),(int)(89*onep_screen_h),(int)(87*onep_screen_w),(int)(95*onep_screen_h));
        rects[2]=new Rect((int)(89*onep_screen_w),(int)(89*onep_screen_h),(int)(93*onep_screen_w),(int)(95*onep_screen_h));
        rects[3]=new Rect((int)(95*onep_screen_w),(int)(89*onep_screen_h),(int)(99*onep_screen_w),(int)(95*onep_screen_h));
        pic_num=regions.length-1;
        //第一个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*16.5f,onep_screen_h*24);
        path.lineTo(onep_screen_w*10, onep_screen_h*57);
        path.lineTo(onep_screen_w*23, onep_screen_h*57);
        path.close();
        regions[1]=new Touchregion(path);
        ObjectAnimator animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress1", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[1].setAnimator(animator);
        //第二个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*29.5f,onep_screen_h*48);
        path.lineTo(onep_screen_w*23, onep_screen_h*62);
        path.lineTo(onep_screen_w*36, onep_screen_h*62);
        path.close();
        regions[2]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress2", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[2].setAnimator(animator);
        //第三个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*48,onep_screen_h*40);
        path.lineTo(onep_screen_w*48, onep_screen_h*71);
        path.lineTo(onep_screen_w*35, onep_screen_h*71);
        path.close();
        regions[3]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress3", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[3].setAnimator(animator);
        //第四个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*48,onep_screen_h*41);
        path.lineTo(onep_screen_w*59, onep_screen_h*67);
        path.lineTo(onep_screen_w*48, onep_screen_h*76);
        path.close();
        regions[4]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress4", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[4].setAnimator(animator);
        //第五个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*62,onep_screen_h*30);
        path.lineTo(onep_screen_w*62, onep_screen_h*46);
        path.lineTo(onep_screen_w*52, onep_screen_h*38);
        path.close();
        regions[5]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress5", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[5].setAnimator(animator);
        //第六个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*61,onep_screen_h*31);
        path.lineTo(onep_screen_w*61, onep_screen_h*14);
        path.lineTo(onep_screen_w*71, onep_screen_h*23);
        path.close();
        regions[6]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress6", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[6].setAnimator(animator);
        //第七个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*76.5f,onep_screen_h*28);
        path.lineTo(onep_screen_w*63, onep_screen_h*60);
        path.lineTo(onep_screen_w*90, onep_screen_h*60);
        path.close();
        regions[7]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame2.this, "progress7", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[7].setAnimator(animator);
    }
    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book0_page2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book0_page2)).getBitmap() : null;
    @Override
    public void onDraw(Canvas c)
    {
        c.drawBitmap(Back,null,new Rect((int)(10*onep_screen_w),(int)(15*onep_screen_h),(int)(90*onep_screen_w),(int)(95*onep_screen_h)),paint);
        //右下角方框
        paint.setColor(this.getResources().getColor(R.color.colorred));
        c.drawRect(75*onep_screen_w,80*onep_screen_h,99*onep_screen_w,85*onep_screen_h,paint);
        paint.setStyle(Paint.Style.STROKE);
        c.drawRect(75*onep_screen_w,80*onep_screen_h,99*onep_screen_w,99*onep_screen_h,paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20*fontrite);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        c.drawText("MAGSPACEE三角形部件", 79*onep_screen_w, 84*onep_screen_h, paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle),null,rects[0],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle_big),null,rects[1],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle2),null,rects[2],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle3),null,rects[3],paint);
        super.onDraw(c);
    }
}
