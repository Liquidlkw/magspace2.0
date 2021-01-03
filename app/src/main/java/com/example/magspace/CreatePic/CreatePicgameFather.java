package com.example.magspace.CreatePic;

import android.animation.Animator;
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
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Point;
import com.example.magspace.Bean.Touchregion;
import com.example.magspace.Utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class CreatePicgameFather extends View {
    Path path;
    Paint paint;

    public int Screen_w;//模拟机为1280X720
    public int Screen_h;
    public float onep_screen_w;
    public float onep_screen_h;
    public float fontrite;
    //打叉图形所需组件
    Path checkpath;
    Paint checkpaint;
    float checklength;
    ObjectAnimator checkanimator;
    boolean ischeck;
    boolean checkflag;
    float checkprogress;
    //截屏种类判断结果
    public int res;
    //矩形数组
//    public Rect[][] rects;
    public RectF[][] rectfs;
    //图形存储数组
    public int[][] resultrect;
    public String[][ ] resnamerect;
    //剩余颜色数量存储
    public int leftcolor[];
    public int leftindex;
    public boolean gettypeflag;
    //方块总计数
    public int squresum;
    //坐标存储
    ArrayList<Point> respoint = new ArrayList<Point>();
    ArrayList<Point> redpoint = new ArrayList<Point>();
    ArrayList<Point> orangepoint = new ArrayList<Point>();
    ArrayList<Point> greenpoint = new ArrayList<Point>();
    ArrayList<Point> yellowpoint = new ArrayList<Point>();
    ArrayList<Point> bluepoint = new ArrayList<Point>();
    ArrayList<Point> lightbluepoint = new ArrayList<Point>();
    ArrayList<Point> pinkpoint = new ArrayList<Point>();

    //判定区域数组
    public Touchregion regions[], chooseregions[], chooseregions2[];
    //判断是否在区域内的方法
    Region re;
    //构造一个区域对象，左闭右开的。
    RectF r;
    //是否需要判定选择图形类型
    public Rect rects[];
    public RectF chooserectf[],minrectf[],chooserectf2[];
    boolean isneedchoose;
    //特殊选择，如第一关
    boolean isneedchoose2;
    //需要截屏，如第一关
    boolean isneedcut;
    //当前选中的图形类型
    String curretypename;
    //当前选中的正确类型
    String righttypename;
    //    String initrighttypename;
    boolean isright;
    //触摸检测数据
    public static int Point_x;
    public static int Point_y;
    public static boolean ismove;
    public static boolean isdown;
    public int index;
    //截屏
    public Bitmap[] shotpics;
    //判断是否首次完成
    public boolean[] getpicflag;
    //判断是否完成裁剪
    public boolean[] getpiccut;
    //动画计时
    public ObjectAnimator timeani[];
    //当前判断区域
    public Touchregion currregion;

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

    public int curreid;
    public int curreindex;
    public boolean resetflag=true;

    public CreatePicgameFather(Context context) {
        super(context);
    }

    public void resetPath() {
        if(index==1||index==2) {
            for (int i = 1; i <= regions.length - 1; i++) {
                regions[i].istouch = false;
            }
            if (isneedchoose) {
                curretypename = chooseregions[0].typename;
                chooseregions[0].leftnum = chooseregions[0].maxnum;
                chooseregions[0].istouch = true;
                for (int i = 1; i < chooseregions.length; i++) {
                    chooseregions[i].leftnum = chooseregions[i].maxnum;
                    chooseregions[i].istouch = false;
                }
            }
            if (isneedchoose2) {
                for (int i = 0; i < chooseregions2.length; i++) {
//                chooseregions2[i].leftnum=chooseregions2[i].maxnum;
                    chooseregions2[i].istouch = false;
                }
//            righttypename=initrighttypename;
            }
            Point_x = 0;
            Point_y = 0;
            //截屏重置
            if (isneedcut) {
                currregion = null;
                for (int i = 0; i < shotpics.length; i++) {
                    shotpics[i] = null;
                    getpicflag[i] = true;
                    getpiccut[i] = false;
                    timeani[i] = null;
                }
            }


            if (resultrect != null) {
                for (int i = 0; i < resultrect.length; i++) {
                    for (int j = 0; j < resultrect[i].length; j++) {
                        resultrect[i][j] = 0;
                    }
                }
            }
        }
        if(index==3){
            curretypename = chooseregions[0].typename;
            chooseregions[0].leftnum = chooseregions[0].maxnum;
            chooseregions[0].istouch = true;
            for (int i = 1; i < chooseregions.length; i++) {
                chooseregions[i].istouch = false;
            }
            squresum=0;
            gettypeflag=false;
            res=0;
            getpicflag=new boolean[]{true,true,true,true,true};
            getpiccut=new boolean[]{false,false,false,false,false};
            leftcolor = new int[]{1,1,1,1};
            leftindex=0;
            respoint.clear();
            for (int i = 0; i < shotpics.length; i++) {
                shotpics[i] = null;
            }
            for(int i=0;i<regions.length;i++){
                regions[i].typename=null;
            }
        }
        if(index==4){
            isshadow=false;
            checkpaint.setAlpha(0);
            rightcheckpaint.setAlpha(0);

                for(int i=0;i<regions.length;i++){
                    regions[i].isright=false;
                }

        }
        if(index==5){
            for(int i=0;i<chooseregions.length;i++){
                chooseregions[i].istouch=false;
            }
            leftcolor = new int[]{4,4,4,4};
            for(int i=0;i<regions.length;i++){
                regions[i].typename=null;
            }
            squresum=0;
            curretypename=null;
        }
        if(index==6){
            redpoint.clear();
            orangepoint.clear();
            greenpoint.clear();
            bluepoint.clear();
            isshadow=false;
            squresum=0;
            for(int i=0;i<regions.length;i++){
                regions[i].typename=null;
                regions[i].isright=false;
            }
        }
    }

    public void init(Display display) {
        paint = new Paint();
        paint.setAntiAlias(true);
        this.setBackgroundColor(Color.WHITE);
        isneedchoose = false;
        isneedchoose2 = false;
        checkflag = false;
        Screen_w = display.getWidth();
        Screen_h = display.getHeight();
        if(Screen_h>Screen_w){
            int x=Screen_w;
            Screen_w=Screen_h;
            Screen_h=x;
        }
        onep_screen_w = Screen_w * 1.0f / 100;
        onep_screen_h = Screen_h * 1.0f / 100;
        fontrite = Screen_w * 1.0f / 1280;
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

    public void setProgress1(float progress) {
        regions[1].paint.setPathEffect(createPathEffect(regions[1].length, progress, 0.0f));
        invalidate();

    }

    public void setProgress2(float progress) {
        regions[2].paint.setPathEffect(createPathEffect(regions[2].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress3(float progress) {
        regions[3].paint.setPathEffect(createPathEffect(regions[3].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress4(float progress) {
        regions[4].paint.setPathEffect(createPathEffect(regions[4].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress5(float progress) {
        regions[5].paint.setPathEffect(createPathEffect(regions[5].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress6(float progress) {
        regions[6].paint.setPathEffect(createPathEffect(regions[6].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress7(float progress) {
        regions[7].paint.setPathEffect(createPathEffect(regions[7].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress8(float progress) {
        regions[8].paint.setPathEffect(createPathEffect(regions[8].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress9(float progress) {
        regions[9].paint.setPathEffect(createPathEffect(regions[9].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress10(float progress) {
        regions[10].paint.setPathEffect(createPathEffect(regions[10].length, progress, 0.0f));
        invalidate();
    }

    public void setProgress11(float progress) {
        regions[11].paint.setPathEffect(createPathEffect(regions[11].length, progress, 0.0f));
        invalidate();
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
    public static PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[]{pathLength, pathLength},
                pathLength - phase * pathLength);
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

    public void onDraw(Canvas c) {
        super.onDraw(c);
        //是否点击在正确区域内
        isright = false;
        //区域判定,选择区域判断
        if (isdown) {
            for (int i = 1; i <= regions.length - 1; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (!isneedchoose2 || (curretypename == righttypename)) {
                    //选中类型判断
                    if (!isneedchoose || (isneedchoose && curretypename == regions[i].typename)) {
                        //点击到正确区域
                        if (re.contains((int) Point_x, (int) Point_y)) {
                            isright = true;
                            isdown = false;

                            //该区域还没有显示,播放动画
                            if (!regions[i].istouch) {
                                //需要选择类型判断则在相应类型的数量上减1
                                if (isneedchoose) {
                                    for (int j = 0; j < chooseregions.length; j++) {
                                        if (chooseregions[j].typename == regions[i].typename) {
                                            chooseregions[j].leftnum--;
                                            //有特殊选择,成功播放特殊音效
                                            if (isneedchoose2 && chooseregions[j].leftnum == 0 && DataUtil.isvoiceplay) {
                                                currregion=regions[i];
                                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                            }
                                        }
                                    }
                                }
                                //点击正确音效
                                if (DataUtil.isvoiceplay)
                                    DataUtil.soundPool.play(6, 1, 1, 1, 0, 1);
                                //过关音效
//                            if (pic_num == 0 && DataUtil.isvoiceplay)
//                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                if(regions[i].animator!=null)
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
            if (isneedchoose2) {
                for (int i = 0; i < chooseregions2.length; i++) {
                    //计算控制点的边界
                    chooseregions2[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(chooseregions2[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        isright = true;
                        isdown = false;
                        chooseregions2[i].istouch = true;
                        righttypename = chooseregions2[i].typename;
                        //选中后其他的就不被选中并清空画线
                        for (int j = 1; j < regions.length; j++) {
                            regions[j].istouch = false;
                        }
                        //其他设置为不选中
                        for (int j = 0; j < chooseregions2.length; j++) {
                            if (i != j) {
                                chooseregions2[j].istouch = false;
//                                chooseregions2[j].leftnum=chooseregions2[j].maxnum;
                            }
                        }
                        //选中后会自动选中正确的选择部件,并重置技术
                        for (int j = 0; j < chooseregions.length; j++) {
                            chooseregions[j].leftnum = chooseregions[j].maxnum;
                            if (chooseregions[j].typename == righttypename) {
                                chooseregions[j].istouch = true;
                                curretypename = righttypename;
                            } else {
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
        for (int i = 1; i <= regions.length - 1; i++) {
            if (regions[i].istouch)
                c.drawPath(regions[i].path, regions[i].paint);
        }
        //选择区域
        if (isneedchoose) {
            for (int i = 0; i < chooseregions.length; i++) {
                if (chooseregions[i].istouch) {
                    c.drawPath(chooseregions[i].path, chooseregions[i].paint);
                }
                //剩余数量
//                c.drawText(""+chooseregions[i].leftnum, chooseregions[i].point_x*onep_screen_w,
//                        chooseregions[i].point_y*onep_screen_h, chooseregions[i].numpaint);
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
