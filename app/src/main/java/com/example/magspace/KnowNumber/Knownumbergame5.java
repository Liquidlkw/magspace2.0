package com.example.magspace.KnowNumber;


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
import com.example.magspace.CreatePic.CreatePicgame4;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Knownumbergame5 extends KnownumbergameFather {
    public static Knownumbergame5 instance;

    public static  Knownumbergame5 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Knownumbergame5(context, display);
        }
        return instance;
    }

    public Knownumbergame5(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display) {

            super.init(display);
            rightcheckanimator = ObjectAnimator.ofFloat(Knownumbergame5.this, "rightcheckprogress", 0.0f, 1.0f);
            rightcheckanimator.setDuration(750);
            checkanimator = ObjectAnimator.ofFloat(Knownumbergame5.this, "checkprogress", 0.0f, 1.0f);
            checkanimator.setDuration(750);
            shadowanimator = ObjectAnimator.ofFloat(Knownumbergame5.this, "shadowprogress", 0.0f, 1.0f);
            shadowanimator.setDuration(300);
            index=5;
            pic_num=maxpic_num=8;
        //12个判断区
        regions=new Touchregion[12];
        rectfs =new RectF[12];
        minrectfs =new RectF[19];
        for(int i=0;i<12;i++) {
            rectfs[i]=new RectF((30+(i%4)*10) * onep_screen_w, 15 * onep_screen_h +(i/4)*10 * onep_screen_w,
                    (40+(i%4)*10)* onep_screen_w, 15 * onep_screen_h +(i/4+1)*10 * onep_screen_w);
            minrectfs[i]=new RectF((31+(i%4)*10) * onep_screen_w, 15 * onep_screen_h +(i/4)*10 * onep_screen_w+onep_screen_w,
                    (39+(i%4)*10)* onep_screen_w, 15 * onep_screen_h +(i/4+1)*10 * onep_screen_w-onep_screen_w);
        }
        minrectfs[18]=new RectF(27 * onep_screen_w, 15 * onep_screen_h -3*onep_screen_w,
                73* onep_screen_w, 15 * onep_screen_h +30 * onep_screen_w+3*onep_screen_w);
        for(int i=0;i<12;i++){
            path = new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path);
            regions[i].point_x=(int)((35+(i%3)*10) * onep_screen_w);
            regions[i].point_y=(int)(15 * onep_screen_h +((i/3)*10 +5)* onep_screen_w);
        }
        regions[0].id=1;
        regions[0].typename="blue";
        regions[0].isright=true;
        regions[1].typename="blue";
        regions[1].id=4;
        regions[1].isright=true;
        regions[2].typename="red";
        regions[2].id=1;
        regions[3].typename="orange";
        regions[3].id=2;
        regions[4].typename="pink";
        regions[4].id=6;
        regions[7].typename="yellow";
        regions[7].id=3;
        regions[8].typename="light_blue";
        regions[8].id=5;
        regions[9].typename="orange";
        regions[9].id=2;
        regions[10].typename="light_greem";
        regions[10].id=4;
        regions[11].typename="orange";
        regions[11].id=2;
        //六个数字选择区域
            chooserectfs= new RectF[6];
            for(int i=0;i<6;i++){
                chooserectfs[i]=new RectF((12*i+14)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                        (12*i+24)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);
                minrectfs[i+12]=new RectF((12*i+15)*onep_screen_w,85*onep_screen_h-4*onep_screen_w,
                        (12*i+23)*onep_screen_w,85*onep_screen_h+4*onep_screen_w);

            }
            chooseregions=new Touchregion[6];

            for(int i=0;i<6;i++){
                path = new Path();
                path.addRect(chooserectfs[i], Path.Direction.CW);
                chooseregions[i]=new Touchregion(path);
                chooseregions[i].point_x=(int)((12*i+19)*onep_screen_w);
                chooseregions[i].point_y=(int)(85*onep_screen_h);
                chooseregions[i].id=i+1;
            }
            chooseregions[0].typename="red";
            chooseregions[1].typename="orange";
            chooseregions[2].typename="yellow";
            chooseregions[3].typename="light_green";
            chooseregions[4].typename="light_blue";
            chooseregions[5].typename="pink";
    }
    Bitmap back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page5mainimg)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page5mainimg)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //区域判断
        if(isdown) {
            for (int i = 0; i <regions.length ; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //点击到正确区域
                if (re.contains((int) Point_x, (int) Point_y)&&i!=5&&i!=6) {
                    isdown=false;
                    isshadow=false;
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    if (!regions[i].isright) {
                        for(int j=0;j<regions.length;j++){
                            regions[j].istouch=false;
                        }
                        regions[i].istouch=true;
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
                        curretypename=regions[i].typename;
                        curreid=regions[i].id;
                    } else {
                    }
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }
            }
            if(isshadow&&isdown){
                isdown=false;
                for(int i=0;i<chooseregions.length;i++){
                    //计算控制点的边界
                    chooseregions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        if(chooseregions[i].id==curreid&&!regions[curreindex].isright){
                            pic_num--;
                            isright=true;
                            regions[curreindex].isright=true;
                            getright(chooseregions[i].point_x,chooseregions[i].point_y);
                            if(pic_num==0&&DataUtil.isvoiceplay){
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                            }
                        }
                        else if(chooseregions[i].id!=curreid&&!regions[curreindex].isright){
                            isright=false;
                            getwrong(chooseregions[i].point_x,chooseregions[i].point_y);
                        }
                    }
                }
            }
        }

        //选中阴影
        for (int i = 0; i < regions.length; i++) {
            if (regions[i].istouch) {
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(10);
                paint.setAlpha(100);
                c.drawRect(rectfs[i], paint);
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
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 25 * onep_screen_h), (int)(1.2*Screen_w), Screen_h);
            c.drawRect(shadowrect,shadowpaint);
        }
        //外框
        c.drawBitmap(back,null,minrectfs[18],paint);
        //数字区域
        //黄底黑框
        for(int i=0;i<regions.length;i++){
            if(i!=5&&i!=6) {
                c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].getTypename()), null, rectfs[i], paint);
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.FILL);
                c.drawRect(minrectfs[i], paint);
            }
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            c.drawRect(rectfs[i],paint);
            //c.drawRect(new RectF(regions[i].point_x-4.5f*onep_screen_w,regions[i].point_y-4.5f*onep_screen_w,regions[i].point_x+4.5f*onep_screen_w,regions[i].point_y+4.5f*onep_screen_w),paint);
        }
        //色块和数字快
        for(int i=0;i<regions.length;i++){
            if(regions[i].isright){
                c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].typename),null,rectfs[i],paint);
                switch (regions[i].id){
                    case 1:       c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit1),null,minrectfs[i],paint);break;
                    case 2:       c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit2),null,minrectfs[i],paint);break;
                    case 3:       c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit3),null,minrectfs[i],paint);break;
                    case 4:       c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit4),null,minrectfs[i],paint);break;
                    case 5:       c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit5),null,minrectfs[i],paint);break;
                    case 6:       c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit6),null,minrectfs[i],paint);break;
                }
            }
        }


        //数字选择区域
        if(isshadow) {
            for (int i = 0; i < chooseregions.length; i++) {
                c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].getTypename()), null, chooserectfs[i], paint);
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.FILL);
                c.drawRect(minrectfs[i + 12], paint);
                //c.drawRect(new RectF(regions[i].point_x-4.5f*onep_screen_w,regions[i].point_y-4.5f*onep_screen_w,regions[i].point_x+4.5f*onep_screen_w,regions[i].point_y+4.5f*onep_screen_w),paint);
            }

            for (int i = 0; i < chooseregions.length; i++) {
                    switch (chooseregions[i].id){
                        case 1: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit1),null,minrectfs[12+i],paint);break;
                        case 2: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit2),null,minrectfs[12+i],paint);break;
                        case 3: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit3),null,minrectfs[12+i],paint);break;
                        case 4: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit4),null,minrectfs[12+i],paint);break;
                        case 5: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit5),null,minrectfs[12+i],paint);break;
                        case 6: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit6),null,minrectfs[12+i],paint);break;
                }
            }
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
