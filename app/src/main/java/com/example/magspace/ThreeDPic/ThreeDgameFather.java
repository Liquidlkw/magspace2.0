package com.example.magspace.ThreeDPic;

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

public class ThreeDgameFather extends View {
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
    public boolean resetflag=true;
    //获得当前的关卡
    public int getIndex(){
        return index;
    }
    public int getpicnum() {return pic_num;}
    public ThreeDgameFather(Context context) {
        super(context);
    }
    //清空所有path,初始化部分数据
    public  void resetPath(){
        pic_num=maxpic_num;
        Point_x=0;
        Point_y=0;
        checkpaint.setAlpha(0);
        rightcheckpaint.setAlpha(0);
        for(int i=0;i<regions.length;i++){
            regions[i].isright=false;
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
    public void onDraw(Canvas c) {

        if(isdown&&pic_num!=0){
            isdown=false;
            for(int i=0;i<regions.length;i++){
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //点击到正确区域
                if (re.contains((int) Point_x, (int) Point_y)) {
                    if(regions[i].typename=="right"&&!regions[i].isright){
                        Log.i("zh", "right");
                        pic_num--;
                        isright=true;
                        regions[i].isright=true;
                        getright(regions[i].point_x,regions[i].point_y);
                        if(pic_num==0&&DataUtil.isvoiceplay){
                            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                        }
                    }
                    else if(regions[i].typename=="wrong"&&!regions[i].isright){
                        Log.i("zh", "wrong");
                        isright=false;
                        getwrong(regions[i].point_x,regions[i].point_y);
                    }
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
