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
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Knownumbergame3 extends KnownumbergameFather {
    public static Knownumbergame3 instance;
    public  String[] nametypes;
    public static  Knownumbergame3 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Knownumbergame3(context, display);
        }
        return instance;
    }

    public Knownumbergame3(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display) {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(Knownumbergame3.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Knownumbergame3.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Knownumbergame3.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=3;
        pic_num=maxpic_num=9;
        //9块阴影和36个小方块
        rectfs= new RectF[9];
        minrectfs = new RectF[36];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                rectfs[i*3+j]=new RectF((25*(j%3)+12.5f)*onep_screen_w,10*onep_screen_h+i*14*onep_screen_w,
                        (25*(j%3+1)+12.5f)*onep_screen_w,10*onep_screen_h+(i+1)*14*onep_screen_w);
                //左1
                minrectfs[i*3+j]=new RectF((25*(j%3)+15)*onep_screen_w,12*onep_screen_h+i*14*onep_screen_w+onep_screen_w,
                        (25*(j%3)+19)*onep_screen_w,12*onep_screen_h+i*14*onep_screen_w+5*onep_screen_w);
                //左2
                minrectfs[i*3+j+9]=new RectF((25*(j%3)+15)*onep_screen_w,13*onep_screen_h+i*14*onep_screen_w+7*onep_screen_w,
                        (25*(j%3)+19)*onep_screen_w,13*onep_screen_h+i*14*onep_screen_w+11*onep_screen_w);
                //右1
                minrectfs[i*3+j+18]=new RectF((25*(j%3)+25)*onep_screen_w,12*onep_screen_h+i*14*onep_screen_w,
                        (25*(j%3)+31)*onep_screen_w,12*onep_screen_h+i*14*onep_screen_w+6*onep_screen_w);
                //右2
                minrectfs[i*3+j+27]=new RectF((25*(j%3)+25)*onep_screen_w,13*onep_screen_h+i*14*onep_screen_w+6*onep_screen_w,
                        (25*(j%3)+31)*onep_screen_w,13*onep_screen_h+i*14*onep_screen_w+12*onep_screen_w);
            }
        }
        nametypes= new String[18];
        nametypes[0]=nametypes[5]=nametypes[7]="tangle";
        nametypes[1]=nametypes[2]=nametypes[10]="square";
        nametypes[3]="trapezoid";
        nametypes[4]="prismatic";
        nametypes[6]="tangle3";
        nametypes[8]=nametypes[15]=nametypes[16]=nametypes[17]="tangle_big";
        nametypes[9]="square_big";
        nametypes[11]="square2";
        nametypes[12]=nametypes[13]=nametypes[14]="hexagon";
        regions=new Touchregion[9];

        for(int i=0;i<9;i++){
            path = new Path();
        path.addRect(rectfs[i], Path.Direction.CW);
        regions[i]=new Touchregion(path);
        regions[i].point_x=(int)((25*(i%3+1))*onep_screen_w);
        regions[i].point_y=(int)(10*onep_screen_h+7*onep_screen_w+14*(i/3)*onep_screen_w);

    }
        regions[0].id=regions[7].id=4;
        regions[1].id= regions[8].id=1;
        regions[2].id= regions[3].id= regions[6].id=2;
        regions[4].id=3;
        regions[5].id=6;
        //5个数字选择区
        chooserectfs= new RectF[5];
        for(int i=0;i<5;i++){
            chooserectfs[i]=new RectF((16*i+10)*onep_screen_w,87*onep_screen_h,
                    (16*i+26)*onep_screen_w,97*onep_screen_h);


        }
        chooseregions=new Touchregion[5];
        for(int i=0;i<5;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((16*i+18)*onep_screen_w);
            chooseregions[i].point_y=(int)(92*onep_screen_h);
            if(i!=4)
            chooseregions[i].id=i+1;
            else
                chooseregions[i].id=i+2;
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
                        regions[i].istouch=true;
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
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
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 15 * onep_screen_h), (int)(1.2*Screen_w), Screen_h);
            c.drawRect(shadowrect,shadowpaint);
        }

        paint.setAntiAlias(true);
        paint.setTextSize(20*fontrite);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        //横线与等于号
        for(int i=0;i<9;i++){
            c.drawLine(regions[i].point_x-11*onep_screen_w,regions[i].point_y+0.8f*onep_screen_h,
                    regions[i].point_x-5*onep_screen_w,regions[i].point_y+0.8f*onep_screen_h,paint);
            c.drawLine(regions[i].point_x-0.5f*onep_screen_w,regions[i].point_y+0.8f*onep_screen_h,
                    regions[i].point_x+6.5f*onep_screen_w,regions[i].point_y+0.8f*onep_screen_h,paint);
            paint.setStrokeWidth(1);
            c.drawText("=",regions[i].point_x-2.5f*onep_screen_w,regions[i].point_y+1.8f*onep_screen_h,paint);
            paint.setStrokeWidth(2);
        }
        //18个方块
        for(int i=18;i<36;i++){
            c.drawRect(minrectfs[i],paint);
        }
        //结果
        for(int i=0;i<9;i++){
            if(regions[i].isright) {
                c.drawText("1", (25 * (i % 3) + 27.5f) * onep_screen_w, 12 * onep_screen_h + (i / 3) * 14 * onep_screen_w + 4 * onep_screen_w, paint);
                c.drawText(regions[i].id + "", (25 * (i % 3) + 27.5f) * onep_screen_w, 12 * onep_screen_h + (i / 3) * 14 * onep_screen_w + 10 * onep_screen_w, paint);
            }
        }

        //18图形
        for(int i=0;i<18;i++){
            c.drawBitmap(GameUtil.getCommonBitmapbyname(nametypes[i]),null,minrectfs[i],paint);
        }
        //选择数字
        for(int i=0;i<5;i++){
            if(isshadow) {
                paint.setColor(this.getResources().getColor(R.color.colororange));
                c.drawLine(chooseregions[i].point_x - 2 * onep_screen_w, chooseregions[i].point_y,
                        chooseregions[i].point_x + 2 * onep_screen_w, chooseregions[i].point_y, paint);

                c.drawText("1", chooseregions[i].point_x - 0.5f * onep_screen_w, chooseregions[i].point_y - 1 * onep_screen_w, paint);
                if(i!=4)
                     c.drawText(i + 1 + "", chooseregions[i].point_x - 0.5f * onep_screen_w, chooseregions[i].point_y + 2 * onep_screen_w, paint);
                else
                    c.drawText(i + 2 + "", chooseregions[i].point_x - 0.5f * onep_screen_w, chooseregions[i].point_y + 2 * onep_screen_w, paint);
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
