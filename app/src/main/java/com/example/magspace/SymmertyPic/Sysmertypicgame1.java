package com.example.magspace.SymmertyPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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

public class Sysmertypicgame1 extends SymmertypicgameFather {
    public static Sysmertypicgame1 instance;

    public static  Sysmertypicgame1 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Sysmertypicgame1(context, display);
        }
        return instance;
    }

    public Sysmertypicgame1(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display) {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(Sysmertypicgame1.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Sysmertypicgame1.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Sysmertypicgame1.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=1;
        pic_num=maxpic_num=2;
        //二个判断区域
        regions=new Touchregion[2];
        rectfs= new RectF[2];
        path =new Path();
        path.moveTo(onep_screen_w*50,onep_screen_h*40);
        path.lineTo(onep_screen_w*56, onep_screen_h*60);
        path.lineTo(onep_screen_w*69, onep_screen_h*60);
        path.lineTo(onep_screen_w*63, onep_screen_h*40);
        path.close();
        regions[0]=new Touchregion(path);
        regions[0].setTypename("red");
        rectfs[0]=new RectF((int)(51*onep_screen_w),(int)(40*onep_screen_h),(int)(68*onep_screen_w),(int)(60*onep_screen_h));
        path =new Path();
        path.moveTo(onep_screen_w*63,onep_screen_h*40);
        path.lineTo(onep_screen_w*69, onep_screen_h*60);
        path.lineTo(onep_screen_w*75, onep_screen_h*40);
        path.close();
        regions[1]=new Touchregion(path);
        regions[1].setTypename("green");
        rectfs[1]=new RectF((int)(64*onep_screen_w),(int)(40*onep_screen_h),(int)(74*onep_screen_w),(int)(60*onep_screen_h));
        //四个图像选择区域
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
            chooseregions[i].point_x=(int)(20*(i+1)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
        }
        chooseregions[0].setTypename("green");
        chooseregions[1].setTypename("yellow");
        chooseregions[2].setTypename("pink");
        chooseregions[3].setTypename("red");
    }
    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1img)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page1img)).getBitmap() : null;
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1imgchoose1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page1imgchoose1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1imgchoose2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page1imgchoose2)).getBitmap() : null;
    Bitmap choose3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1imgchoose3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page1imgchoose3)).getBitmap() : null;
    Bitmap choose4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1imgchoose4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page1imgchoose4)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //区域判断

        if(isdown&&pic_num!=0) {
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
//                        for(int j=0;j<regions.length;j++){
//                            regions[j].istouch=false;
//                        }
//                        regions[i].istouch=true;
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
                        curretypename=regions[i].typename;
//                        curreid=regions[i].id;
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
                        if(chooseregions[i].typename==curretypename&&!regions[curreindex].isright){
                            Log.i("zh", "right");
                            pic_num--;
                            isright=true;
                            regions[curreindex].isright=true;
                            getright(chooseregions[i].point_x,chooseregions[i].point_y);
                            if(pic_num==0&&DataUtil.isvoiceplay){
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                            }
                        }
                        else if(chooseregions[i].typename!=curretypename&&!regions[curreindex].isright){
                            Log.i("zh", "wrong");
                            isright=false;
                            getwrong(chooseregions[i].point_x,chooseregions[i].point_y);
                        }
                    }
                }
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
        //中间图形
        c.drawBitmap(Back,null,new Rect((int)(10*onep_screen_w),(int)(40*onep_screen_h),(int)(90*onep_screen_w),(int)(60*onep_screen_h)),paint);
        for(int i=0;i<regions.length;i++){
            if(regions[i].isright){
                switch (i){
                    case 0: c.drawBitmap(choose4,null,rectfs[i],paint);break;
                    case 1: c.drawBitmap(choose1,null,rectfs[i],paint);break;
                }
            }
        }
        if(isshadow){
                c.drawBitmap(choose1,null,chooserectfs[0],paint);
                c.drawBitmap(choose2,null,chooserectfs[1],paint);
                c.drawBitmap(choose3,null,chooserectfs[2],paint);
                c.drawBitmap(choose4,null,chooserectfs[3],paint);
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
