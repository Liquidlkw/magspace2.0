package com.example.magspace.KnowNumber;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Knownumbergame2 extends KnownumbergameFather {
    public static Knownumbergame2 instance;

    public static  Knownumbergame2 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Knownumbergame2(context, display);
        }
        return instance;
    }

    public Knownumbergame2(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display)
    {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(Knownumbergame2.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Knownumbergame2.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Knownumbergame2.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=2;
        pic_num=maxpic_num=3;
        //二十个数字排序区域
        rectfs= new RectF[20];
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                rectfs[i*5+j]=new RectF((8*(j%5)+10)*onep_screen_w,15*onep_screen_h+i*8*onep_screen_w,
                        (8*(j%5+1)+10)*onep_screen_w,15*onep_screen_h+(i+1)*8*onep_screen_w);;
            }
        }
        regions=new Touchregion[20];
        for(int i=0;i<20;i++){
            path = new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path);
            regions[i].isright=true;
        }
        regions[2].isright=regions[4].isright=regions[5].isright=regions[6].isright=regions[7].isright=regions[9].isright=
                regions[14].isright=regions[15].isright=regions[16].isright=regions[17].isright=regions[18].isright=regions[19].isright=false;

        regions[0].setTypename("red");
        regions[1].setTypename("red");
        regions[2].setTypename("orange");
        regions[3].setTypename("yellow");
        regions[4].setTypename("light_green");
        regions[5].setTypename("orange");
        regions[6].setTypename("orange");
        regions[7].setTypename("orange");
        regions[8].setTypename("yellow");
        regions[9].setTypename("light_green");
        regions[10].setTypename("yellow");
        regions[11].setTypename("yellow");
        regions[12].setTypename("yellow");
        regions[13].setTypename("yellow");
        regions[14].setTypename("light_green");
        regions[15].setTypename("light_green");
        regions[16].setTypename("light_green");
        regions[17].setTypename("light_green");
        regions[18].setTypename("light_green");
        regions[19].setTypename("light_green");
        //右边四个数字选择区
        rectfs2= new RectF[4];
        minrectfs = new RectF[8];

        for(int i=0;i<4;i++){
                rectfs2[i]=new RectF(70*onep_screen_w,15*onep_screen_h+i*8*onep_screen_w,
                        78*onep_screen_w,15*onep_screen_h+(i+1)*8*onep_screen_w);
                 minrectfs[i]=new RectF(71*onep_screen_w,15*onep_screen_h+i*8*onep_screen_w+onep_screen_w,
                         77*onep_screen_w,15*onep_screen_h+(i+1)*8*onep_screen_w-onep_screen_w);
        }
        regions2=new Touchregion[4];
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(rectfs2[i], Path.Direction.CW);
            regions2[i]=new Touchregion(path,(i+1)*2);
            regions2[i].isright=true;
            regions2[i].point_x=(int)(74*onep_screen_w);
            regions2[i].point_y=(int)(19*onep_screen_h+i*8*onep_screen_w);
        }
        regions2[1].isright=regions2[3].isright=false;
        regions2[0].setTypename("red");
        regions2[1].setTypename("orange");
        regions2[2].setTypename("yellow");
        regions2[3].setTypename("light_green");

        //四个数字选择区域
        chooserectfs= new RectF[4];
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*i+15)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (20*i+25)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);
            minrectfs[i+4]=new RectF((20*i+16)*onep_screen_w,85*onep_screen_h-4*onep_screen_w,
                    (20*i+24)*onep_screen_w,85*onep_screen_h+4*onep_screen_w);

        }
        chooseregions=new Touchregion[4];

        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)(20*(i+1)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
            chooseregions[i].setTypename("red");
        }

    }
    public void onDraw(Canvas c) {
        //区域判断
        if(isdown) {
            for (int i = 0; i <regions.length ; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //点击到正确区域
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown=false;
                    isshadow=false;
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    if (!regions[i].isright) {
                        for(int j=0;j<regions.length;j++){
                            regions[j].istouch=false;
                        }
                        for(int j=0;j<regions2.length;j++){
                            regions2[j].istouch=false;
                        }
                        regions[i].istouch=true;
                        regionindex=1;
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
                        curretypename=regions[i].typename;
                    } else {
                    }
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }
            }
            for (int i = 0; i <regions2.length ; i++) {
                //计算控制点的边界
                regions2[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions2[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //点击到正确区域
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown=false;
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    isshadow=false;
                    if (!regions2[i].isright) {
                        for(int j=0;j<regions.length;j++){
                            regions[j].istouch=false;
                        }
                        for(int j=0;j<regions2.length;j++){
                            regions2[j].istouch=false;
                        }
                        regions2[i].istouch=true;
                        regionindex=2;
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
                        curretypename=regions2[i].typename;
                        curreid=regions2[i].id;
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
                        if(regionindex==1) {
                            if (chooseregions[i].typename == curretypename && !regions[curreindex].isright) {
//                            pic_num--;

                                isright = true;
                                regions[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                                //全部完成
                                for(int j=0;j<regions.length;j++){
                                    if(!regions[j].isright)
                                        break;
                                    if(j==regions.length-1)
                                        pic_num--;
                                }
                                if (pic_num == 0 && DataUtil.isvoiceplay) {
                                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                }
                            } else if (chooseregions[i].typename != curretypename && !regions[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                        else{
                            if ( chooseregions[i].id == curreid&&!regions2[curreindex].isright) {
                            pic_num--;
                                isright = true;
                                regions2[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                                if (pic_num == 0 && DataUtil.isvoiceplay) {
                                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                }
                            } else if (chooseregions[i].id != curreid && !regions2[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                    }
                }
            }
        }
        //底部紫色区域
        paint.setARGB(255,230,230,250);
        paint.setStyle(Paint.Style.FILL);
        c.drawRect(new RectF(10*onep_screen_w,15*onep_screen_h,50*onep_screen_w,15*onep_screen_h+32*onep_screen_w),paint);
        //选中阴影
        for(int i=0;i<regions.length;i++){
            if(regions[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs[i],paint);
                paint.setAlpha(255);
            }
        }
        for(int i=0;i<regions2.length;i++){
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
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 25 * onep_screen_h), (int)(1.2*Screen_w), Screen_h);
            c.drawRect(shadowrect,shadowpaint);
        }

        //左边数字区域
        for(int i=0;i<regions.length;i++){
            if(regions[i].isright) {
                c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].getTypename()),null,rectfs[i],paint);
//                switch (regions[i].getTypename()) {
//                    case "red":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red), null, rectfs[i], paint);
//                        break;
//                    case "orange":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.orange), null, rectfs[i], paint);
//                        break;
//                    case "yellow":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.yellow), null, rectfs[i], paint);
//                        break;
//                    case "blue":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.blue), null, rectfs[i], paint);
//                        break;
//                    case "pink":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.pink), null, rectfs[i], paint);
//                        break;
//                    case "green":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.green), null, rectfs[i], paint);
//                        break;
//                    case "light_blue":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_blue), null, rectfs[i], paint);
//                        break;
//                    case "light_green":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_green), null, rectfs[i], paint);
//                        break;
//                    default:
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red), null, rectfs[i], paint);
//                }
            }
        }
        //右边数字判断区域
        paint.setColor(getResources().getColor(R.color.colorblue));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20*fontrite);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        c.drawText("算算每个颜色矩形的数量",65*onep_screen_w,13*onep_screen_h,paint);
        //框框
        for(int i=0;i<regions2.length;i++){
//            switch(regions2[i].getTypename()){
//                case "red" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red),null,rectfs2[i],paint);break;
//                case "orange" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.orange),null,rectfs2[i],paint);break;
//                case "yellow" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.yellow),null,rectfs2[i],paint);break;
//                case "blue" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.blue),null,rectfs2[i],paint);break;
//                case "pink" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.pink),null,rectfs2[i],paint);break;
//                case "green" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.green),null,rectfs2[i],paint);break;
//                case "light_blue" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_blue),null,rectfs2[i],paint);break;
//                case "light_green" :    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_green),null,rectfs2[i],paint);break;
//                default:  c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red),null,rectfs2[i],paint);
//            }
            c.drawBitmap(GameUtil.getCommonBitmapbyname(regions2[i].getTypename()),null,rectfs2[i],paint);
            //黄色底
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
            c.drawRect(minrectfs[i],paint);
        }
        //数字
        for(int i=0;i<regions2.length;i++){
            paint.setColor(getResources().getColor(R.color.colorblue));
            paint.setTextSize(40*fontrite);
            c.drawText(""+regions2[i].id,regions2[i].point_x-0.75f*onep_screen_w,regions2[i].point_y+2.5f*onep_screen_w,paint);
        }
        //问号
        for(int i=0;i<regions2.length;i++){
            if(!regions2[i].isright){
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                c.drawRect(minrectfs[i],paint);
                c.drawBitmap(GameUtil.getCommonBitmapbyname("wenhao"),null,minrectfs[i],paint);
            }
        }
        //数字选择区域
        if(isshadow) {
            if(regionindex==2)
            for (int i = 0; i < chooseregions.length; i++) {
                chooseregions[i].typename = curretypename;
            }
            else{
                chooseregions[0].typename="red";
                chooseregions[1].typename="orange";
                chooseregions[2].typename="yellow";
                chooseregions[3].typename="light_green";
            }
            if(regionindex==2) {
                switch (curreindex) {
                    case 1:
                        chooseregions[0].id = 2;
                        chooseregions[1].id = 3;
                        chooseregions[2].id = 4;
                        chooseregions[3].id = 5;
                        break;
                    case 3:
                        chooseregions[0].id = 6;
                        chooseregions[1].id = 7;
                        chooseregions[2].id = 8;
                        chooseregions[3].id = 9;
                        break;
                    default:
                        break;
                }
            }
            for (int i = 0; i < chooseregions.length; i++) {
//                switch (chooseregions[i].getTypename()) {
//                    case "red":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red), null, chooserectfs[i], paint);
//                        break;
//                    case "orange":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.orange), null, chooserectfs[i], paint);
//                        break;
//                    case "yellow":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.yellow), null, chooserectfs[i], paint);
//                        break;
//                    case "blue":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.blue), null, chooserectfs[i], paint);
//                        break;
//                    case "pink":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.pink), null, chooserectfs[i], paint);
//                        break;
//                    case "green":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.green), null, chooserectfs[i], paint);
//                        break;
//                    case "light_blue":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_blue), null, chooserectfs[i], paint);
//                        break;
//                    case "light_green":
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_green), null, chooserectfs[i], paint);
//                        break;
//                    default:
//                        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red), null, chooserectfs[i], paint);
//                }
                c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].getTypename()), null, chooserectfs[i], paint);
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.FILL);
                if(regionindex==2)
                c.drawRect(minrectfs[i + 4], paint);
                //c.drawRect(new RectF(regions[i].point_x-4.5f*onep_screen_w,regions[i].point_y-4.5f*onep_screen_w,regions[i].point_x+4.5f*onep_screen_w,regions[i].point_y+4.5f*onep_screen_w),paint);
            }
            if(regionindex==2) {
                for (int i = 0; i < chooseregions.length; i++) {
                    paint.setColor(getResources().getColor(R.color.colorblue));
                    paint.setTextSize(60 * fontrite);
                    c.drawText("" + chooseregions[i].id, chooseregions[i].point_x - 1.5f * onep_screen_w, chooseregions[i].point_y + 1.5f * onep_screen_w, paint);
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
