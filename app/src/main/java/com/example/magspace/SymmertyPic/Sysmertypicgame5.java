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

public class Sysmertypicgame5 extends SymmertypicgameFather {
    public static Sysmertypicgame5 instance;

    public static  Sysmertypicgame5 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Sysmertypicgame5(context, display);
        }
        return instance;
    }

    public Sysmertypicgame5(Context context,Display display) {
        super(context);
        this.init(display);
        index=5;
        pic_num=maxpic_num=2;
        getpicflag=new boolean[]{true,true};
        getpiccut=new boolean[]{false,false};
        shotpics=new Bitmap[2];
        rightcheckanimator = ObjectAnimator.ofFloat(Sysmertypicgame5.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Sysmertypicgame5.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Sysmertypicgame5.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        //四个判断区域
        regions=new Touchregion[16];
        rectfs= new RectF[16];
        for(int i=0;i<16;i++){
            rectfs[i]=new RectF((50+(i%4)*6)* onep_screen_w, 20 * onep_screen_h +(i/4)*6 * onep_screen_w,
                    (50+(i%4+1)*6) * onep_screen_w, 20 * onep_screen_h +(i/4+1)*6 * onep_screen_w);
        }
        for(int i=0;i<16;i++){
            path = new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path);
        }
        //4个数字选择区域
        chooserectfs= new RectF[4];
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*i+15)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (20*i+25)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[4];

        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((20*i+20)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
        }
        chooseregions[0].typename="blue";
        chooseregions[1].typename="light_blue";
        chooseregions[2].typename="orange";
        chooseregions[3].typename="red";
        //2个第二区域
        regions2=new Touchregion[2];
        rectfs2= new RectF[2];
        for(int i=0;i<2;i++){
            rectfs2[i]=new RectF(32 * onep_screen_w, 20 * onep_screen_h +i*13 * onep_screen_w,
                    44*onep_screen_w, 20 * onep_screen_h +(i*13+12) * onep_screen_w);

        }
        for(int i=0;i<2;i++){
            path = new Path();
            path.addRect(rectfs2[i], Path.Direction.CW);
            regions2[i]=new Touchregion(path);
            regions2[i].isright=false;
        }
        regions2[0].istouch=true;
        getrightpic(0);
    }
    public void getrightpic(int index){
        for(int i=0;i<regions.length;i++){
            regions[i].istouch=false;
            regions[i].isright=false;
            regions[i].typename=null;
        }
        isshadow=false;
        checkpaint.setAlpha(0);
        rightcheckpaint.setAlpha(0);
        if(index==0){
            regionindex=0;
            squresum=0;
            regions[6].typename=regions[9].typename=regions[12].typename="blue";
            regions[7].typename=regions[10].typename=regions[13].typename="light_blue";
        }
        if(index==1){
            squresum=0;
            regionindex=1;
            regions[2].typename=regions[5].typename=regions[10].typename="orange";
            regions[6].typename=regions[11].typename="red";
        }
    }
    public void init(Display display) {
        super.init(display);
    }
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page5number1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page5number1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page5number2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page5number2)).getBitmap() : null;

    public void onDraw(Canvas c) {
        //区域判定,选择区域判断

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
                            if ((squresum==6&&regionindex==0)||(squresum == 5&&regionindex==1)&& DataUtil.isvoiceplay) {
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                            }
                            if((squresum==6&&regionindex==0)||(squresum == 5&&regionindex==1)){
                                if(regions2[regionindex].isright==false) {
                                    if (getpicflag[regionindex]) {
                                        DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                        getpicflag[regionindex] = false;
                                        getShotPic(regionindex);
                                        this.destroyDrawingCache();
                                    }
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

        //色块和白灰底黑框
        for(int i=0;i<regions.length;i++){
            paint.setStyle(Paint.Style.FILL);
            if((i/4+i%4)%2==1)
                paint.setColor(Color.WHITE);
            else{
                paint.setAlpha(30);
                paint.setColor(Color.GRAY);
            }
            c.drawRect(rectfs[i], paint);
            paint.setAlpha(255);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(2);
            c.drawRect(rectfs[i], paint);
            if(regions[i].isright&&regions[i].typename!=null)
            c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].typename),null,rectfs[i],paint);
        }
        //左侧选择图形
        c.drawBitmap(c1,null,new RectF(20*onep_screen_w,20*onep_screen_h+2*onep_screen_w,30*onep_screen_w,
                20*onep_screen_h+10*onep_screen_w),paint);
        c.drawBitmap(c2,null,new RectF(20*onep_screen_w,20*onep_screen_h+15*onep_screen_w,30*onep_screen_w,
                20*onep_screen_h+23*onep_screen_w),paint);
        //截图区域
        for(int i=0;i<regions2.length;i++){
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(2);
            c.drawRect(rectfs2[i],paint);
            if(regions2[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs2[i],paint);
                paint.setAlpha(255);
            }
            if(regions2[i].isright){
                if(getpicflag[i]==false&&shotpics[i]!=null){
                    if(getpiccut[i]==false) {
                        shotpics[i] = Bitmap.createBitmap(shotpics[i], (int) (50 * onep_screen_w), (int) (20 * onep_screen_h), (int) (24 * onep_screen_w), (int) (24 * onep_screen_w));
                        getpiccut[i]=true;
                    }
                    c.drawBitmap(shotpics[i], null, rectfs2[i], paint);
                }
            }
            else {
                c.drawBitmap(GameUtil.getCommonBitmapbyname("wenhao"),null,rectfs2[i],paint);
            }
        }
        //选择阴影
        for(int i=0;i<regions.length;i++){
            if(regions[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs[i],paint);
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
        //下方选项
        if(isshadow){
           for(int i=0;i<chooseregions.length;i++){
               c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].typename),null,chooserectfs[i],paint);
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
