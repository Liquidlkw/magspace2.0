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
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.Levelfirst.Viewpoint1chooselid;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
public class SimilarPic6choosepic extends  View{
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

    ObjectAnimator  ani[];

    public static SimilarPic6choosepic instance;
    public static  SimilarPic6choosepic getInstance(Context context, Display display){
        if (instance == null) {
            instance=new SimilarPic6choosepic(context, display);
        }
        return instance;
    }
    public SimilarPic6choosepic(Context context) {
        super(context);
    }
    public SimilarPic6choosepic(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
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
        //4个判断区域
        rects= new RectF[4];
        regions= new Touchregion[4];
        for(int i=0;i<2;i++){
            rects[i]=new RectF((5+40*i)*onep_screen_w,5*onep_screen_h,(35+40*i)*onep_screen_w,5*onep_screen_h+10*onep_screen_w);
        }
        for(int i=0;i<2;i++){
            rects[i+2]=new RectF((5+40*i)*onep_screen_w,15*onep_screen_h,(35+40*i)*onep_screen_w,15*onep_screen_h+10*onep_screen_w);
        }
        for(int i=0;i<4;i++){
            if(i<1) {
                path = new Path();
                anopath = new Path();
                path.addRect(rects[i], Path.Direction.CW);
                if(i==0){
                    anopath.moveTo(20*onep_screen_w,5*onep_screen_w+5*onep_screen_h);
                    anopath.lineTo(60*onep_screen_w,5*onep_screen_w+15*onep_screen_h);
                    regions[0] = new Touchregion(path, anopath);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(SimilarPic6choosepic.this, "progress1", 0.0f, 1.0f);
                    animator.setDuration(1000);
                    regions[0].setAnimator(animator);
                }
            }
            else{
                path = new Path();
                path.addRect(rects[i], Path.Direction.CW);
                regions[i] = new Touchregion(path);
            }
        }
        regions[0].typename=regions[3].typename="fang";
        regions[1].typename="san";
        regions[2].typename="yuan";

    }
    public void setProgress1(float progress)
    {
        regions[0].paint.setPathEffect(createPathEffect(regions[0].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress2(float progress)
    {
        regions[1].paint.setPathEffect(createPathEffect(regions[1].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress3(float progress)
    {
        regions[2].paint.setPathEffect(createPathEffect(regions[2].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress4(float progress)
    {
        regions[3].paint.setPathEffect(createPathEffect(regions[3].length, progress, 0.0f));
        invalidate();
    }
    public static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                pathLength - phase * pathLength);
    }
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section1_page6_img1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section1_page6_img1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section1_page6_img2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section1_page6_img2)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section1_page6_img3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section1_page6_img3)).getBitmap() : null;
    Bitmap c4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book2_section1_page6_img4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book2_section1_page6_img4)).getBitmap() : null;

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
                    if(!regions[i].isright){
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        if(curretypename==regions[i].typename&&curreindex!=i){
                            Log.i("zh", "curretypename: "+curretypename+"curreindex: "+curreindex);
                            if (DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                            regions[i].isright=regions[curreindex].isright=true;
                            if(i<curreindex){
                                regions[i].animator.start();
                            }
                            else{
                                regions[curreindex].animator.start();
                            }
                        }
                        else {
                            curreindex = i;
                            curretypename = regions[i].typename;
                        }
                    }
                    break;
                }
            }
        }
        c.drawBitmap(c1,null,rects[0],paint);
        c.drawBitmap(c2,null,rects[1],paint);
        c.drawBitmap(c3,null,rects[2],paint);
        c.drawBitmap(c4,null,rects[3],paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50*fontrite);
        c.drawText("A",20*onep_screen_w,5*onep_screen_h+15*onep_screen_w,paint);
        c.drawText("B",60*onep_screen_w,5*onep_screen_h+15*onep_screen_w,paint);
        c.drawText("C",20*onep_screen_w,15*onep_screen_h+15*onep_screen_w,paint);
        c.drawText("D",60*onep_screen_w,15*onep_screen_h+15*onep_screen_w,paint);
        //判定区域
        for(int i=0;i<1;i++){
            if(regions[i].isright)
                c.drawPath(regions[i].anopath,regions[i].paint);
        }
    }
}
