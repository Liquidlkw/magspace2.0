package com.example.magspace.LevelThird;
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
public class Regular3choosename extends View{
    Path path,anopath;
    Paint paint;
    public Touchregion regions[],chooseregions[];
    public String namelist[];
    public float pxlist[],pylist[];
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
    public RectF rects[],chooserectfs[],minrects[];
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

    public static Regular3choosename instance;
    public static  Regular3choosename getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Regular3choosename(context, display);
        }
        return instance;
    }
    public Regular3choosename(Context context) {
        super(context);
    }
    public Regular3choosename(Context context,Display display) {
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

        rightcheckanimator = ObjectAnimator.ofFloat(Regular3choosename.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Regular3choosename.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Regular3choosename.this, "shadowprogress", 0.0f, 1.0f);
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
        //8个判断区域
        //前4个红色大方块，中间4个图形方块，最后16个黑色小方块
        rects= new RectF[24];
        namelist=new String[16];
        pxlist=new float[16];
        pylist=new float[16];
        regions= new Touchregion[8];
        //大方块
        for(int i=0;i<4;i++){
            rects[i]=new RectF((4+40*(i%2))*onep_screen_w,(10+20*(i/2))*onep_screen_h,
                    (44+40*(i%2))*onep_screen_w,(30+20*(i/2))*onep_screen_h);

        }
        //图形方块
        for(int i=0;i<4;i++){
            rects[4+i]=new RectF((19+40*(i%2))*onep_screen_w,(14.5f+20*(i/2))*onep_screen_h-5*onep_screen_w,
                    (29+40*(i%2))*onep_screen_w,(14.5f+20*(i/2))*onep_screen_h+5*onep_screen_w);

        }
        //黑框小方块
        for(int i=0;i<4;i++){
            rects[8+i]=new RectF((6+40*(i%2))*onep_screen_w,(19+(i/2)*20)*onep_screen_h,
                    (24+40*(i%2))*onep_screen_w,(24+(i/2)*20)*onep_screen_h);
            pxlist[i]=(15+40*(i%2))*onep_screen_w;
            pylist[i]=(21.5f+(i/2)*20)*onep_screen_h;
        }
        for(int i=0;i<4;i++){
            rects[12+i]=new RectF((6+40*(i%2))*onep_screen_w,(24+(i/2)*20)*onep_screen_h,
                    (24+40*(i%2))*onep_screen_w,(29+(i/2)*20)*onep_screen_h);
            pxlist[4+i]=(15+40*(i%2))*onep_screen_w;
            pylist[4+i]=(26.5f+(i/2)*20)*onep_screen_h;
        }
        for(int i=0;i<4;i++){
            rects[16+i]=new RectF((24+40*(i%2))*onep_screen_w,(19+(i/2)*20)*onep_screen_h,
                    (42+40*(i%2))*onep_screen_w,(24+(i/2)*20)*onep_screen_h);
            pxlist[8+i]=(33+40*(i%2))*onep_screen_w;
            pylist[8+i]=(21.5f+(i/2)*20)*onep_screen_h;
        }
        for(int i=0;i<4;i++){
            rects[20+i]=new RectF((24+40*(i%2))*onep_screen_w,(24+(i/2)*20)*onep_screen_h,
                    (42+40*(i%2))*onep_screen_w,(29+(i/2)*20)*onep_screen_h);
            pxlist[12+i]=(33+40*(i%2))*onep_screen_w;
            pylist[12+i]=(26.5f+(i/2)*20)*onep_screen_h;
        }
        for(int i=0;i<8;i++){
            path = new Path();
            path.addRect(rects[16+i], Path.Direction.CW);
            regions[i] = new Touchregion(path);
        }
        regions[0].id=8;
        regions[1].id=20;
        regions[2].id=6;
        regions[3].id=12;

        regions[4].typename="  正八面体";
        regions[5].typename="正二十面体";
        regions[6].typename="    立方体";
        regions[7].typename="正十二面体";

        //四个数字选择区域
        chooserectfs= new RectF[4];
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*i+10)*onep_screen_w,60*onep_screen_h-5*onep_screen_w,
                    (20*i+20)*onep_screen_w,60*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[4];

        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((20*i+15)*onep_screen_w);
            chooseregions[i].point_y=(int)(60*onep_screen_h);
        }
        chooseregions[0].typename="    立方体";
        chooseregions[0].id=6;
        chooseregions[1].typename="  正八面体";
        chooseregions[1].id=8;
        chooseregions[2].typename="正十二面体";
        chooseregions[2].id=12;
        chooseregions[3].typename="正二十面体";
        chooseregions[3].id=20;
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
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page3_img2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page3_img2)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page3_img3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page3_img3)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page3_img4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page3_img4)).getBitmap() : null;
    Bitmap c4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page3_img5)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page3_img5)).getBitmap() : null;
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
                for(int i=0;i<chooseregions.length;i++){
                    //计算控制点的边界
                    chooseregions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x-startx, (int) Point_y-starty)) {
                        if(curreindex<4){
                            if (chooseregions[i].id == curreid && !regions[curreindex].isright) {
                                isright = true;
                                regions[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                            } else if (chooseregions[i].id != curreid && !regions[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                        else {
                            if (chooseregions[i].typename == curretypename && !regions[curreindex].isright) {
                                isright = true;
                                regions[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                            } else if (chooseregions[i].typename != curretypename && !regions[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                    }
                }
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
        //画红外框
        for(int i=0;i<4;i++){
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            c.drawRect(rects[i],paint);
        }

        //4个图形
        c.drawBitmap(c1,null,rects[4],paint);
        c.drawBitmap(c2,null,rects[5],paint);
        c.drawBitmap(c3,null,rects[6],paint);
        c.drawBitmap(c4,null,rects[7],paint);
//画黄色内填充
        for(int i=8;i<16;i++){
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(2);
            c.drawRect(rects[i],paint);
        }
        //画黑外框
        for(int i=8;i<24;i++){
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            c.drawRect(rects[i],paint);
        }


        //上方的文字
        paint.setTextSize(40 * fontrite);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        c.drawText("制作出如图像所示的正多面体，得出相应的面数和名称",2*onep_screen_w,5*onep_screen_h,paint);
        //里面的文字
        for(int i=0;i<4;i++){
            paint.setTextSize(40 * fontrite);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            c.drawText("面数",pxlist[i]-3*onep_screen_w,pylist[i]+onep_screen_w,paint);
        }
        for(int i=4;i<8;i++){
            paint.setTextSize(40 * fontrite);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            c.drawText("名称",pxlist[i]-3*onep_screen_w,pylist[i]+onep_screen_w,paint);
        }
        for(int i=0;i<4;i++){
            if(regions[i].isright)
            c.drawText(regions[i].id+"",pxlist[i+8]-onep_screen_w,pylist[i+8]+onep_screen_w,paint);
            if(regions[i+4].isright)
            c.drawText(regions[i+4].typename,pxlist[i+12]-8*onep_screen_w,pylist[i+12]+onep_screen_w,paint);
        }
        //选择区域
        if(isshadow) {
            for (int i = 0; i < chooseregions.length; i++) {
                paint.setTextSize(40 * fontrite);
                if(curreindex<4)
                    c.drawText("    "+chooseregions[i].id, chooseregions[i].point_x - 4*onep_screen_w, chooseregions[i].point_y + 2 * onep_screen_w, paint);
                else
                      c.drawText(chooseregions[i].typename, chooseregions[i].point_x - 8*onep_screen_w, chooseregions[i].point_y + 2 * onep_screen_w, paint);
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
