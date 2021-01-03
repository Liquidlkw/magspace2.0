package com.example.magspace.SymmertyPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Sysmertypicgame4 extends SymmertypicgameFather {
    public static Sysmertypicgame4 instance;

    public static  Sysmertypicgame4 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Sysmertypicgame4(context, display);
        }
        return instance;
    }

    public Sysmertypicgame4(Context context,Display display) {
        super(context);
        this.init(display);
        index=4;
        pic_num=maxpic_num=3;
        rightcheckanimator = ObjectAnimator.ofFloat(Sysmertypicgame4.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Sysmertypicgame4.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Sysmertypicgame4.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        //四个判断区域
        regions=new Touchregion[4];
        rectfs= new RectF[4];
        for(int i=0;i<4;i++){
            rectfs[i]=new RectF((50+(i%2)*10) * onep_screen_w, 25 * onep_screen_h +(i/2)*10 * onep_screen_w,
                    (60+(i%2)*10) * onep_screen_w, 25 * onep_screen_h +(i/2+1)*10 * onep_screen_w);
        }
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path);
            regions[i].isright=true;
        }
        //四个选择区域
        chooserectfs= new RectF[4];
        minrectfs=new RectF[8];
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*i+20)*onep_screen_w,85*onep_screen_h,
                    (20*i+25)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);
            minrectfs[i]=new RectF((20*i+21)*onep_screen_w,85*onep_screen_h-4*onep_screen_w,
                    (20*i+24)*onep_screen_w,85*onep_screen_h-onep_screen_w);

        }
        chooseregions=new Touchregion[4];
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((20*i+22.5f)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h+2.5f*onep_screen_w);
        }
        chooseregions[0].typename="red";
        chooseregions[1].typename="blue";
        chooseregions[2].typename="green";
        chooseregions[3].typename="yellow";
        //四个第二区域
        regions2=new Touchregion[4];
        rectfs2= new RectF[4];
        for(int i=0;i<4;i++){
            rectfs2[i]=new RectF(20 * onep_screen_w, 12 * onep_screen_h +i*8 * onep_screen_w,
                    28*onep_screen_w, 12 * onep_screen_h +(i+1)*8 * onep_screen_w);
            minrectfs[i+4]=new RectF(20 * onep_screen_w, 12 * onep_screen_h +(i*8 +3)* onep_screen_w,
                    28*onep_screen_w, 12 * onep_screen_h +((i+1)*8 -3)* onep_screen_w);
        }
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(rectfs2[i], Path.Direction.CW);
            regions2[i]=new Touchregion(path);
            regions2[i].isright=false;
        }
        regions2[0].istouch=true;
        regions2[0].isright=true;
        regions[0].typename="red";
        regions[1].typename="blue";
        regions[2].typename="yellow";
        regions[3].typename="green";
    }
    public void getrightpic(int index){
        for(int i=0;i<regions.length;i++){
            regions[i].istouch=false;
        }
        isshadow=false;
        checkpaint.setAlpha(0);
        rightcheckpaint.setAlpha(0);
        if(index==0){
            squresum=4;
            for(int i=0;i<regions.length;i++){
                regions[i].isright=true;
            }
            regions[0].typename="red";
            regions[1].typename="blue";
            regions[2].typename="yellow";
            regions[3].typename="green";
        }
        if(index==1){
            squresum=0;
            for(int i=0;i<regions.length;i++){
                regions[i].isright=false;
            }
            regions[0].typename="yellow";
            regions[1].typename="green";
            regions[2].typename="red";
            regions[3].typename="blue";
        }
        if(index==2){
            squresum=0;
            for(int i=0;i<regions.length;i++){
                regions[i].isright=false;
            }
            regions[0].typename="green";
            regions[1].typename="yellow";
            regions[2].typename="blue";
            regions[3].typename="red";
        }
        if(index==3){
            squresum=0;
            for(int i=0;i<regions.length;i++){
                regions[i].isright=false;
            }
            regions[0].typename="red";
            regions[1].typename="green";
            regions[2].typename="yellow";
            regions[3].typename="blue";
        }
    }
    public void init(Display display) {
        super.init(display);
    }
    Bitmap red = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4component1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4component1)).getBitmap() : null;
    Bitmap blue = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4component2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4component2)).getBitmap() : null;
    Bitmap green = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4component3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4component3)).getBitmap() : null;
    Bitmap yellow = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4component4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4component4)).getBitmap() : null;
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4number1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4number1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4number2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4number2)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4number3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4number3)).getBitmap() : null;
    Bitmap c4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4number4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4number4)).getBitmap() : null;
    Bitmap one = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4componentnum1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4componentnum1)).getBitmap() : null;
    Bitmap two = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4componentnum2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4componentnum2)).getBitmap() : null;
    Bitmap three = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4componentnum3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4componentnum3)).getBitmap() : null;
    Bitmap four = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page4componentnum4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page4componentnum4)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //区域判定,选择区域判断;
        if (isdown) {
            for (int i = 0; i < regions.length; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    for(int j=0;j<regions.length;j++){
                        regions[j].istouch=false;
                    }
                    regions[i].istouch=true;
                    isdown = false;
                    isshadow = false;
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    if (!regions[i].isright) {
                        curreindex = i;
                        isshadow = true;
                        shadowflag = false;
                        curretypename = regions[i].typename;
                        curreid = regions[i].id;
                    } else {
                    }
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }

            }
            for (int i = 0; i < regions2.length; i++) {
                //计算控制点的边界
                regions2[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions2[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown = false;
                    regions2[i].istouch = true;
                    for (int j = 0; j < regions2.length; j++) {
                        if (j != i)
                            regions2[j].istouch = false;
                    }
                    regionindex=i;
                    getrightpic(i);
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }
            }
            if (isshadow && isdown) {
                isdown = false;
                for (int i = 0; i < chooseregions.length; i++) {
                    //计算控制点的边界
                    chooseregions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        if (chooseregions[i].typename == curretypename && !regions[curreindex].isright) {
                            Log.i("zh", "squrenum: "+squresum);
                            squresum++;
                            isright = true;
                            regions[curreindex].isright = true;
                            getright(chooseregions[i].point_x, chooseregions[i].point_y);
                            if (squresum == 4 && DataUtil.isvoiceplay) {
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                            }
                            if(squresum==4){
                                if(regions2[regionindex].isright==false) {
                                    pic_num--;
                                    regions2[regionindex].isright=true;
                                }
                            }
                        } else if (chooseregions[i].typename != curretypename && !regions[curreindex].isright) {
                            isright = false;
                            getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                        }
                    }
                }
            }
        }

        //色块和粉红底黑框
        for(int i=0;i<regions.length;i++){
            paint.setStyle(Paint.Style.FILL);
            paint.setARGB(185, 255, 193, 193);
            c.drawRect(rectfs[i], paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(1);
            c.drawRect(rectfs[i], paint);
            if(regions[i].isright&&regions[i].typename!=null){
                if(regions[i].typename=="red")
                    c.drawBitmap(red,null,rectfs[i],paint);
                if(regions[i].typename=="blue")
                    c.drawBitmap(blue,null,rectfs[i],paint);
                if(regions[i].typename=="yellow")
                    c.drawBitmap(yellow,null,rectfs[i],paint);
                if(regions[i].typename=="green")
                    c.drawBitmap(green,null,rectfs[i],paint);
            }
        }
       //左侧选择框
        c.drawBitmap(c1,null,minrectfs[4],paint);
        c.drawBitmap(c2,null,minrectfs[5],paint);
        c.drawBitmap(c3,null,minrectfs[6],paint);
        c.drawBitmap(c4,null,minrectfs[7],paint);
        //选择阴影

        for(int i=0;i<regions.length;i++){
            if(regions[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs[i],paint);
                paint.setAlpha(255);
            }
            if(regions2[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs2[i],paint);
                paint.setAlpha(255);
            }
        }
        //下方阴影
        if(isshadow){
            //没有开始
            if(!shadowflag) {
                Log.i("zh", "shadow start");
                shadowprogress = 0;
                shadowpaint.setAlpha(100);
                shadowanimator.start();
                shadowflag=true;
            }
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 24 * onep_screen_h), (int)(1.2*Screen_w), Screen_h);
            c.drawRect(shadowrect,shadowpaint);
        }
        //下方选项
        if(isshadow){
            c.drawBitmap(red,null,chooserectfs[0],paint);
            c.drawBitmap(blue,null,chooserectfs[1],paint);
            c.drawBitmap(green,null,chooserectfs[2],paint);
            c.drawBitmap(yellow,null,chooserectfs[3],paint);
            c.drawBitmap(one,null,minrectfs[0],paint);
            c.drawBitmap(two,null,minrectfs[1],paint);
            c.drawBitmap(three,null,minrectfs[2],paint);
            c.drawBitmap(four,null,minrectfs[3],paint);
        }
        //勾叉
        if(isright){
            c.drawPath(rightcheckpath,rightcheckpaint);
        }
        else{
            c.drawPath(checkpath,checkpaint);
        }
    }
}
