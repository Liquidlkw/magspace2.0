package com.example.magspace.SymmertyPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

public class Sysmertypicgame2 extends SymmertypicgameFather  {
    public static Sysmertypicgame2 instance;

    public static  Sysmertypicgame2 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Sysmertypicgame2(context, display);
        }
        return instance;
    }

    public Sysmertypicgame2(Context context,Display display) {
        super(context);
        this.init(display);
    }

    public void init(Display display) {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(Sysmertypicgame2.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Sysmertypicgame2.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Sysmertypicgame2.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(1000);
        index=2;
        pic_num=maxpic_num=2;
        //三个判断区域
        regions=new Touchregion[3];
        rectfs= new RectF[3];
        path =new Path();
        path.moveTo(onep_screen_w*44,onep_screen_h*30);
        path.lineTo(onep_screen_w*38, onep_screen_h*50);
        path.lineTo(onep_screen_w*44, onep_screen_h*70);
        path.lineTo(onep_screen_w*57, onep_screen_h*70);
        path.lineTo(onep_screen_w*63, onep_screen_h*50);
        path.lineTo(onep_screen_w*57, onep_screen_h*30);
        path.close();
        regions[0]=new Touchregion(path);
        regions[0].setTypename("orange");
        rectfs[0]=new RectF((int)(38*onep_screen_w),(int)(30*onep_screen_h),(int)(63*onep_screen_w),(int)(70*onep_screen_h));
        path =new Path();
        path.moveTo(onep_screen_w*57,onep_screen_h*70);
        path.lineTo(onep_screen_w*63, onep_screen_h*50);
        path.lineTo(onep_screen_w*69, onep_screen_h*70);
        path.close();
        regions[1]=new Touchregion(path);
        regions[1].setTypename("bluered");
        rectfs[1]=new RectF((int)(57*onep_screen_w),(int)(30*onep_screen_h),(int)(69*onep_screen_w),(int)(70*onep_screen_h));
        path =new Path();
        path.moveTo(onep_screen_w*57,onep_screen_h*30);
        path.lineTo(onep_screen_w*63, onep_screen_h*50);
        path.lineTo(onep_screen_w*69, onep_screen_h*30);
        path.close();
        regions[2]=new Touchregion(path);
        regions[2].setTypename("bluered");
        rectfs[2]=new RectF((int)(57*onep_screen_w),(int)(30*onep_screen_h),(int)(69*onep_screen_w),(int)(70*onep_screen_h));
        //两个个图像选择区域
        chooserectfs= new RectF[2];
        for(int i=0;i<2;i++){
            chooserectfs[i]=new RectF((40*i+30)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (40*i+40)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[2];

        for(int i=0;i<2;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((40*i+35)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
        }
        chooseregions[0].setTypename("bluered");
        chooseregions[1].setTypename("orange");

    }
    Bitmap Back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page2img)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page2img)).getBitmap() : null;
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1imgchoose1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page2imgchoose1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page1imgchoose2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page2imgchoose2)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //画网格，画判定区域时使用，完成后去除
//        for(int i=0;i<100;i++){
//            for(int j=0;j<100;j++){
//                if(i==0&&j%10==0){
//                    paint.setColor(Color.BLUE);
//                    paint.setTextSize(25);
//                    c.drawText(""+j, 5, j*onep_screen_h, paint);
//                }
//                else if(j==0&&i%10==0){
//                    paint.setColor(Color.BLUE);
//                    paint.setTextSize(25);
//                    c.drawText(""+i, i*onep_screen_w, 20, paint);
//                }
//                if(i%10==0&&j%10==0){
//                    paint.setColor(Color.YELLOW) ;
//                    c.drawCircle(i*onep_screen_w,j*onep_screen_h,5,paint);
//                }
//                else{
//                    paint.setColor(Color.GREEN) ;
//                    c.drawCircle(i*onep_screen_w,j*onep_screen_h,2,paint);
//                }
//            }
//        }
        //区域判断
//        for(int i=0;i<regions.length;i++){
//            c.drawPath(regions[i].path,regions[i].paint);
//        }
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
                            if(curreindex==1){
                                regions[curreindex+1].isright=true;
                            }
                            else if(curreindex==2){
                                regions[curreindex-1].isright=true;
                            }
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
        c.drawBitmap(Back,null,new Rect((int)(10*onep_screen_w),(int)(30*onep_screen_h),(int)(90*onep_screen_w),(int)(70*onep_screen_h)),paint);
        for(int i=0;i<regions.length;i++){
            if(regions[i].isright){
                switch (i){
                    case 0: c.drawBitmap(choose2,null,rectfs[i],paint);break;
                    case 1: c.drawBitmap(choose1,null,rectfs[i],paint);break;
                }
            }
        }
        if(isshadow){
            c.drawBitmap(choose1,null,chooserectfs[0],paint);
            c.drawBitmap(choose2,null,chooserectfs[1],paint);
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
