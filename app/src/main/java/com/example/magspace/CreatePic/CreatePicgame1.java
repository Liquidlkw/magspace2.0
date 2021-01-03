package com.example.magspace.CreatePic;

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
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.GameUtil;

public class CreatePicgame1 extends CreatePicgameFather {
    public static CreatePicgame1 instance;

    public static  CreatePicgame1 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new CreatePicgame1(context, display);
        }
        return instance;
    }

    public CreatePicgame1(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display){
        super.init(display);
        index=1;
        isneedchoose=true;
        isneedchoose2=true;
        isneedcut=true;
        getpicflag=new boolean[]{true,true,true};
        getpiccut=new boolean[]{false,false,false};
        timeani= new ObjectAnimator[3];
        shotpics=new Bitmap[3];
        checkanimator = ObjectAnimator.ofFloat(CreatePicgame1.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        regions=new Touchregion[12];
        rects= new Rect[3];
        rects[0]= new Rect((int)(77*onep_screen_w),(int)(88*onep_screen_h),(int)(81*onep_screen_w),(int)(95*onep_screen_h));
        rects[1]=new Rect((int)(84*onep_screen_w),(int)(88*onep_screen_h),(int)(88*onep_screen_w),(int)(95*onep_screen_h));
        rects[2]=new Rect((int)(91*onep_screen_w),(int)(88*onep_screen_h),(int)(95*onep_screen_w),(int)(95*onep_screen_h));
        //第一个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*39,onep_screen_h*48);
        path.lineTo(onep_screen_w*48, onep_screen_h*25);
        path.lineTo(onep_screen_w*67, onep_screen_h*25);
        path.lineTo(onep_screen_w*76, onep_screen_h*48);
        path.close();
        regions[1]=new Touchregion(path);
        regions[1].setTypename("trapezoid");
        ObjectAnimator animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress1", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[1].setAnimator(animator);
        //第二个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*39,onep_screen_h*48);
        path.lineTo(onep_screen_w*48, onep_screen_h*70);
        path.lineTo(onep_screen_w*67, onep_screen_h*70);
        path.lineTo(onep_screen_w*76, onep_screen_h*48);
        path.close();
        regions[2]=new Touchregion(path);
        regions[2].setTypename("trapezoid");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress2", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[2].setAnimator(animator);
        //第三个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*39,onep_screen_h*48);
        path.lineTo(onep_screen_w*48, onep_screen_h*25);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.lineTo(onep_screen_w*48, onep_screen_h*70);
        path.close();
        regions[3]=new Touchregion(path);
        regions[3].setTypename("prismatic");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress3", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[3].setAnimator(animator);
        //第四个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*48,onep_screen_h*25);
        path.lineTo(onep_screen_w*67, onep_screen_h*25);
        path.lineTo(onep_screen_w*76, onep_screen_h*48);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[4]=new Touchregion(path);
        regions[4].setTypename("prismatic");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress4", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[4].setAnimator(animator);
        //第五个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*57.5f,onep_screen_h*48);
        path.lineTo(onep_screen_w*76, onep_screen_h*48);
        path.lineTo(onep_screen_w*67, onep_screen_h*70);
        path.lineTo(onep_screen_w*48, onep_screen_h*70);
        path.close();
        regions[5]=new Touchregion(path);
        regions[5].setTypename("prismatic");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress5", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[5].setAnimator(animator);
        //第六个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*39,onep_screen_h*48);
        path.lineTo(onep_screen_w*48, onep_screen_h*25);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[6]=new Touchregion(path);
        regions[6].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress6", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[6].setAnimator(animator);
        //第七个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*48,onep_screen_h*25);
        path.lineTo(onep_screen_w*67, onep_screen_h*25);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[7]=new Touchregion(path);
        regions[7].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress7", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[7].setAnimator(animator);
        //第八个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*67,onep_screen_h*25);
        path.lineTo(onep_screen_w*76, onep_screen_h*48);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[8]=new Touchregion(path);
        regions[8].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress8", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[8].setAnimator(animator);
        //第九个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*76,onep_screen_h*48);
        path.lineTo(onep_screen_w*67, onep_screen_h*70);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[9]=new Touchregion(path);
        regions[9].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress9", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[9].setAnimator(animator);
        //第十个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*67,onep_screen_h*70);
        path.lineTo(onep_screen_w*48, onep_screen_h*70);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[10]=new Touchregion(path);
        regions[10].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress10", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[10].setAnimator(animator);
        //第十一个判定区域的位置
        path = new Path();
        path.moveTo(onep_screen_w*48,onep_screen_h*70);
        path.lineTo(onep_screen_w*39, onep_screen_h*48);
        path.lineTo(onep_screen_w*57.5f, onep_screen_h*48);
        path.close();
        regions[11]=new Touchregion(path);
        regions[11].setTypename("tangle");
        animator = ObjectAnimator.ofFloat(CreatePicgame1.this, "progress11", 0.0f, 1.0f);
        animator.setDuration(1000);
        regions[11].setAnimator(animator);
        chooseregions=new Touchregion[3];
        //第一个选择区域
        path = new Path();
        path.addRect(76*onep_screen_w,87*onep_screen_h,83*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[0]=new Touchregion(path,"trapezoid",2);
        chooseregions[0].setPoint_x(81);
        chooseregions[0].setPoint_y(93);
        //第二个选择区域
        path = new Path();
        path.addRect(83*onep_screen_w,87*onep_screen_h,90*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[1]=new Touchregion(path,"prismatic",3);
        chooseregions[1].setPoint_x(88);
        chooseregions[1].setPoint_y(93);
        //第三个选择区域
        path = new Path();
        path.addRect(90*onep_screen_w,87*onep_screen_h,97*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[2]=new Touchregion(path,"tangle",6);
        chooseregions[2].setPoint_x(95);
        chooseregions[2].setPoint_y(93);

        chooseregions2=new Touchregion[3];
        //第一个特殊选择区域
        path = new Path();
        path.addRect(10*onep_screen_w,30*onep_screen_h,20*onep_screen_w,35*onep_screen_h, Path.Direction.CW);
        chooseregions2[0]=new Touchregion(path,"trapezoid",2);
        chooseregions2[0].setPoint_x(81);
        chooseregions2[0].setPoint_y(93);
        //第二个特殊区域
        path = new Path();
        path.addRect(10*onep_screen_w,45*onep_screen_h,20*onep_screen_w,50*onep_screen_h, Path.Direction.CW);
        chooseregions2[1]=new Touchregion(path,"prismatic",3);
        chooseregions2[1].setPoint_x(88);
        chooseregions2[1].setPoint_y(93);
        //第三个特殊区域
        path = new Path();
        path.addRect(10*onep_screen_w,60*onep_screen_h,20*onep_screen_w,65*onep_screen_h, Path.Direction.CW);
        chooseregions2[2]=new Touchregion(path,"tangle",6);
        chooseregions2[2].setPoint_x(95);
        chooseregions2[2].setPoint_y(93);
    }
    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.section2_page1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.section2_page1)).getBitmap() : null;
    public void onDraw(Canvas c)
    {

        c.drawBitmap(Back,null,new Rect((int)(35*onep_screen_w),(int)(20*onep_screen_h),(int)(80*onep_screen_w),(int)(75*onep_screen_h)),paint);
        //右下角方框
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.getResources().getColor(R.color.colorred));
        c.drawRect(75*onep_screen_w,80*onep_screen_h,99*onep_screen_w,85*onep_screen_h,paint);
        paint.setStyle(Paint.Style.STROKE);
        c.drawRect(75*onep_screen_w,80*onep_screen_h,99*onep_screen_w,99*onep_screen_h,paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20*fontrite);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        c.drawText("MAGSPACEE部件", 81*onep_screen_w, 84*onep_screen_h, paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.trapezoid),null,rects[0],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.prismatic),null,rects[1],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.tangle),null,rects[2],paint);


        super.onDraw(c);
        if(currregion!=null&&currregion.typename=="trapezoid"){
            timeani[0]=currregion.animator;
        }
        else if(currregion!=null&&currregion.typename=="prismatic"){
            timeani[1]=currregion.animator;
        }
        else if(currregion!=null&&currregion.typename=="tangle"){
            timeani[2]=currregion.animator;
        }
        //截图
        for(int i=0;i<chooseregions2.length;i++) {
            if (chooseregions[i].leftnum == 0 && getpicflag[i] && timeani[i] != null && timeani[i].isRunning() == false) {
                getpicflag[i] = false;
                getShotPic(i);
                invalidate();
                this.destroyDrawingCache();
            }
        }
        //画截图
        if(getpicflag[0]==false&&shotpics[0]!=null){
            if(getpiccut[0]==false) {
                shotpics[0] = Bitmap.createBitmap(shotpics[0], (int) (35 * onep_screen_w), (int) (20 * onep_screen_h), (int) (45 * onep_screen_w), (int) (55 * onep_screen_h));
                getpiccut[0]=true;
            }
            c.drawBitmap(shotpics[0], null, new Rect((int) (20.5*onep_screen_w), (int) (35*onep_screen_h-2.5*onep_screen_w),
                    (int) (25.5*onep_screen_w), (int) (35*onep_screen_h+2.5*onep_screen_w)), paint);
        }
         if(getpicflag[1]==false&&shotpics[1]!=null){
             if(getpiccut[1]==false) {
                 shotpics[1] = Bitmap.createBitmap(shotpics[1], (int) (35 * onep_screen_w), (int) (20 * onep_screen_h), (int) (45 * onep_screen_w), (int) (55 * onep_screen_h));
                 getpiccut[1]=true;
             }
             c.drawBitmap(shotpics[1], null, new Rect((int) (20.5*onep_screen_w), (int) (50*onep_screen_h-2.5*onep_screen_w),
                     (int) (25.5*onep_screen_w), (int) (50*onep_screen_h+2.5*onep_screen_w)), paint);
        }
         if(getpicflag[2]==false&&shotpics[2]!=null){
             if(getpiccut[2]==false) {
                 shotpics[2] = Bitmap.createBitmap(shotpics[2], (int) (35 * onep_screen_w), (int) (20 * onep_screen_h), (int) (45 * onep_screen_w), (int) (55 * onep_screen_h));
                 getpiccut[2]=true;
             }
             c.drawBitmap(shotpics[2], null, new Rect((int) (20.5*onep_screen_w), (int) (65*onep_screen_h-2.5*onep_screen_w),
                     (int) (25.5*onep_screen_w), (int) (65*onep_screen_h+2.5*onep_screen_w)), paint);
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20*fontrite);
        paint.setColor(this.getResources().getColor(R.color.colororange));
        c.drawCircle(9*onep_screen_w,34*onep_screen_h,0.5f*onep_screen_w,paint);
        c.drawCircle(9*onep_screen_w,49*onep_screen_h,0.5f*onep_screen_w,paint);
        c.drawCircle(9*onep_screen_w,64*onep_screen_h,0.5f*onep_screen_w,paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        c.drawRect(20*onep_screen_w,35*onep_screen_h-3*onep_screen_w,26*onep_screen_w,35*onep_screen_h+3*onep_screen_w,paint);
        c.drawRect(20*onep_screen_w,50*onep_screen_h-3*onep_screen_w,26*onep_screen_w,50*onep_screen_h+3*onep_screen_w,paint);
        c.drawRect(20*onep_screen_w,65*onep_screen_h-3*onep_screen_w,26*onep_screen_w,65*onep_screen_h+3*onep_screen_w,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(chooseregions2[0].istouch?this.getResources().getColor(R.color.colororange):Color.BLACK);
        c.drawText("2个部分",10*onep_screen_w,35*onep_screen_h,paint);
        paint.setColor(chooseregions2[1].istouch?this.getResources().getColor(R.color.colororange):Color.BLACK);
        c.drawText("3个部分",10*onep_screen_w,50*onep_screen_h,paint);
        paint.setColor(chooseregions2[2].istouch?this.getResources().getColor(R.color.colororange):Color.BLACK);
        c.drawText("6个部分",10*onep_screen_w,65*onep_screen_h,paint);
    }
}
