package com.example.magspace.SymmertyPic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.Utils.DataUtil;


public class SymmertypicgameFather extends View {
    Path path;
    Paint paint;

    public int Screen_w;//模拟机为1280X720
    public  int Screen_h;
    public  float onep_screen_w;
    public  float onep_screen_h;
    public    int pic_num;
    public int maxpic_num;
    public  float fontrite;
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

    //判定区域数组
    public Touchregion regions[],chooseregions[],regions2[];
    int regionindex;
    public RectF[] rectfs,rectfs2,chooserectfs,minrectfs;
    //判断是否在区域内的方法
    Region re;
    //构造一个区域对象，左闭右开的。
    RectF r;
    //是否需要判定选择图形类型
    boolean isneedchoose;
    //当前选中的图形类型，当前选中的数字,当前选中的矩形下表
    String curretypename;
    int curreid,curreindex;
    boolean isright;
    //触摸检测数据
    public  static int Point_x;
    public static int Point_y;
    public static boolean ismove;
    public  static boolean isdown;
    public int index;
    //截屏
    public Bitmap[] shotpics;
    //判断是否首次完成
    public boolean[] getpicflag;
    //判断是否完成裁剪
    public boolean[] getpiccut;
    public int squresum;
    public boolean resetflag=true;
    //获得当前的关卡
    public int getIndex(){
        return index;
    }
    public int getpicnum() {return pic_num;}
    public SymmertypicgameFather(Context context) {
        super(context);
    }
    //清空所有path,初始化部分数据
    public  void resetPath(){
        pic_num=maxpic_num;
        Point_x=0;
        Point_y=0;
        isshadow=false;
        checkpaint.setAlpha(0);
        rightcheckpaint.setAlpha(0);
        if(index==1||index==2||index==3){
            for(int i=0;i<regions.length;i++){
                regions[i].isright=false;
            }
        }
        if(index==4) {
            squresum = 4;
            for (int i = 0; i < regions2.length; i++) {
                regions2[i].isright = false;
                regions2[i].istouch = false;
            }
            for (int i = 0; i < regions.length; i++) {
                regions[i].isright = true;
            }
            regions2[0].istouch=true;
            regions2[0].isright=true;
            regions[0].typename="red";
            regions[1].typename="blue";
            regions[2].typename="yellow";
            regions[3].typename="green";
        }
        if(index==5){
            regions2[0].istouch=true;
            regions2[1].istouch=false;
            for(int i=0;i<regions2.length;i++){
                regions2[i].isright=false;
            }
            for(int i=0;i<regions.length;i++){
                regions[i].istouch=false;
                regions[i].isright=false;
                regions[i].typename=null;
            }
            isshadow=false;
            checkpaint.setAlpha(0);
            rightcheckpaint.setAlpha(0);
            regionindex=0;
            squresum=0;
            regions[6].typename=regions[9].typename=regions[12].typename="blue";
            regions[7].typename=regions[10].typename=regions[13].typename="light_blue";
            getpicflag=new boolean[]{true,true};
            getpiccut=new boolean[]{false,false};
            for (int i = 0; i < shotpics.length; i++) {
                shotpics[i] = null;
            }
        }
        if(index==6){
            regions2[0].istouch=true;
            regions2[1].istouch=false;
            for(int i=0;i<regions2.length;i++){
                regions2[i].isright=false;
            }
            for(int i=0;i<regions.length;i++){
                regions[i].istouch=false;
                regions[i].isright=false;
            }
            isshadow=false;
            checkpaint.setAlpha(0);
            rightcheckpaint.setAlpha(0);
            regionindex=0;
            squresum=0;
            getpicflag=new boolean[]{true,true};
            getpiccut=new boolean[]{false,false};
            for (int i = 0; i < shotpics.length; i++) {
                shotpics[i] = null;
            }
        }
    }

    public void init(Display display)
    {
        paint =new Paint();
        paint.setAntiAlias(true);
        this.setBackgroundColor(Color.WHITE);
        checkflag=false;
        Screen_w = display.getWidth();
        Screen_h = display.getHeight();
        if(Screen_h>Screen_w){
            int x=Screen_w;
            Screen_w=Screen_h;
            Screen_h=x;
        }
        onep_screen_w=Screen_w*1.0f/100;
        onep_screen_h=Screen_h*1.0f/100;
        fontrite=Screen_w*1.0f/1280;

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
        //未知bug，设置过高则会出问题
//        shadowpaint.setStrokeWidth();
////        shadowpaint.setAlpha(0);
//        shadowpath=new Path();
//        shadowpath.moveTo(Screen_w/2,Screen_h);
//        shadowpath.lineTo(Screen_w/2,Screen_h-20*onep_screen_h);
//        PathMeasure measure = new PathMeasure(shadowpath, false);
//        shadowlength = measure.getLength();

        re = new Region();
        r = new RectF();
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
    //打勾
    public void getright( int p_x, int p_y){
        rightcheckpaint.setAlpha(255);
        rightcheckprogress = 0;
        rightcheckpath = new Path();
        rightcheckpath.addCircle(p_x, p_y, 2 * onep_screen_w, Path.Direction.CW);
        rightcheckpath.moveTo(p_x - 0.5f * onep_screen_w, p_y);
        rightcheckpath.lineTo(p_x , p_y + 1* onep_screen_h);
        rightcheckpath.lineTo(p_x + 1 * onep_screen_w, p_y - 1 * onep_screen_h);;
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
        checkpath.addCircle(p_x, p_y, 2 * onep_screen_w, Path.Direction.CW);
        checkpath.moveTo(p_x - 1 * onep_screen_w, p_y - 2 * onep_screen_h);
        checkpath.lineTo(p_x + 1 * onep_screen_w, p_y + 2 * onep_screen_h);
        checkpath.moveTo(p_x + 1 * onep_screen_w, p_y - 2 * onep_screen_h);
        checkpath.lineTo(p_x - 1 * onep_screen_w, p_y + 2 * onep_screen_h);
        PathMeasure measure = new PathMeasure(checkpath, false);
        checklength = measure.getLength();
        //打叉音效
        if (DataUtil.isvoiceplay)
            DataUtil.soundPool.play(3, 1, 1, 1, 0, 1);
        checkanimator.start();
    }
    public static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                pathLength - phase * pathLength);
    }
    public void getShotPic(int index) {
        Log.i("zh", "getShotPic: "+index);
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        //克隆一个新对象存储
        Bitmap s = this.getDrawingCache().copy(Bitmap.Config.ARGB_8888, true);
        this.shotpics[index] = s;
//
//        for (int i = 0; i < 3; i++)
//            if (this.shotpics[i] != null)
//                System.out.println(this.shotpics[i]);
    }
}
