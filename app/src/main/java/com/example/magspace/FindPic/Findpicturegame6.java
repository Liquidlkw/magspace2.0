package com.example.magspace.FindPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.GameUtil;

public class Findpicturegame6 extends FindpicturegameFather {
    public static Findpicturegame6 instance;
    public static Findpicturegame6 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Findpicturegame6(context, display);
        }
        return instance;
    }

    public Findpicturegame6(Context context, Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display)
    {
        super.init(display);
        isneedchoose=true;
        curretypename=null;
        checkanimator = ObjectAnimator.ofFloat(Findpicturegame6.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        index=6;
        regions=new Touchregion[8];
        rects= new Rect[4];
        rects[0]= new Rect((int)(76*onep_screen_w),(int)(88*onep_screen_h),(int)(79.5f*onep_screen_w),(int)(94*onep_screen_h));
        rects[1]=new Rect((int)(81.5f*onep_screen_w),(int)(88*onep_screen_h),(int)(84*onep_screen_w),(int)(94*onep_screen_h));
        rects[2]=new Rect((int)(87*onep_screen_w),(int)(88*onep_screen_h),(int)(90.5*onep_screen_w),(int)(94*onep_screen_h));
        rects[3]=new Rect((int)(92.5f*onep_screen_w),(int)(88*onep_screen_h),(int)(96*onep_screen_w),(int)(94*onep_screen_h));
        new Rect((int)(76*onep_screen_w),(int)(88*onep_screen_h),(int)(80*onep_screen_w),(int)(95*onep_screen_h));
        pic_num=regions.length-1;
        //第一个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*27,onep_screen_h*70);
        path.lineTo(onep_screen_w*33, onep_screen_h*54);
        path.lineTo(onep_screen_w*39, onep_screen_h*70);
        path.close();
        regions[1]=new Touchregion(path);
        regions[1].setTypename("tangle");
        ObjectAnimator animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress1", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[1].setAnimator(animator);
        //第二个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*61,onep_screen_h*70);
        path.lineTo(onep_screen_w*67, onep_screen_h*54);
        path.lineTo(onep_screen_w*73, onep_screen_h*70);
        path.close();
        regions[2]=new Touchregion(path);
        regions[2].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress2", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[2].setAnimator(animator);
        //第三个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*27,onep_screen_h*70);
        path.lineTo(onep_screen_w*39, onep_screen_h*70);
        path.lineTo(onep_screen_w*39, onep_screen_h*87);
        path.lineTo(onep_screen_w*27, onep_screen_h*87);
        path.close();
        regions[3]=new Touchregion(path);
        regions[3].setTypename("square");
        animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress3", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[3].setAnimator(animator);
        //第四个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*73,onep_screen_h*70);
        path.lineTo(onep_screen_w*61, onep_screen_h*70);
        path.lineTo(onep_screen_w*61, onep_screen_h*87);
        path.lineTo(onep_screen_w*73, onep_screen_h*87);
        path.close();
        regions[4]=new Touchregion(path);
        regions[4].setTypename("square");
        animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress4", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[4].setAnimator(animator);
        //第五个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*39,onep_screen_h*53);
        path.lineTo(onep_screen_w*61, onep_screen_h*53);
        path.lineTo(onep_screen_w*61, onep_screen_h*87);
        path.lineTo(onep_screen_w*39, onep_screen_h*87);
        path.close();
        regions[5]=new Touchregion(path);
        regions[5].setTypename("square_big");
        animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress5", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[5].setAnimator(animator);
        //第六个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*39,onep_screen_h*53);
        path.lineTo(onep_screen_w*50, onep_screen_h*23);
        path.lineTo(onep_screen_w*50, onep_screen_h*53);
        path.close();
        regions[6]=new Touchregion(path);
        regions[6].setTypename("tangle3");
        animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress6", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[6].setAnimator(animator);
        //第七个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*50,onep_screen_h*23);
        path.lineTo(onep_screen_w*61, onep_screen_h*53);
        path.lineTo(onep_screen_w*50, onep_screen_h*53);
        path.close();
        regions[7]=new Touchregion(path);
        regions[7].setTypename("tangle3");
        animator = ObjectAnimator.ofFloat(Findpicturegame6.this, "progress7", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[7].setAnimator(animator);

        chooseregions=new Touchregion[4];
        //第一个选择区域
        path = new Path();
        path.addRect(76*onep_screen_w,87*onep_screen_h,81.5f*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[0]=new Touchregion(path,"tangle",2);
        chooseregions[0].setPoint_x(80);
        chooseregions[0].setPoint_y(93);
        //第二个选择区域
        path = new Path();
        path.addRect(81.5f*onep_screen_w,87*onep_screen_h,87*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[1]=new Touchregion(path,"square",2);
        chooseregions[1].setPoint_x(85);
        chooseregions[1].setPoint_y(93);
        //第三个选择区域
        path = new Path();
        path.addRect(87*onep_screen_w,87*onep_screen_h,92.5f*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[2]=new Touchregion(path,"square_big",1);
        chooseregions[2].setPoint_x(91);
        chooseregions[2].setPoint_y(93);
        //第四个选择区域
        path = new Path();
        path.addRect(92.5f*onep_screen_w,87*onep_screen_h,98*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[3]=new Touchregion(path,"tangle3",2);
        chooseregions[3].setPoint_x(96);
        chooseregions[3].setPoint_y(93);
        curretypename=chooseregions[0].typename;
        chooseregions[0].istouch = true;
    }

    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book0_page6)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book0_page6)).getBitmap() : null;

    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        c.drawBitmap(Back,null,new Rect((int)(27*onep_screen_w),(int)(10*onep_screen_h),(int)(73*onep_screen_w),(int)(95*onep_screen_h)),paint);
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
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square_big),null,rects[2],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle3),null,rects[3],paint);
        super.onDraw(c);
    }
}
