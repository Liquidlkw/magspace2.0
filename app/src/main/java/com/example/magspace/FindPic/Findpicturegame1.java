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
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Findpicturegame1 extends FindpicturegameFather {


    public static Findpicturegame1 instance;

    public static  Findpicturegame1 getInstance(Context context,Display display){
        if (instance == null) {
            instance=new Findpicturegame1(context, display);
        }
        return instance;
    }

    public Findpicturegame1(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display)
    {
        super.init(display);
        checkanimator = ObjectAnimator.ofFloat(Findpicturegame1.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        index=1;
        regions=new Touchregion[7];
        rects= new Rect[5];
        rects[0]=new Rect((int)(76*onep_screen_w),(int)(91*onep_screen_h),(int)(79*onep_screen_w),(int)(95*onep_screen_h));
        rects[1]=new Rect((int)(81*onep_screen_w),(int)(91*onep_screen_h),(int)(84*onep_screen_w),(int)(95*onep_screen_h));
        rects[2]=new Rect((int)(86*onep_screen_w),(int)(91*onep_screen_h),(int)(89*onep_screen_w),(int)(95*onep_screen_h));
        rects[3]=new Rect((int)(91*onep_screen_w),(int)(91*onep_screen_h),(int)(94*onep_screen_w),(int)(95*onep_screen_h));
        rects[4]=new Rect((int)(96*onep_screen_w),(int)(91*onep_screen_h),(int)(99*onep_screen_w),(int)(95*onep_screen_h));
        pic_num=regions.length-1;
        //第一个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*11,onep_screen_h*34);
        path.lineTo(onep_screen_w*37, onep_screen_h*34);
        path.lineTo(onep_screen_w*37, onep_screen_h*54);
        path.lineTo(onep_screen_w*11, onep_screen_h*54);
        path.close();
        regions[1]=new Touchregion(path);
        ObjectAnimator animator = ObjectAnimator.ofFloat(Findpicturegame1.this, "progress1", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[1].setAnimator(animator);
        //第二个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*11,onep_screen_h*54);
        path.lineTo(onep_screen_w*37, onep_screen_h*54);
        path.lineTo(onep_screen_w*37, onep_screen_h*94);
        path.lineTo(onep_screen_w*11, onep_screen_h*94);
        path.close();
        regions[2]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame1.this, "progress2", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[2].setAnimator(animator);
        //第三个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*48,onep_screen_h*60);
        path.lineTo(onep_screen_w*60, onep_screen_h*55);
        path.lineTo(onep_screen_w*68, onep_screen_h*69);
        path.lineTo(onep_screen_w*45, onep_screen_h*79);
        path.close();
        regions[3]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame1.this, "progress3", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[3].setAnimator(animator);
        //第四个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*60,onep_screen_h*25);
        path.lineTo(onep_screen_w*71, onep_screen_h*15);
        path.lineTo(onep_screen_w*78, onep_screen_h*33);
        path.lineTo(onep_screen_w*67, onep_screen_h*43);;
        path.close();
        regions[4]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame1.this, "progress4", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[4].setAnimator(animator);
        //第五个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*67,onep_screen_h*43);
        path.lineTo(onep_screen_w*78, onep_screen_h*53);
        path.lineTo(onep_screen_w*78, onep_screen_h*72);
        path.lineTo(onep_screen_w*67, onep_screen_h*62);
        path.close();
        regions[5]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame1.this, "progress5", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[5].setAnimator(animator);
        //第六个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*78,onep_screen_h*53);
        path.lineTo(onep_screen_w*89, onep_screen_h*43);
        path.lineTo(onep_screen_w*89, onep_screen_h*62);
        path.lineTo(onep_screen_w*78, onep_screen_h*72);
        path.close();
        regions[6]=new Touchregion(path);
        animator = ObjectAnimator.ofFloat(Findpicturegame1.this, "progress6", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[6].setAnimator(animator);
    }


    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book0_page1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book0_page1)).getBitmap() : null;

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
        c.drawText("MAGSPACEE四边形部件", 79*onep_screen_w, 84*onep_screen_h, paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square),null,rects[0],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square2),null,rects[1],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square_big),null,rects[2],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.prismatic),null,rects[3],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.trapezoid),null,rects[4],paint);
        super.onDraw(c);
    }
}
