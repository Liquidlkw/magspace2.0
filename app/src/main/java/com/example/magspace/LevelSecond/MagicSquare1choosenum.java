package com.example.magspace.LevelSecond;
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
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class MagicSquare1choosenum extends View{
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


    public static MagicSquare1choosenum instance;
    public static  MagicSquare1choosenum getInstance(Context context, Display display){
        if (instance == null) {
            instance=new MagicSquare1choosenum(context, display);
        }
        return instance;
    }
    public MagicSquare1choosenum(Context context) {
        super(context);
    }
    public MagicSquare1choosenum(Context context,Display display) {
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

        rightcheckanimator = ObjectAnimator.ofFloat(MagicSquare1choosenum.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(MagicSquare1choosenum.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
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
        //10个判断区域
        rects= new RectF[10];
        regions= new Touchregion[10];

            rects[0]=new RectF(15*onep_screen_w,30*onep_screen_h,
                    23*onep_screen_w,30*onep_screen_h+8*onep_screen_w);
            path = new Path();
            path.addRect(rects[0], Path.Direction.CW);
            regions[0] = new Touchregion(path);
            regions[0].point_x=(int)(19*onep_screen_w);
            regions[0].point_y=(int)(30*onep_screen_h+4*onep_screen_w);
            regions[0].id=3;

        rects[1]=new RectF(7*onep_screen_w,30*onep_screen_h+8*onep_screen_w,
                15*onep_screen_w,30*onep_screen_h+16*onep_screen_w);
        path = new Path();
        path.addRect(rects[1], Path.Direction.CW);
        regions[1] = new Touchregion(path);
        regions[1].point_x=(int)(11*onep_screen_w);
        regions[1].point_y=(int)(30*onep_screen_h+12*onep_screen_w);
        regions[1].id=2;

        rects[2]=new RectF(15*onep_screen_w,30*onep_screen_h+8*onep_screen_w,
                23*onep_screen_w,30*onep_screen_h+16*onep_screen_w);
        path = new Path();
        path.addRect(rects[2], Path.Direction.CW);
        regions[2] = new Touchregion(path);
        regions[2].point_x=(int)(19*onep_screen_w);
        regions[2].point_y=(int)(30*onep_screen_h+12*onep_screen_w);
        regions[2].id=1;

        rects[3]=new RectF(23*onep_screen_w,30*onep_screen_h+8*onep_screen_w,
                31*onep_screen_w,30*onep_screen_h+16*onep_screen_w);
        path = new Path();
        path.addRect(rects[3], Path.Direction.CW);
        regions[3] = new Touchregion(path);
        regions[3].point_x=(int)(27*onep_screen_w);
        regions[3].point_y=(int)(30*onep_screen_h+12*onep_screen_w);
        regions[3].id=5;

        rects[4]=new RectF(15*onep_screen_w,30*onep_screen_h+16*onep_screen_w,
                23*onep_screen_w,30*onep_screen_h+24*onep_screen_w);
        path = new Path();
        path.addRect(rects[4], Path.Direction.CW);
        regions[4] = new Touchregion(path);
        regions[4].point_x=(int)(19*onep_screen_w);
        regions[4].point_y=(int)(30*onep_screen_h+20*onep_screen_w);
        regions[4].id=4;


        rects[5]=new RectF(65*onep_screen_w,30*onep_screen_h,
                73*onep_screen_w,30*onep_screen_h+8*onep_screen_w);
        path = new Path();
        path.addRect(rects[5], Path.Direction.CW);
        regions[5] = new Touchregion(path);
        regions[5].point_x=(int)(69*onep_screen_w);
        regions[5].point_y=(int)(30*onep_screen_h+4*onep_screen_w);
        regions[5].id=1;

        rects[6]=new RectF(57*onep_screen_w,30*onep_screen_h+8*onep_screen_w,
                65*onep_screen_w,30*onep_screen_h+16*onep_screen_w);
        path = new Path();
        path.addRect(rects[1], Path.Direction.CW);
        regions[6] = new Touchregion(path);
        regions[6].point_x=(int)(61*onep_screen_w);
        regions[6].point_y=(int)(30*onep_screen_h+12*onep_screen_w);
        regions[6].id=2;

        rects[7]=new RectF(65*onep_screen_w,30*onep_screen_h+8*onep_screen_w,
                73*onep_screen_w,30*onep_screen_h+16*onep_screen_w);
        path = new Path();
        path.addRect(rects[7], Path.Direction.CW);
        regions[7] = new Touchregion(path);
        regions[7].point_x=(int)(69*onep_screen_w);
        regions[7].point_y=(int)(30*onep_screen_h+12*onep_screen_w);
        regions[7].id=5;

        rects[8]=new RectF(73*onep_screen_w,30*onep_screen_h+8*onep_screen_w,
                81*onep_screen_w,30*onep_screen_h+16*onep_screen_w);
        path = new Path();
        path.addRect(rects[8], Path.Direction.CW);
        regions[8] = new Touchregion(path);
        regions[8].point_x=(int)(77*onep_screen_w);
        regions[8].point_y=(int)(30*onep_screen_h+12*onep_screen_w);
        regions[8].id=3;

        rects[9]=new RectF(65*onep_screen_w,30*onep_screen_h+16*onep_screen_w,
                73*onep_screen_w,30*onep_screen_h+24*onep_screen_w);
        path = new Path();
        path.addRect(rects[9], Path.Direction.CW);
        regions[9] = new Touchregion(path);
        regions[9].point_x=(int)(69*onep_screen_w);
        regions[9].point_y=(int)(30*onep_screen_h+20*onep_screen_w);
        regions[9].id=4;
        for(int i=0;i<regions.length;i++){
            regions[i].isright=true;
        }
        regions[0].isright=regions[1].isright=regions[8].isright=regions[9].isright=false;
        //5个数字选择区域
        chooserectfs= new RectF[5];
        for(int i=0;i<5;i++){
            chooserectfs[i]=new RectF((15*i+9)*onep_screen_w,10*onep_screen_h-6*onep_screen_w,
                    (15*i+19)*onep_screen_w,10*onep_screen_h+6*onep_screen_w);

        }
        chooseregions=new Touchregion[5];
        for(int i=0;i<5;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((15*i+14)*onep_screen_w);
            chooseregions[i].point_y=(int)(10*onep_screen_h);
            chooseregions[i].id=i+1;
        }
       curreid=0;
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
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img2)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img3)).getBitmap() : null;
    Bitmap c4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img4)).getBitmap() : null;
    Bitmap c5 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img5)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img5)).getBitmap() : null;
    Bitmap num8 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img6)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img6)).getBitmap() : null;
    Bitmap num10 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section2_page1_img7)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section2_page1_img7)).getBitmap() : null;

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
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    if (!regions[i].isright) {
                        for(int j=0;j<regions.length;j++){
                            regions[j].istouch=false;
                        }
                        regions[i].istouch=true;
                        curreindex=i;
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
            if(isdown){
                isdown=false;
                for(int i=0;i<chooseregions.length;i++){
                    //计算控制点的边界
                    chooseregions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if(curreid!=0) {
                        if (re.contains((int) Point_x - startx, (int) Point_y - starty)) {
                            if (chooseregions[i].id == curreid && !regions[curreindex].isright) {
                                isright = true;
                                regions[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                            } else if (chooseregions[i].id != curreid && !regions[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                    }
                    else{
                        if (re.contains((int) Point_x - startx, (int) Point_y - starty)) {
                            isright = false;
                            getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
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

        paint.setColor(Color.BLACK);
        c.drawBitmap(num8,null,new RectF(7*onep_screen_w,
                30*onep_screen_h-8*onep_screen_w,15*onep_screen_w, 30*onep_screen_h),paint);
        c.drawBitmap(num10,null,new RectF(57*onep_screen_w,
                30*onep_screen_h-8*onep_screen_w,65*onep_screen_w, 30*onep_screen_h),paint);
        //判断区域
        for(int i=0;i<regions.length;i++){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            c.drawRect(rects[i],paint);
            if(regions[i].isright) {
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(60 * fontrite);
                c.drawText("" + regions[i].id, regions[i].point_x - 1.5f * onep_screen_w, regions[i].point_y + 1.5f * onep_screen_w, paint);
            }
        }
        //选择区域

        c.drawBitmap(c1,null,chooserectfs[0],paint);
        c.drawBitmap(c2,null,chooserectfs[1],paint);
        c.drawBitmap(c3,null,chooserectfs[2],paint);
        c.drawBitmap(c4,null,chooserectfs[3],paint);
        c.drawBitmap(c5,null,chooserectfs[4],paint);

        //勾叉
        if(isright){
            c.drawPath(rightcheckpath,rightcheckpaint);
        }
        else{
            c.drawPath(checkpath,checkpaint);
        }
    }
}
