package com.example.magspace.CreatePic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class CreatePicgame2 extends CreatePicgameFather {
    public static CreatePicgame2 instance;
    public int res;
    public static  CreatePicgame2 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new CreatePicgame2(context, display);
        }
        return instance;
    }

    public CreatePicgame2(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        super.init(display);
        index=2;
        isneedchoose = true;
        isneedcut=true;
        getpicflag=new boolean[]{true,true,true,true};
        getpiccut=new boolean[]{false,false,false,false};
        timeani= new ObjectAnimator[4];
        shotpics=new Bitmap[4];
        checkanimator = ObjectAnimator.ofFloat(CreatePicgame2.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        regions=new Touchregion[6];
//        rects =new Rect[2][3];
        rects= new Rect[2];
        rects[0]= new Rect((int)(81*onep_screen_w),(int)(88*onep_screen_h),(int)(85*onep_screen_w),(int)(95*onep_screen_h));
        rects[1]=new Rect((int)(89*onep_screen_w),(int)(88*onep_screen_h),(int)(93*onep_screen_w),(int)(95*onep_screen_h));
        rectfs =new RectF[2][3];
        resultrect=new int[2][3];
        for(int i=0;i<2;i++) {
            for(int j=0;j<3;j++) {
//                rects[i][j]=new Rect((int)((40+j*10) * onep_screen_w), (int)(30 * onep_screen_h +i*10 * onep_screen_w),
//                        (int)((50+j*10)* onep_screen_w), (int)(30 * onep_screen_h +(i+1)* 10 * onep_screen_w));
                rectfs[i][j]=new RectF((50+j*10) * onep_screen_w, 30 * onep_screen_h +i*10 * onep_screen_w,
                        (60+j*10)* onep_screen_w, 30 * onep_screen_h +(i+1)* 10 * onep_screen_w);
             }
        }
        //六个判定区域
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                path = new Path();
                resultrect[i][j]=0;
                path.addRect(rectfs[i][j], Path.Direction.CW);
                regions[i*3+j]=new Touchregion(path,i,j);
            }
        }
        //第一个选择区域
        path = new Path();
        chooseregions= new Touchregion[2];
        path = new Path();
        path.addRect(80*onep_screen_w,87*onep_screen_h,86*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[0]=new Touchregion(path,"square",2);
        //第二个选择区域
        path = new Path();
        path.addRect(88*onep_screen_w,87*onep_screen_h,94*onep_screen_w,96*onep_screen_h, Path.Direction.CW);
        chooseregions[1]=new Touchregion(path,"square2",1);
        curretypename= chooseregions[0].typename;
        chooseregions[0].istouch=true;
    }
    //判断当前是否完成，否返回0，是返回正确类型id
    public int isright(){
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                if(resultrect[i][j]==0)
                        return 0;
            }
        }
        if(resultrect[0][0]==1&&resultrect[0][1]==2&&resultrect[0][2]==2){
            return 1;
        }
        else if(resultrect[0][0]==1&&resultrect[0][1]==1&&resultrect[0][2]==2){
            return 2;
        }
        else if(resultrect[0][0]==2&&resultrect[0][1]==1&&resultrect[0][2]==1){
            return 3;
        }
        else  if(resultrect[0][0]==2&&resultrect[0][1]==2&&resultrect[0][2]==1){
            return 4;
        }
        else
            return 0;
    }
    public void onDraw(Canvas c) {
        //六个矩形
        for(int i=0;i<2;i++) {
            for(int j=0;j<3;j++) {
                paint.setStyle(Paint.Style.FILL);
                paint.setARGB(185, 211, 210, 255);
                c.drawRect(rectfs[i][j], paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(1);
                c.drawRect(rectfs[i][j], paint);
            }
        }
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
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square),null,rects[0],paint);
        c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.square2),null,rects[1],paint);
        //截屏展示区
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20*fontrite);
        paint.setColor(this.getResources().getColor(R.color.colororange));
        c.drawCircle(19*onep_screen_w,34*onep_screen_h,0.5f*onep_screen_w,paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        c.drawRect(30*onep_screen_w,35*onep_screen_h-3*onep_screen_w,36*onep_screen_w,35*onep_screen_h+3*onep_screen_w,paint);
        c.drawRect(30*onep_screen_w,50*onep_screen_h-3*onep_screen_w,36*onep_screen_w,50*onep_screen_h+3*onep_screen_w,paint);
        c.drawRect(37*onep_screen_w,35*onep_screen_h-3*onep_screen_w,43*onep_screen_w,35*onep_screen_h+3*onep_screen_w,paint);;
        c.drawRect(37*onep_screen_w,50*onep_screen_h-3*onep_screen_w,43*onep_screen_w,50*onep_screen_h+3*onep_screen_w,paint);
        paint.setColor(this.getResources().getColor(R.color.colororange));
        paint.setStyle(Paint.Style.FILL);
        c.drawText("2个部分",20*onep_screen_w,35*onep_screen_h,paint);
        //是否点击在正确区域内
        isright = false;
        //区域判定,选择区域判断
        if (isdown) {
            for (int i = 0; i < regions.length ; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                            isright = true;
                            isdown = false;
                            //没有方块
                            if(resultrect[regions[i].rown][regions[i].coln]==0){
                                if(curretypename=="square") {
                                    resultrect[regions[i].rown][regions[i].coln] = 1;
                                }
                                else if(curretypename=="square2") {
                                    for(int k=0;k<2;k++){
                                        resultrect[k][regions[i].coln] = 2;
                                    }
                                }
                            }
                            //红方块
                            else if(resultrect[regions[i].rown][regions[i].coln]==1){
                                if(curretypename=="square") {
                                    resultrect[regions[i].rown][regions[i].coln] = 0;
                                }
                                else if(curretypename=="square2") {

                                }
                            }
                            //绿方块
                            else if(resultrect[regions[i].rown][regions[i].coln]==2){
                                if(curretypename=="square") {

                                }
                                else if(curretypename=="square2") {
                                        for(int k=0;k<2;k++){
                                            resultrect[k][regions[i].coln] = 0;
                                        }
                                }
                            }
                }

            }
            if (isneedchoose) {
                for (int i = 0; i < chooseregions.length; i++) {
                    //计算控制点的边界
                    chooseregions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        isright = true;
                        isdown = false;
                        chooseregions[i].istouch = true;
                        curretypename = chooseregions[i].typename;

                        //选中后其他的就不被选中
                        for (int j = 0; j < chooseregions.length; j++) {
                            if (i != j) {
                                chooseregions[j].istouch = false;
                            }
                        }
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        break;
                    }
                }
            }
        }
        //判定区域
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                if(resultrect[i][j]==1){
                    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.red),null,rectfs[i][j],paint);
                }
                else if(resultrect[i][j]==2)
                    c.drawBitmap(GameUtil.getCommonBitmap(R.drawable.light_green),null,rectfs[i][j],paint);
            }
        }
        //选择区域
        if (isneedchoose) {
            for (int i = 0; i < chooseregions.length; i++) {
                if (chooseregions[i].istouch) {
                    c.drawPath(chooseregions[i].path, chooseregions[i].paint);
                }
            }
        }

        //打叉动画
        //如果进入判断区域，打叉动画消失
        if (isright) {
            checkpaint.setAlpha(0);
        }
        //触摸到界面并且还没开始画叉
        if (isdown) {
            if (!ischeck) {
                checkflag = true;
                ischeck = true;
                isdown = false;
                checkpaint.setAlpha(255);
                checkprogress = 0;
                checkpath = new Path();
                checkpath.addCircle(Point_x, Point_y, 2 * onep_screen_w, Path.Direction.CW);
                checkpath.moveTo(Point_x - 1 * onep_screen_w, Point_y - 2 * onep_screen_h);
                checkpath.lineTo(Point_x + 1 * onep_screen_w, Point_y + 2 * onep_screen_h);
                checkpath.moveTo(Point_x + 1 * onep_screen_w, Point_y - 2 * onep_screen_h);
                checkpath.lineTo(Point_x - 1 * onep_screen_w, Point_y + 2 * onep_screen_h);
                PathMeasure measure = new PathMeasure(checkpath, false);
                checklength = measure.getLength();
                //打叉音效
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                checkanimator.start();
            }
            //触摸到界面并且已经在画叉,重新画叉
            else if (ischeck) {
                isdown = false;
                checkprogress = 0;
                checkpaint.setAlpha(255);
                checkpath = new Path();
                checkpath.addCircle(Point_x, Point_y, 2 * onep_screen_w, Path.Direction.CW);
                checkpath.moveTo(Point_x - 1 * onep_screen_w, Point_y - 2 * onep_screen_h);
                checkpath.lineTo(Point_x + 1 * onep_screen_w, Point_y + 2 * onep_screen_h);
                checkpath.moveTo(Point_x + 1 * onep_screen_w, Point_y - 2 * onep_screen_h);
                checkpath.lineTo(Point_x - 1 * onep_screen_w, Point_y + 2 * onep_screen_h);
                PathMeasure measure = new PathMeasure(checkpath, false);
                checklength = measure.getLength();
                //打叉音效
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                checkanimator.start();
            }
        }
        //叉画完,消失
        if (ischeck && checkprogress >= 1f) {
            ischeck = false;
            checkpaint.setAlpha(0);
            invalidate();
            isdown = false;
        }
        c.drawPath(checkpath, checkpaint);
        //判断是否达到要求
        res=isright();
        if(res==1&& getpicflag[0]){
            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
            getpicflag[0] = false;
            getShotPic(0);
            this.destroyDrawingCache();
        }
        else if(res==2&& getpicflag[1]){
            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
            getpicflag[1] = false;
            getShotPic(1);
            this.destroyDrawingCache();
        }
        else if(res==3&& getpicflag[2]){
            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
            getpicflag[2] = false;
            getShotPic(2);
            this.destroyDrawingCache();
        }
        else if(res==4&& getpicflag[3]){
            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
            getpicflag[3] = false;
            getShotPic(3);
            this.destroyDrawingCache();
        }
        //画截图
        if(getpicflag[0]==false&&shotpics[0]!=null){
            if(getpiccut[0]==false) {
                shotpics[0] = Bitmap.createBitmap(shotpics[0], (int) (50 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
                getpiccut[0]=true;
            }
            c.drawBitmap(shotpics[0], null, new Rect((int) (30.5*onep_screen_w), (int) (35*onep_screen_h-2.5*onep_screen_w),
                    (int) (35.5*onep_screen_w), (int) (35*onep_screen_h+2.5*onep_screen_w)), paint);
        }
        if(getpicflag[1]==false&&shotpics[1]!=null){
            if(getpiccut[1]==false) {
                shotpics[1] = Bitmap.createBitmap(shotpics[1], (int) (50 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
                getpiccut[1]=true;
            }
            c.drawBitmap(shotpics[1], null, new Rect((int) (37.5*onep_screen_w), (int) (35*onep_screen_h-2.5*onep_screen_w),
                    (int) (42.5*onep_screen_w), (int) (35*onep_screen_h+2.5*onep_screen_w)), paint);
        }
        if(getpicflag[2]==false&&shotpics[2]!=null){
            if(getpiccut[2]==false) {
                shotpics[2] = Bitmap.createBitmap(shotpics[2], (int) (50 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
                getpiccut[2]=true;
            }
            c.drawBitmap(shotpics[2], null, new Rect((int) (30.5*onep_screen_w), (int) (50*onep_screen_h-2.5*onep_screen_w),
                    (int) (35.5*onep_screen_w), (int) (50*onep_screen_h+2.5*onep_screen_w)), paint);
        }
        if(getpicflag[3]==false&&shotpics[3]!=null){
            if(getpiccut[3]==false) {
                shotpics[3] = Bitmap.createBitmap(shotpics[3], (int) (50 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
                getpiccut[3]=true;
            }
            c.drawBitmap(shotpics[3], null, new Rect((int) (37.5*onep_screen_w), (int) (50*onep_screen_h-2.5*onep_screen_w),
                    (int) (42.5*onep_screen_w), (int) (50*onep_screen_h+2.5*onep_screen_w)), paint);
        }
    }

}
