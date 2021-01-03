package com.example.magspace.FindPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Findpicturegame3 extends FindpicturegameFather {
    public static Findpicturegame3 instance;
    public static  Findpicturegame3 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Findpicturegame3(context, display);
        }
        return instance;
    }

    public Findpicturegame3(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display)
    {
        super.init(display);
        isneedchoose=true;
        curretypename=null;
        checkanimator = ObjectAnimator.ofFloat(Findpicturegame3.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        index=3;
        regions=new Touchregion[10];
        rects= new Rect[3];
        rects[0]= new Rect((int)(76*onep_screen_w),(int)(88*onep_screen_h),(int)(80*onep_screen_w),(int)(95*onep_screen_h));
        rects[1]=new Rect((int)(83*onep_screen_w),(int)(88*onep_screen_h),(int)(87*onep_screen_w),(int)(95*onep_screen_h));
        rects[2]=new Rect((int)(90*onep_screen_w),(int)(88*onep_screen_h),(int)(94*onep_screen_w),(int)(95*onep_screen_h));

        new Rect((int)(76*onep_screen_w),(int)(88*onep_screen_h),(int)(80*onep_screen_w),(int)(95*onep_screen_h));
        pic_num=regions.length-1;
        //第一个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*22,onep_screen_h*25);
        path.lineTo(onep_screen_w*34, onep_screen_h*34);
        path.lineTo(onep_screen_w*22, onep_screen_h*43);
        path.close();
        regions[1]=new Touchregion(path);
        regions[1].setTypename("tangle");
        ObjectAnimator animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress1", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[1].setAnimator(animator);
        //第二个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*34,onep_screen_h*34);
        path.lineTo(onep_screen_w*47, onep_screen_h*34);
        path.lineTo(onep_screen_w*40, onep_screen_h*50);
        path.close();
        regions[2]=new Touchregion(path);
        regions[2].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress2", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[2].setAnimator(animator);
        //第三个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*22,onep_screen_h*43);
        path.lineTo(onep_screen_w*34, onep_screen_h*34);
        path.lineTo(onep_screen_w*40, onep_screen_h*50);
        path.lineTo(onep_screen_w*29, onep_screen_h*60);
        path.close();
        regions[3]=new Touchregion(path);
        regions[3].setTypename("square");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress3", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[3].setAnimator(animator);
        //第四个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*29,onep_screen_h*60);
        path.lineTo(onep_screen_w*40, onep_screen_h*50);
        path.lineTo(onep_screen_w*40, onep_screen_h*69);
        path.close();
        regions[4]=new Touchregion(path);
        regions[4].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress4", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[4].setAnimator(animator);
        //第五个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*40,onep_screen_h*50);
        path.lineTo(onep_screen_w*53, onep_screen_h*50);
        path.lineTo(onep_screen_w*53, onep_screen_h*69);
        path.lineTo(onep_screen_w*40, onep_screen_h*69);
        path.close();
        regions[5]=new Touchregion(path);
        regions[5].setTypename("square");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress5", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[5].setAnimator(animator);
        //第六个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*53,onep_screen_h*50);
        path.lineTo(onep_screen_w*66, onep_screen_h*50);
        path.lineTo(onep_screen_w*66, onep_screen_h*69);
        path.lineTo(onep_screen_w*53, onep_screen_h*69);
        path.close();
        regions[6]=new Touchregion(path);
        regions[6].setTypename("square");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress6", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[6].setAnimator(animator);
        //第七个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*40,onep_screen_h*69);
        path.lineTo(onep_screen_w*53, onep_screen_h*69);
        path.lineTo(onep_screen_w*48, onep_screen_h*85);
        path.lineTo(onep_screen_w*35, onep_screen_h*85);
        path.close();
        regions[7]=new Touchregion(path);
        regions[7].setTypename("prismatic");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress7", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[7].setAnimator(animator);
        //第八个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*53,onep_screen_h*69);
        path.lineTo(onep_screen_w*66, onep_screen_h*69);
        path.lineTo(onep_screen_w*73, onep_screen_h*85);
        path.lineTo(onep_screen_w*60, onep_screen_h*85);
        path.close();
        regions[8]=new Touchregion(path);
        regions[8].setTypename("prismatic");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress8", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[8].setAnimator(animator);
        //第九个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*66,onep_screen_h*50);
        path.lineTo(onep_screen_w*78, onep_screen_h*41);
        path.lineTo(onep_screen_w*78, onep_screen_h*60);
        path.lineTo(onep_screen_w*66, onep_screen_h*69);
        path.close();
        regions[9]=new Touchregion(path);
        regions[9].setTypename("prismatic");
        animator = ObjectAnimator.ofFloat(Findpicturegame3.this, "progress9", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[9].setAnimator(animator);

        chooseregions=new Touchregion[3];
        //第一个选择区域
        path = new Path();
        path.addRect(76*onep_screen_w,87*onep_screen_h,83*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[0]=new Touchregion(path,"tangle",3);
        chooseregions[0].setPoint_x(81);
        chooseregions[0].setPoint_y(93);
        //第二个选择区域
        path = new Path();
        path.addRect(83*onep_screen_w,87*onep_screen_h,90*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[1]=new Touchregion(path,"square",3);
        chooseregions[1].setPoint_x(88);
        chooseregions[1].setPoint_y(93);
        //第三个选择区域
        path = new Path();
        path.addRect(90*onep_screen_w,87*onep_screen_h,97*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[2]=new Touchregion(path,"prismatic",3);
        chooseregions[2].setPoint_x(95);
        chooseregions[2].setPoint_y(93);
        curretypename=chooseregions[0].typename;
        chooseregions[0].istouch = true;
    }

    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book0_page3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book0_page3)).getBitmap() : null;

    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        c.drawBitmap(Back,null,new Rect((int)(22*onep_screen_w),(int)(15*onep_screen_h),(int)(78*onep_screen_w),(int)(95*onep_screen_h)),paint);
        //右下角方框
        paint.setColor(this.getResources().getColor(R.color.colorred));
        c.drawRect(75*onep_screen_w,80*onep_screen_h,99*onep_screen_w,85*onep_screen_h,paint);
        paint.setStyle(Paint.Style.STROKE);
        c.drawRect(75*onep_screen_w,80*onep_screen_h,99*onep_screen_w,99*onep_screen_h,paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20*fontrite);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        c.drawText("MAGSPACEE部件", 81*onep_screen_w, 84*onep_screen_h, paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle),null,rects[0],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square),null,rects[1],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.prismatic),null,rects[2],paint);
        super.onDraw(c);
    }
}
