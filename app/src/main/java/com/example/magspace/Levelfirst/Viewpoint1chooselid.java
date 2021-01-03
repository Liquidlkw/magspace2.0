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

public class Viewpoint1chooselid extends View {
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

    public static Viewpoint1chooselid instance;
    public static  Viewpoint1chooselid getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Viewpoint1chooselid(context, display);
        }
        return instance;
    }
    public Viewpoint1chooselid(Context context) {
        super(context);
    }
    public Viewpoint1chooselid(Context context,Display display) {
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
        //8个判断区域
        rects= new RectF[8];
        regions= new Touchregion[8];
        for(int i=0;i<4;i++){
            rects[i]=new RectF((5+20*i)*onep_screen_w,10*onep_screen_h,(20+20*i)*onep_screen_w,10*onep_screen_h+15*onep_screen_w);
        }
        for(int i=0;i<4;i++){
            rects[i+4]=new RectF((5+20*i)*onep_screen_w,40*onep_screen_h,(20+20*i)*onep_screen_w,40*onep_screen_h+15*onep_screen_w);
        }
        for(int i=0;i<8;i++){
//                path = new Path();
//                path.addRect(rects[i], Path.Direction.CW);
//                regions[i] = new Touchregion(path);
            if(i<4) {
                path = new Path();
                anopath = new Path();
                path.addRect(rects[i], Path.Direction.CW);
                if(i==0){
                    anopath.moveTo(12.5f*onep_screen_w,7.5f*onep_screen_w+10*onep_screen_h);
                    anopath.lineTo(32.5f*onep_screen_w,7.5f*onep_screen_w+40*onep_screen_h);
                    regions[0] = new Touchregion(path, anopath);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(Viewpoint1chooselid.this, "progress1", 0.0f, 1.0f);
                    animator.setDuration(1000);
                    regions[0].setAnimator(animator);
                }
                if(i==1){
                    anopath.moveTo(32.5f*onep_screen_w,7.5f*onep_screen_w+10*onep_screen_h);
                    anopath.lineTo(52.5f*onep_screen_w,7.5f*onep_screen_w+40*onep_screen_h);
                    regions[1] = new Touchregion(path, anopath);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(Viewpoint1chooselid.this, "progress2", 0.0f, 1.0f);
                    animator.setDuration(1000);
                    regions[1].setAnimator(animator);
                }
                if(i==2){
                    anopath.moveTo(52.5f*onep_screen_w,7.5f*onep_screen_w+10*onep_screen_h);
                    anopath.lineTo(12.5f*onep_screen_w,7.5f*onep_screen_w+40*onep_screen_h);
                    regions[2] = new Touchregion(path, anopath);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(Viewpoint1chooselid.this, "progress3", 0.0f, 1.0f);
                    animator.setDuration(1000);
                    regions[2].setAnimator(animator);
                }
                if(i==3){
                    anopath.moveTo(72.5f*onep_screen_w,7.5f*onep_screen_w+10*onep_screen_h);
                    anopath.lineTo(72.5f*onep_screen_w,7.5f*onep_screen_w+40*onep_screen_h);
                    regions[3] = new Touchregion(path, anopath);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(Viewpoint1chooselid.this, "progress4", 0.0f, 1.0f);
                    animator.setDuration(1000);
                    regions[3].setAnimator(animator);
                }
            }
            else{
                path = new Path();
                path.addRect(rects[i], Path.Direction.CW);
                regions[i] = new Touchregion(path);
            }
        }
        regions[0].typename=regions[5].typename="yuan";
        regions[1].typename=regions[6].typename="wu";
        regions[2].typename=regions[4].typename="si";
        regions[3].typename=regions[7].typename="liu";
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
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img2)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img3)).getBitmap() : null;
    Bitmap c4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img4)).getBitmap() : null;
    Bitmap c5 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img5)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img5)).getBitmap() : null;
    Bitmap c6 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img6)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img6)).getBitmap() : null;
    Bitmap c7 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img7)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img7)).getBitmap() : null;
    Bitmap c8 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book1_section7_page1_img8)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book1_section7_page1_img8)).getBitmap() : null;
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
        c.drawBitmap(c5,null,rects[4],paint);
        c.drawBitmap(c6,null,rects[5],paint);
        c.drawBitmap(c7,null,rects[6],paint);
        c.drawBitmap(c8,null,rects[7],paint);
        //判定区域
        for(int i=0;i<4;i++){
            if(regions[i].isright)
                c.drawPath(regions[i].anopath,regions[i].paint);
        }
    }
}
