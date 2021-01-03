package com.example.magspace.FindPic;

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
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class FindpicturegameFather extends View {
    Path path;
    Paint paint;

    public int Screen_w;//模拟机为1280X720
    public  int Screen_h;
    public  float onep_screen_w;
    public  float onep_screen_h;
    public    int pic_num;
    public  float fontrite;
    //打叉图形所需组件
    Path checkpath;
    Paint checkpaint;
    float  checklength;
    ObjectAnimator checkanimator;
    boolean ischeck;
    boolean checkflag;
    float checkprogress;
    //判定区域数组
    public   Touchregion regions[],chooseregions[];
    //判断是否在区域内的方法
    Region re;
    //构造一个区域对象，左闭右开的。
    RectF r;
    //是否需要判定选择图形类型
    public Rect rects[];
    boolean isneedchoose;
    //当前选中的图形类型
    String curretypename;
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
    public FindpicturegameFather(Context context) {
        super(context);
    }
    //清空所有path,初始化部分数据
    public  void resetPath(){
        pic_num=regions.length-1;
        for(int i=1;i<=regions.length-1;i++){
            regions[i].istouch=false;
        }
        if (isneedchoose) {
            curretypename=chooseregions[0].typename;
            chooseregions[0].leftnum = chooseregions[0].maxnum;
            chooseregions[0].istouch = true;
            for (int i = 1; i < chooseregions.length; i++) {
                chooseregions[i].leftnum = chooseregions[i].maxnum;
                chooseregions[i].istouch = false;
            }
        }
        Point_x=0;
        Point_y=0;
    }
    public void init(Display display)
    {
        paint =new Paint();
        paint.setAntiAlias(true);
        this.setBackgroundColor(Color.WHITE);
        isneedchoose=false;
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
        re = new Region();
        r = new RectF();
    }
public void setProgress1(float progress)
{
    regions[1].paint.setPathEffect(createPathEffect(regions[1].length, progress, 0.0f));
    invalidate();

}

    public void setProgress2(float progress)
    {
        regions[2].paint.setPathEffect(createPathEffect(regions[2].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress3(float progress)
    {
        regions[3].paint.setPathEffect(createPathEffect(regions[3].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress4(float progress)
    {
        regions[4].paint.setPathEffect(createPathEffect(regions[4].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress5(float progress)
    {
        regions[5].paint.setPathEffect(createPathEffect(regions[5].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress6(float progress)
    {
        regions[6].paint.setPathEffect(createPathEffect(regions[6].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress7(float progress)
    {
        regions[7].paint.setPathEffect(createPathEffect(regions[7].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress8(float progress)
    {
        regions[8].paint.setPathEffect(createPathEffect(regions[8].length, progress, 0.0f));
        invalidate();
    }
    public void setProgress9(float progress)
    {
        regions[9].paint.setPathEffect(createPathEffect(regions[9].length, progress, 0.0f));
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
    public void onDraw(Canvas c) {
        super.onDraw(c);
        //是否点击在正确区域内
        isright=false;
        //区域判定,选择区域判断
        if(isdown) {
            for (int i = 1; i <= regions.length - 1; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型判断
                if(!isneedchoose||(isneedchoose&&curretypename==regions[i].typename)) {
                    //点击到正确区域
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        isright = true;
                        isdown = false;

                        //该区域还没有显示,播放动画
                        if (!regions[i].istouch) {
                            pic_num--;
                            //需要选择类型判断则在相应类型的数量上减1
                            if(isneedchoose){
                                for(int j=0;j<chooseregions.length;j++){
                                    if(chooseregions[j].typename==regions[i].typename)
                                        chooseregions[j].leftnum--;
                                }
                            }
                            //点击正确音效
                            if (DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                            //过关音效
                            if (pic_num == 0 && DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                            regions[i].animator.start();
                            regions[i].istouch = true;
                        } else {
                            //选择了已被选中的区域
                            if (DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(2, 1, 1, 1, 0, 1);
                        }
                        break;

                    }
                }
            }
            if(isneedchoose) {
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
                        curretypename=chooseregions[i].typename;

                        //选中后其他的就不被选中
                        for(int j=0;j<chooseregions.length;j++){
                            if(i!=j){
                                chooseregions[j].istouch=false;
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
        for(int i=1;i<=regions.length-1;i++){
            if(regions[i].istouch)
                c.drawPath(regions[i].path,regions[i].paint);
        }
        //选择区域
        if(isneedchoose) {
            for (int i = 0; i < chooseregions.length; i++) {
                if (chooseregions[i].istouch) {
                    c.drawPath(chooseregions[i].path, chooseregions[i].paint);
                }
                //剩余数量
                c.drawText(""+chooseregions[i].leftnum, chooseregions[i].point_x*onep_screen_w,
                        chooseregions[i].point_y*onep_screen_h, chooseregions[i].numpaint);
            }
        }
        //打叉动画
        //如果进入判断区域，打叉动画消失
        if(isright){
            checkpaint.setAlpha(0);
        }
        //触摸到界面并且还没开始画叉
        if(isdown) {
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
        if(ischeck&&checkprogress>=1f){
            ischeck=false;
            checkpaint.setAlpha(0);
            invalidate();
            isdown=false;
        }
        c.drawPath(checkpath,checkpaint);
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
    }
}
