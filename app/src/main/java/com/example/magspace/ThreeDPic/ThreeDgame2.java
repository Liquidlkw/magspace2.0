package com.example.magspace.ThreeDPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;

public class ThreeDgame2 extends ThreeDgameFather {
    public static ThreeDgame2 instance;

    public static  ThreeDgame2 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new ThreeDgame2(context, display);
        }
        return instance;
    }

    public ThreeDgame2(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(ThreeDgame2.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(ThreeDgame2.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        index=2;
        pic_num=maxpic_num=1;
        //四个判断区域
        regions=new Touchregion[4];
        rectfs= new RectF[4];
        rectfs[0]=new RectF((int)(10*onep_screen_w),(int)(20*onep_screen_h),(int)(20*onep_screen_w),(int)(20*onep_screen_h+10*onep_screen_w));
        rectfs[1]=new RectF((int)(80*onep_screen_w),(int)(20*onep_screen_h),(int)(90*onep_screen_w),(int)(20*onep_screen_h+10*onep_screen_w));
        rectfs[2]=new RectF((int)(10*onep_screen_w),(int)(70*onep_screen_h),(int)(20*onep_screen_w),(int)(70*onep_screen_h+10*onep_screen_w));
        rectfs[3]=new RectF((int)(80*onep_screen_w),(int)(70*onep_screen_h),(int)(90*onep_screen_w),(int)(70*onep_screen_h+10*onep_screen_w));
        for(int i=0;i<4;i++) {
            path = new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path);
            switch (i){
                case 0:   regions[i].point_x=(int)(15*onep_screen_w);
                    regions[i].point_y=(int)(20*onep_screen_h+5*onep_screen_w);break;
                case 1:   regions[i].point_x=(int)(85*onep_screen_w);
                    regions[i].point_y=(int)(20*onep_screen_h+5*onep_screen_w);break;
                case 2:  regions[i].point_x=(int)(15*onep_screen_w);
                    regions[i].point_y=(int)(70*onep_screen_h+5*onep_screen_w);break;
                case 3:   regions[i].point_x=(int)(85*onep_screen_w);
                    regions[i].point_y=(int)(70*onep_screen_h+5*onep_screen_w);break;
            }
            if(i==1){
                regions[i].setTypename("right");
            }
            else
                regions[i].setTypename("wrong");
        }
    }
    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s5page2img)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s5page2img)).getBitmap() : null;
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s5page2choose1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s5page2choose1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s5page2choose2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s5page2choose2)).getBitmap() : null;
    Bitmap choose3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s5page2choose3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s5page2choose3)).getBitmap() : null;
    Bitmap choose4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s5page2choose4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s5page2choose4)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //中间图形
        c.drawBitmap(Back,null,new Rect((int)(25*onep_screen_w),(int)(15*onep_screen_h),(int)(75*onep_screen_w),(int)(85*onep_screen_h)),paint);
        for(int i=0;i<regions.length;i++){
            switch (i){
                case 0: c.drawBitmap(choose1,null,rectfs[i],paint);break;
                case 1: c.drawBitmap(choose2,null,rectfs[i],paint);break;
                case 2: c.drawBitmap(choose3,null,rectfs[i],paint);break;
                case 3: c.drawBitmap(choose4,null,rectfs[i],paint);break;
            }
        }
        super.onDraw(c);
    }
}
