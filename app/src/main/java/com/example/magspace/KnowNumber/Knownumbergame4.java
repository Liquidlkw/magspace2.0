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
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Knownumbergame4 extends KnownumbergameFather {
    public static Knownumbergame4 instance;

    public static  Knownumbergame4 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Knownumbergame4(context, display);
        }
        return instance;
    }

    public Knownumbergame4(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display) {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(Knownumbergame4.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Knownumbergame4.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Knownumbergame4.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=4;
        squresum=0;
        pic_num=maxpic_num=3;
        //9个判断区
        regions=new Touchregion[9];
        rectfs =new RectF[9];
        for(int i=0;i<9;i++) {
                rectfs[i]=new RectF((45+(i%3)*10) * onep_screen_w, 15 * onep_screen_h +(i/3)*10 * onep_screen_w,
                        (55+(i%3)*10)* onep_screen_w, 15 * onep_screen_h +(i/3+1)*10 * onep_screen_w);

        }
        for(int i=0;i<9;i++){
                path = new Path();
                path.addRect(rectfs[i], Path.Direction.CW);
                regions[i]=new Touchregion(path);
                regions[i].point_x=(int)((50+(i%3)*10) * onep_screen_w);
                regions[i].point_y=(int)(15 * onep_screen_h +((i/3)*10 +5)* onep_screen_w);
                regions[i].id=0;
        }
        //3个数字选择区域
        chooserectfs= new RectF[3];
        minrectfs=new RectF[3];
        for(int i=0;i<3;i++){
            chooserectfs[i]=new RectF((25*i+20)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (25*i+30)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);
            minrectfs[i]=new RectF((25*i+21)*onep_screen_w,85*onep_screen_h-4*onep_screen_w,
                    (25*i+29)*onep_screen_w,85*onep_screen_h+4*onep_screen_w);

        }
        chooseregions=new Touchregion[3];

        for(int i=0;i<3;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((25*i+25)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
        }
        chooseregions[0].typename="yellow";
        chooseregions[1].typename="orange";
        chooseregions[2].typename="light_blue";
        chooseregions[0].id=1;
        chooseregions[1].id=2;
        chooseregions[2].id=3;
        //3个第二选择区,左边选择区
        rectfs2= new RectF[3];

        for(int i=0;i<3;i++){
            rectfs2[i]=new RectF(25*onep_screen_w,10*i*onep_screen_w+15*onep_screen_h,
                    (35)*onep_screen_w,10*(i+1)*onep_screen_w+15*onep_screen_h);

        }
        regions2=new Touchregion[3];
        for(int i=0;i<3;i++){
            path = new Path();
            path.addRect(rectfs2[i], Path.Direction.CW);
            regions2[i]=new Touchregion(path);
        }
    }
    public void chooseeregion(int index){
        squresum=3;
        isshadow=false;
        for(int i=0;i<regions.length;i++){
            regions[i].istouch=false;
        }
        if(index==0){
            regions[0].id=2;
            regions[0].typename="orange";
            regions[0].isright=false;
            regions[1].id=3;
            regions[1].typename="light_blue";
            regions[1].isright=false;
            regions[2].id=1;
            regions[2].typename="yellow";
            regions[2].isright=true;
            regions[3].id=1;
            regions[3].typename="yellow";
            regions[3].isright=false;
            regions[4].id=2;
            regions[4].typename="orange";
            regions[4].isright=true;
            regions[5].id=3;
            regions[5].typename="light_blue";
            regions[5].isright=false;
            regions[6].id=3;
            regions[6].typename="light_blue";
            regions[6].isright=true;
            regions[7].id=1;
            regions[7].typename="yellow";
            regions[7].isright=false;
            regions[8].id=2;
            regions[8].typename="orange";
            regions[8].isright=false;
        }
        if(index==1){
            regions[0].id=3;
            regions[0].typename="light_blue";
            regions[0].isright=false;
            regions[1].id=1;
            regions[1].typename="yellow";
            regions[1].isright=true;
            regions[2].id=2;
            regions[2].typename="orange";
            regions[2].isright=false;
            regions[3].id=2;
            regions[3].typename="orange";
            regions[3].isright=true;
            regions[4].id=3;
            regions[4].typename="light_blue";
            regions[4].isright=false;
            regions[5].id=1;
            regions[5].typename="yellow";
            regions[5].isright=false;
            regions[6].id=1;
            regions[6].typename="yellow";
            regions[6].isright=false;
            regions[7].id=2;
            regions[7].typename="orange";
            regions[7].isright=false;
            regions[8].id=3;
            regions[8].typename="light_blue";
            regions[8].isright=true;
        }
        if(index==2){
            regions[0].id=1;
            regions[0].typename="yellow";
            regions[0].isright=false;
            regions[1].id=2;
            regions[1].typename="orange";
            regions[1].isright=false;
            regions[2].id=3;
            regions[2].typename="light_blue";
            regions[2].isright=false;
            regions[3].id=3;
            regions[3].typename="light_blue";
            regions[3].isright=true;
            regions[4].id=1;
            regions[4].typename="yellow";
            regions[4].isright=true;
            regions[5].id=2;
            regions[5].typename="orange";
            regions[5].isright=false;
            regions[6].id=2;
            regions[6].typename="orange";
            regions[6].isright=false;
            regions[7].id=3;
            regions[7].typename="light_blue";
            regions[7].isright=false;
            regions[8].id=1;
            regions[8].typename="yellow";
            regions[8].isright=true;
        }
    }
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page4image1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page4image1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page4image2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page4image2)).getBitmap() : null;
    Bitmap choose3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page4image3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page4image3)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //区域判定,选择区域判断
        if (isdown) {
            for (int i = 0; i < regions.length ; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown=false;
                    isshadow=false;
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    for(int j=0;j<regions.length;j++)
                        regions[j].istouch=false;
                    regions[i].istouch=true;
                    if (!regions[i].isright) {
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
            for(int i=0;i<regions2.length;i++){
                //计算控制点的边界
                regions2[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions2[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown=false;
                    regions2[i].istouch=true;
                    for(int j=0;j<regions2.length;j++){
                        if(j!=i)
                            regions2[j].istouch=false;
                    }
                    chooseeregion(i);
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
                            squresum++;
                            isright=true;
                            regions[curreindex].isright=true;
                            getright(chooseregions[i].point_x,chooseregions[i].point_y);
                            if(squresum==9&&DataUtil.isvoiceplay){
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                            }
                            if(squresum==9){
                                if(chooseregions[i].isright==false)
                                    pic_num--;
                                chooseregions[i].isright=true;
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

        //第二区域
        c.drawBitmap(choose1,null,rectfs2[0],paint);
        c.drawBitmap(choose2,null,rectfs2[1],paint);
        c.drawBitmap(choose3,null,rectfs2[2],paint);
        //第二区域选中阴影
        for (int i = 0; i < regions2.length; i++) {
            if (regions2[i].istouch) {
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs2[i], paint);
                paint.setAlpha(255);
            }
        }
        //下方阴影
        if (isshadow) {
            //没有开始
            if (!shadowflag) {
                Log.i("zh", "shadow start");
                shadowprogress = 0;
                shadowpaint.setAlpha(100);
                shadowanimator.start();
                shadowflag = true;
            }
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 25 * onep_screen_h), (int) (1.2 * Screen_w), Screen_h);
            c.drawRect(shadowrect, shadowpaint);
        }
        //判断区域
        for (int i = 0; i < regions.length; i++) {
            //黄底黑框
            paint.setStyle(Paint.Style.FILL);
            paint.setARGB(185, 240, 230, 140);
            c.drawRect(rectfs[i], paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(1);
            c.drawRect(rectfs[i], paint);
            paint.setStyle(Paint.Style.FILL);
            //色块和数字
            if (regions[i].isright) {
                if(regions[i].typename!=null) {
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].typename), null, rectfs[i], paint);
                    paint.setColor(getResources().getColor(R.color.colorblue));
                    paint.setTextSize(60 * fontrite);
                    c.drawText(regions[i].id + "", regions[i].point_x - 1.5f * onep_screen_w, regions[i].point_y + 1.5f * onep_screen_w, paint);
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
        //选择区域
        if(isshadow){
            for(int i=0;i<chooseregions.length;i++){
                c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].getTypename()), null, chooserectfs[i], paint);
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.FILL);
                c.drawRect(minrectfs[i], paint);
                paint.setColor(getResources().getColor(R.color.colorblue));
                paint.setTextSize(60 * fontrite);
                c.drawText("" + chooseregions[i].id, chooseregions[i].point_x - 1.5f * onep_screen_w,
                        chooseregions[i].point_y + 1.5f * onep_screen_w, paint);
            }
        }
        //勾叉
        if(isshadow) {
            if (isright) {
                c.drawPath(rightcheckpath, rightcheckpaint);
            } else {
                c.drawPath(checkpath, checkpaint);
            }
        }
    }
}
