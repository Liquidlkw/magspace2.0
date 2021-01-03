package com.example.magspace.Levelfirst;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.LevelSecond.SimilarPic6choosepic;
import com.example.magspace.Levelfirst.Viewpoint1chooselid;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Find4choosenum extends View{
    Path path,anopath;
    Paint paint;
    public Touchregion regions[],chooseregions[];
    public int Screen_w;
    public  int Screen_h;
    public int Viewwith;
    public int Viewheight;
    public int startx;
    public int starty;
    public  float onep_screen_w;
    public  float onep_screen_h;
    public  float fontrite;
    //判断是否在区域内的方法
    Region re;
    //构造一个区域对象，左闭右开的。
    RectF r;
    //是否需要判定选择图形类型
    public RectF rects[],chooserectfs[];
    //当前选中的图形类型
    String curretypename;
    int curreindex,curreid;
    boolean isright;
    //触摸检测数据
    public  static int Point_x;
    public static int Point_y;
    public static boolean ismove;
    public  static boolean isdown;
    //打叉图形所需组件
    Path checkpath;
    Paint checkpaint;
    float  checklength;
    ObjectAnimator checkanimator;
    boolean ischeck;
    boolean checkflag;
    float checkprogress;
    //打勾图形所需组件
    Path rightcheckpath;
    Paint rightcheckpaint;
    float  rightchecklength;
    ObjectAnimator rightcheckanimator;
    boolean isrightcheck;
    boolean rightcheckflag;
    float rightcheckprogress;

    //阴影所需组件
//    Path shadowpath;
    Rect shadowrect;
    Paint shadowpaint;
    float  shadowlength;
    ObjectAnimator shadowanimator;
    boolean isshadow;
    boolean shadowflag;
    float shadowprogress;

    public static Find4choosenum instance;
    public static  Find4choosenum getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Find4choosenum(context, display);
        }
        return instance;
    }
    public Find4choosenum(Context context) {
        super(context);
    }
    public Find4choosenum(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        ischeck=false;
        checkpaint=new Paint();
        checkpaint.setColor(Color.RED);
        checkpaint.setStyle(Paint.Style.STROKE);
        checkpaint.setStrokeWidth(4);
        checkpaint.setAlpha(0);
        checkpath=new Path();

        isrightcheck=false;
        rightcheckpaint=new Paint();
        rightcheckpaint.setColor(Color.GREEN);
        rightcheckpaint.setStyle(Paint.Style.STROKE);
        rightcheckpaint.setStrokeWidth(4);
        rightcheckpaint.setAlpha(0);
        rightcheckpath=new Path();

        isshadow=false;
        shadowpaint=new Paint();
        shadowpaint.setColor(Color.GRAY);
        shadowpaint.setStyle(Paint.Style.FILL);

        rightcheckanimator = ObjectAnimator.ofFloat(Find4choosenum.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Find4choosenum.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Find4choosenum.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        paint =new Paint();
        paint.setAntiAlias(true);
//        this.setBackgroundColor(Color.WHITE);
        Screen_w = display.getWidth();
        Screen_h = display.getHeight();
        if(Screen_h<Screen_w){
            int x=Screen_w;
            Screen_w=Screen_h;
            Screen_h=x;
        }
        onep_screen_w=Screen_w*1.0f/100;
        onep_screen_h=Screen_h*1.0f/100;
        fontrite=Screen_w*1.0f/1280;

        re = new Region();
        r = new RectF();
        //24个判断区域
        rects= new RectF[24];
        regions= new Touchregion[24];
        for(int i=0;i<8;i++){
            rects[i]=new RectF((4+10*i)*onep_screen_w,5*onep_screen_h,
                    (14+10*i)*onep_screen_w,5*onep_screen_h+10*onep_screen_w);
        }
        for(int i=0;i<8;i++){
            path = new Path();
            path.addRect(rects[i], Path.Direction.CW);
            regions[i] = new Touchregion(path);
            regions[i].point_x=(int)((9+10*i)*onep_screen_w);
            regions[i].point_y=(int)(5*onep_screen_h+5*onep_screen_w);
            regions[i].typename=(i%2==0)?"blue":"red";
            regions[i].id=0;
        }
        for(int i=0;i<8;i++){
            rects[i+8]=new RectF((4+10*i)*onep_screen_w,20*onep_screen_h,
                    (14+10*i)*onep_screen_w,20*onep_screen_h+10*onep_screen_w);
        }
        for(int i=0;i<8;i++){
            path = new Path();
            path.addRect(rects[i+8], Path.Direction.CW);
            regions[i+8] = new Touchregion(path);
            regions[i+8].point_x=(int)((9+10*i)*onep_screen_w);
            regions[i+8].point_y=(int)(20*onep_screen_h+5*onep_screen_w);
            regions[i+8].typename=(i%2==0)?"blue":"red";
            regions[i+8].id=i+1;
        }
        for(int i=0;i<8;i++){
            rects[i+16]=new RectF((4+10*i)*onep_screen_w,35*onep_screen_h,
                    (14+10*i)*onep_screen_w,35*onep_screen_h+10*onep_screen_w);
        }
        for(int i=0;i<8;i++){
            path = new Path();
            path.addRect(rects[i+16], Path.Direction.CW);
            regions[i+16] = new Touchregion(path);
            regions[i+16].point_x=(int)((9+10*i)*onep_screen_w);
            regions[i+16].point_y=(int)(35*onep_screen_h+5*onep_screen_w);
            regions[i+16].typename=(i%2==0)?"green":"blue";
            if(i%3==0){
                regions[i+16].id=9;
            }
            if(i%3==1){
                regions[i+16].id=7;
            }
            if(i%3==2){
                regions[i+16].id=5;
            }
        }
        for(int i=0;i<24;i++){
            regions[i].isright=true;
        }
        regions[6].isright=false;
        regions[11].isright=false;
        regions[22].isright=false;

        //10个数字选择区域
        chooserectfs= new RectF[10];
        chooseregions=new Touchregion[10];
        for(int i=0;i<2;i++){
            chooserectfs[i]=new RectF((40*i+20)*onep_screen_w,60*onep_screen_h-5*onep_screen_w,
                    (40*i+30)*onep_screen_w,60*onep_screen_h+5*onep_screen_w);

        }
        for(int i=0;i<2;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((40*i+25)*onep_screen_w);
            chooseregions[i].point_y=(int)(60*onep_screen_h);
            chooseregions[i].id=0;
        }
        chooseregions[0].typename="red";
        chooseregions[1].typename="blue";

        for(int i=0;i<4;i++){
            chooserectfs[i+2]=new RectF((20*i+10)*onep_screen_w,60*onep_screen_h-5*onep_screen_w,
                    (20*i+20)*onep_screen_w,60*onep_screen_h+5*onep_screen_w);

        }
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i+2], Path.Direction.CW);
            chooseregions[i+2]=new Touchregion(path);
            chooseregions[i+2].point_x=(int)((20*i+15)*onep_screen_w);
            chooseregions[i+2].point_y=(int)(60*onep_screen_h);
            chooseregions[i+2].typename=(i%2==0)?"blue":"red";
        }
        chooseregions[2].id=4;
        chooseregions[3].id=3;
        chooseregions[4].id=5;
        chooseregions[5].id=4;
        for(int i=0;i<4;i++){
            chooserectfs[i+6]=new RectF((20*i+10)*onep_screen_w,60*onep_screen_h-5*onep_screen_w,
                    (20*i+20)*onep_screen_w,60*onep_screen_h+5*onep_screen_w);

        }
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i+6], Path.Direction.CW);
            chooseregions[i+6]=new Touchregion(path);
            chooseregions[i+6].point_x=(int)((20*i+15)*onep_screen_w);
            chooseregions[i+6].point_y=(int)(60*onep_screen_h);
            chooseregions[i+6].typename=(i%2==0)?"green":"blue";
        }
        chooseregions[6].id=7;
        chooseregions[7].id=9;
        chooseregions[8].id=9;
        chooseregions[9].id=8;
    }
    public void setRightcheckprogress(float rightcheckprogress){
        this.rightcheckprogress=rightcheckprogress;
        rightcheckpaint.setPathEffect(createPathEffect(rightchecklength, rightcheckprogress, 0.0f));
        invalidate();
    }
    public void setCheckprogress(float checkprogress){
        this.checkprogress=checkprogress;
        checkpaint.setPathEffect(createPathEffect(checklength, checkprogress, 0.0f));
        invalidate();
    }
    public void setShadowprogress(float shadowprogress){
        this.shadowprogress=shadowprogress;
//       shadowpaint.setPathEffect(createPathEffect(shadowlength, shadowprogress, 0.0f));
        invalidate();
    }
    public static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                pathLength - phase * pathLength);
    }
    //打勾
    public void getright( int p_x, int p_y){
        rightcheckpaint.setAlpha(255);
        rightcheckprogress = 0;
        rightcheckpath = new Path();
        rightcheckpath.addCircle(p_x, p_y, 2 * onep_screen_h, Path.Direction.CW);
        rightcheckpath.moveTo(p_x - 0.5f * onep_screen_h, p_y);
        rightcheckpath.lineTo(p_x , p_y + 1* onep_screen_w);
        rightcheckpath.lineTo(p_x + 1 * onep_screen_h, p_y - 1 * onep_screen_w);;
        PathMeasure measure = new PathMeasure(rightcheckpath, false);
        rightchecklength = measure.getLength();
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
        rightcheckanimator.start();

    }
    //打叉
    public void getwrong(int p_x,int p_y){
        checkpaint.setAlpha(255);
        checkprogress = 0;
        checkpath = new Path();
        checkpath.addCircle(p_x, p_y, 2 * onep_screen_h, Path.Direction.CW);
        checkpath.moveTo(p_x - 1 * onep_screen_h, p_y - 2 * onep_screen_w);
        checkpath.lineTo(p_x + 1 * onep_screen_h, p_y + 2 * onep_screen_w);
        checkpath.moveTo(p_x + 1 * onep_screen_h, p_y - 2 * onep_screen_w);
        checkpath.lineTo(p_x - 1 * onep_screen_h, p_y + 2 * onep_screen_w);
        PathMeasure measure = new PathMeasure(checkpath, false);
        checklength = measure.getLength();
        //打叉音效
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
        checkanimator.start();
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
                if (re.contains((int) Point_x-startx, (int) Point_y-starty)) {
                    Log.i("zh", "index: "+i+"isright: "+regions[i].isright);
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
                        for(int j=0;j<regions.length;j++){
                            regions[j].istouch=false;
                        }
                    }
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }
            }
            if(isshadow&&isdown){
                isdown=false;
                if(curreindex<8){
                    for(int i=0;i<2;i++){
                        //计算控制点的边界
                        chooseregions[i].path.computeBounds(r, true);
                        //设置区域路径和剪辑描述的区域
                        re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                        //点击到正确区域
                        if (re.contains((int) Point_x-startx, (int) Point_y-starty)) {
                            if(chooseregions[i].id==curreid&&chooseregions[i].typename==curretypename&&!regions[curreindex].isright){
                                isright=true;
                                regions[curreindex].isright=true;
                                getright(chooseregions[i].point_x,chooseregions[i].point_y);

                            }
                            else{
                                isright=false;
                                getwrong(chooseregions[i].point_x,chooseregions[i].point_y);
                            }
                            break;
                        }
                    }
                }
                else if(curreindex<16){
                    for(int i=2;i<6;i++){
                        //计算控制点的边界
                        chooseregions[i].path.computeBounds(r, true);
                        //设置区域路径和剪辑描述的区域
                        re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                        //点击到正确区域
                        if (re.contains((int) Point_x-startx, (int) Point_y-starty)) {
                            if(chooseregions[i].id==curreid&&chooseregions[i].typename==curretypename&&!regions[curreindex].isright){
                                isright=true;
                                regions[curreindex].isright=true;
                                getright(chooseregions[i].point_x,chooseregions[i].point_y);

                            }
                            else{
                                isright=false;
                                getwrong(chooseregions[i].point_x,chooseregions[i].point_y);
                            }
                            break;
                        }
                    }
                }
             else if(curreindex<24){
                    for(int i=6;i<10;i++){
                        //计算控制点的边界
                        chooseregions[i].path.computeBounds(r, true);
                        //设置区域路径和剪辑描述的区域
                        re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                        //点击到正确区域
                        if (re.contains((int) Point_x-startx, (int) Point_y-starty)) {
                            if(chooseregions[i].id==curreid&&chooseregions[i].typename==curretypename&&!regions[curreindex].isright){
                                isright=true;
                                regions[curreindex].isright=true;
                                getright(chooseregions[i].point_x,chooseregions[i].point_y);

                            }
                            else{
                                isright=false;
                                getwrong(chooseregions[i].point_x,chooseregions[i].point_y);
                            }
                            break;
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
                c.drawRect(rects[i],paint);
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
            shadowrect = new Rect(0, (int) (60*onep_screen_h+8*onep_screen_w-shadowprogress * 16 * onep_screen_w),
                    (int)(1.2*Screen_w), (int)(60*onep_screen_h+8*onep_screen_w));
            c.drawRect(shadowrect,shadowpaint);
        }
        paint.setColor(Color.BLACK);
        //判断区域
        for(int i=0;i<regions.length;i++){
            if(regions[i].isright) {
                paint.setStyle(Paint.Style.FILL);
                c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].getTypename()),null,rects[i],paint);
                paint.setTextSize(60 * fontrite);
                if(regions[i].id!=0)
                c.drawText("" + regions[i].id, regions[i].point_x - 1.5f * onep_screen_w, regions[i].point_y + 1.5f * onep_screen_w, paint);
            }
            else{
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                c.drawRect(rects[i],paint);
            }
        }
        //选择区域
        if(isshadow) {
            if(curreindex<8){
                for (int i = 0; i < 2; i++) {
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].typename),null,chooserectfs[i],paint);
                    paint.setTextSize(60 * fontrite);
                    if(chooseregions[i].id!=0)
                        c.drawText("" + chooseregions[i].id, chooseregions[i].point_x - 1.5f * onep_screen_w, chooseregions[i].point_y + 1.5f * onep_screen_w, paint);
                }
            }
            else if(curreindex<16){
                for (int i = 2; i < 6; i++) {
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].typename),null,chooserectfs[i],paint);
                    paint.setTextSize(60 * fontrite);
                    if(chooseregions[i].id!=0)
                        c.drawText("" + chooseregions[i].id, chooseregions[i].point_x - 1.5f * onep_screen_w, chooseregions[i].point_y + 1.5f * onep_screen_w, paint);
                }
            }
            else  if(curreindex<24){
                for (int i = 6; i < 10; i++) {
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].typename),null,chooserectfs[i],paint);
                    paint.setTextSize(60 * fontrite);
                    if(chooseregions[i].id!=0)
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
