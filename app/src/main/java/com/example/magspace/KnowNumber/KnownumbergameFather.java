package com.example.magspace.KnowNumber;

import android.animation.ObjectAnimator;
import android.content.Context;
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
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.Utils.DataUtil;

public class KnownumbergameFather extends View {
    Path path;
    Paint paint;

    public int Screen_w;//模拟机为1280X720
    public  int Screen_h;
    public  float onep_screen_w;
    public  float onep_screen_h;
    public    int pic_num;
    public int squresum;
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
    public int[] leftcolor;
    public boolean gettypeflag;
    public int leftindex;
    public boolean resetflag=true;
    //获得当前的关卡
    public int getIndex(){
        return index;
    }
    public int getpicnum() {return pic_num;}
    public KnownumbergameFather(Context context) {
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
        if(index==1){
            for(int i=0;i<regions.length;i++){
                regions[i].istouch=false;
            }
            regions[2].isright=regions[4].isright=regions[8].isright=regions[11].isright=false;
        }
        if(index==2){
            for(int i=0;i<regions.length;i++){
                regions[i].istouch=false;
            }
            regions[2].isright=regions[4].isright=regions[5].isright=regions[6].isright=regions[7].isright=regions[9].isright=
                    regions[14].isright=regions[15].isright=regions[16].isright=regions[17].isright=regions[18].isright=regions[19].isright=false;
            for(int i=0;i<regions2.length;i++){
                regions2[i].istouch=false;
            }
            regions2[1].isright=regions2[3].isright=false;
        }
        if(index==3){
            for(int i=0;i<regions.length;i++){
                regions[i].isright=false;
            }
        }
        if(index==4){
            squresum=0;
            for(int i=0;i<regions.length;i++){
                regions[i].isright=false;
                regions[i].istouch=false;
                regions[i].typename=null;
                regions[i].id=0;
            }
            for(int i=0;i<regions2.length;i++){
                regions2[i].istouch=false;
            }
            for(int i=0;i<chooseregions.length;i++){
                chooseregions[i].isright=false;
            }
        }
        if(index==5){
            for(int i=0;i<regions.length;i++){
                regions[i].istouch=false;
                regions[i].isright=false;
            }
            regions[0].isright=regions[1].isright=true;
        }
        if(index==6){
            leftcolor = new int[]{4,4,4,4};
            squresum=0;
            curretypename=null;
            for(int i=0;i<chooseregions.length;i++){
                chooseregions[i].istouch=false;
            }
            for(int i=0;i<regions.length;i++){
                regions[i].typename=null;
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
}
