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
public class Pic6choosepic extends  View{
    Path path,anopath;
    Paint paint;
    public Touchregion regions[];
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
    public RectF rects[];
    //当前选中的图形类型
    String curretypename;
    int curreindex;
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




    public static Pic6choosepic instance;
    public static  Pic6choosepic getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Pic6choosepic(context, display);
        }
        return instance;
    }
    public Pic6choosepic(Context context) {
        super(context);
    }
    public Pic6choosepic(Context context,Display display) {
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

        rightcheckanimator = ObjectAnimator.ofFloat(Pic6choosepic.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Pic6choosepic.this, "checkprogress", 0.0f, 1.0f);
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
        //6个判断区域
        rects= new RectF[6];
        regions= new Touchregion[6];
        for(int i=0;i<6;i++){
            if(i<3)
            rects[i]=new RectF((10+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w,
                    (22+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w+15*onep_screen_w);
            if(i==3){
                rects[i]=new RectF((12+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w,
                        (20+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w+15*onep_screen_w);
            }
            if(i==4){
                rects[i]=new RectF((13+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w,
                        (19+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w+15*onep_screen_w);
            }
            if(i==5){
                rects[i]=new RectF((9+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w,
                        (23+30*(i%3))*onep_screen_w,30*onep_screen_h+30*(i/3)*onep_screen_w+15*onep_screen_w);
            }
        }
        for(int i=0;i<6;i++){
                path = new Path();
                path.addRect(rects[i], Path.Direction.CW);
                regions[i] = new Touchregion(path);
                regions[i].point_x=(int)((16+30*(i%3))*onep_screen_w);
                regions[i].point_y=(int)(30*onep_screen_h+30*(i/3)*onep_screen_w+7.5f*onep_screen_w);
        }
        regions[0].typename="A";
        regions[1].typename="B";
        regions[2].typename="C";
        regions[3].typename="D";
        regions[4].typename="E";
        regions[5].typename="F";
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
    Bitmap back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img8)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img8)).getBitmap() : null;
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img2)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img3)).getBitmap() : null;
    Bitmap c4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img4)).getBitmap() : null;
    Bitmap c5 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img5)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img5)).getBitmap() : null;
    Bitmap c6 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section5_page6_img6)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section5_page6_img6)).getBitmap() : null;

    public void onDraw(Canvas c) {
        //区域判定,选择区域判断;
        if (isdown) {
            isdown=false;
            for (int i = 0; i < regions.length; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x-startx, (int)( Point_y)-starty)) {

                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.FILL);
                        if(regions[i].typename=="E"){
                            isright=true;
                            if (DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                            getright(regions[i].point_x,regions[i].point_y);
                        }
                        else {
                            isright=false;
                            if (DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
                            getwrong(regions[i].point_x,regions[i].point_y);
                        }
                    break;
                }
            }
        }
        paint.setTextSize(50*fontrite);
        paint.setColor(Color.BLACK);
        c.drawText("找出哪个在覆盖下面图片时用不到的图形",0,5*onep_screen_h,paint);
        for(int i=0;i<regions.length;i++){
            c.drawText(regions[i].typename,regions[i].point_x,regions[i].point_y+15*onep_screen_w,paint);
        }
        c.drawBitmap(back,null,new RectF(28*onep_screen_w,10*onep_screen_h,68*onep_screen_w,10*onep_screen_h+20*onep_screen_w),paint);
        c.drawBitmap(c1,null,rects[0],paint);
        c.drawBitmap(c2,null,rects[1],paint);
        c.drawBitmap(c3,null,rects[2],paint);
        c.drawBitmap(c4,null,rects[3],paint);
        c.drawBitmap(c5,null,rects[4],paint);
        c.drawBitmap(c6,null,rects[5],paint);
        //勾叉
        if(isright){
            c.drawPath(rightcheckpath,rightcheckpaint);
        }
        else{
            c.drawPath(checkpath,checkpaint);
        }


    }
}
