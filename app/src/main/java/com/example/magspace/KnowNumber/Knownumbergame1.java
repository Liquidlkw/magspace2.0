package com.example.magspace.KnowNumber;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;
import android.view.Display;
import android.widget.Switch;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.FindPic.Findpicturegame1;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Knownumbergame1 extends KnownumbergameFather {
    public static Knownumbergame1 instance;

    public static  Knownumbergame1 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Knownumbergame1(context, display);
        }
        return instance;
    }

    public Knownumbergame1(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display)
    {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(Knownumbergame1.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Knownumbergame1.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Knownumbergame1.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=1;
        pic_num=maxpic_num=4;
        //十二个数字快
        rectfs= new RectF[12];
        minrectfs = new RectF[16];
        for(int i=0;i<2;i++){
            for(int j=0;j<6;j++){
                rectfs[i*6+j]=new RectF((12*(j%6)+14)*onep_screen_w,(20+i*3)*onep_screen_h+i*12*onep_screen_w,
                        (12*(j%6+1)+14)*onep_screen_w,(20+i*3)*onep_screen_h+(i+1)*12*onep_screen_w);
                minrectfs[i*6+j]=new RectF((12*(j%6)+15.5f)*onep_screen_w,(20+i*3)*onep_screen_h+i*12*onep_screen_w+1.5f*onep_screen_w,
                        (12*(j%6+1)+12.5f)*onep_screen_w,(20+i*3)*onep_screen_h+(i+1)*12*onep_screen_w-1.5f*onep_screen_w);
            }
        }
        //十二个数字排序区域
        regions=new Touchregion[12];

        for(int i=0;i<6;i++){
            path = new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path,9-i);
            regions[i].isright=true;
            regions[i].point_x=(int)((12*(i%6)+14)*onep_screen_w+6*onep_screen_w);
            regions[i].point_y=(int)(20*onep_screen_h+6*onep_screen_w);
        }
        for(int i=0;i<6;i++){
            path = new Path();
            path.addRect(rectfs[6+i], Path.Direction.CW);
            regions[6+i]=new Touchregion(path,11+i);
            regions[6+i].isright=true;
            regions[6+i].point_x=(int)((12*(i%6)+14)*onep_screen_w+6*onep_screen_w);
            regions[6+i].point_y=(int)(23*onep_screen_h+18*onep_screen_w);
        }

        regions[2].isright=regions[4].isright=regions[8].isright=regions[11].isright=false;

        regions[0].setTypename("red");
        regions[1].setTypename("orange");
        regions[2].setTypename("yellow");
        regions[3].setTypename("light_green");
        regions[4].setTypename("light_blue");
        regions[5].setTypename("blue");
        regions[6].setTypename("pink");
        regions[7].setTypename("green");
        regions[8].setTypename("blue");
        regions[9].setTypename("light_blue");
        regions[10].setTypename("light_green");
        regions[11].setTypename("orange");

        //四个数字选择区域
        chooserectfs= new RectF[4];
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*i+15)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                        (20*i+25)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);
                minrectfs[i+12]=new RectF((20*i+16)*onep_screen_w,85*onep_screen_h-4*onep_screen_w,
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

        //数字区域

        for(int i=0;i<12;i++){
            c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].getTypename()),null,rectfs[i],paint);
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
            c.drawRect(minrectfs[i],paint);
        }
        for(int i=0;i<6;i++){
            paint.setColor(getResources().getColor(R.color.colorblue));
            paint.setTextSize(60*fontrite);
            c.drawText(""+(9-i),regions[i].point_x-1.5f*onep_screen_w,regions[i].point_y+1.5f*onep_screen_w,paint);
        }
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit1),null,rectfs[6],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit2),null,rectfs[7],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit3),null,rectfs[8],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit4),null,rectfs[9],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit5),null,rectfs[10],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit6),null,rectfs[11],paint);
        for(int i=0;i<12;i++){
            if(!regions[i].isright){
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                c.drawRect(minrectfs[i],paint);
                c.drawBitmap(GameUtil.getCommonBitmapbyname("wenhao"),null,minrectfs[i],paint);
            }
        }
        //数字选择区域
        if(isshadow) {
            for (int i = 0; i < 4; i++) {
                chooseregions[i].typename = curretypename;
            }
            switch (curreindex) {
                case 2:
                    chooseregions[0].id = 5;
                    chooseregions[1].id = 7;
                    chooseregions[2].id = 6;
                    chooseregions[3].id = 8;
                    break;
                case 4:
                    chooseregions[0].id = 3;
                    chooseregions[1].id = 4;
                    chooseregions[2].id = 6;
                    chooseregions[3].id = 5;
                    break;
                case 8:
                    chooseregions[0].id = 13;
                    chooseregions[1].id = 11;
                    chooseregions[2].id = 14;
                    chooseregions[3].id = 12;
                    break;
                case 11:
                    chooseregions[0].id = 14;
                    chooseregions[1].id = 13;
                    chooseregions[2].id = 16;
                    chooseregions[3].id = 15;
                    break;
            }
            for (int i = 0; i < 4; i++) {
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
                c.drawRect(minrectfs[i + 12], paint);
                //c.drawRect(new RectF(regions[i].point_x-4.5f*onep_screen_w,regions[i].point_y-4.5f*onep_screen_w,regions[i].point_x+4.5f*onep_screen_w,regions[i].point_y+4.5f*onep_screen_w),paint);
            }

            for (int i = 0; i < 4; i++) {
                paint.setColor(getResources().getColor(R.color.colorblue));
                paint.setTextSize(60 * fontrite);
                if(chooseregions[i].id<10) {
                    c.drawText("" + chooseregions[i].id, chooseregions[i].point_x - 1.5f * onep_screen_w, chooseregions[i].point_y + 1.5f * onep_screen_w, paint);
                }
                else{
                    switch (chooseregions[i].id){
                        case 11: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit1),null,minrectfs[12+i],paint);break;
                        case 12: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit2),null,minrectfs[12+i],paint);break;
                        case 13: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit3),null,minrectfs[12+i],paint);break;
                        case 14: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit4),null,minrectfs[12+i],paint);break;
                        case 15: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit5),null,minrectfs[12+i],paint);break;
                        case 16: c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.digit6),null,minrectfs[12+i],paint);break;
                    }
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
