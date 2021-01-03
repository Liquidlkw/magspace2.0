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
public class Regular1chooseangle extends View{
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

    public static Regular1chooseangle instance;
    public static  Regular1chooseangle getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Regular1chooseangle(context, display);
        }
        return instance;
    }
    public Regular1chooseangle(Context context) {
        super(context);
    }
    public Regular1chooseangle(Context context,Display display) {
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

        rightcheckanimator = ObjectAnimator.ofFloat(Regular1chooseangle.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Regular1chooseangle.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Regular1chooseangle.this, "shadowprogress", 0.0f, 1.0f);
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
        //4个判断区域
        rects= new RectF[16];
        namelist=new String[16];
        pxlist=new float[16];
        pylist=new float[16];
        minrects=new RectF[3];
        regions= new Touchregion[5];
        for(int i=0;i<4;i++){
            rects[i]=new RectF((5+20*(i%4))*onep_screen_w,2*onep_screen_h,
                    (25+20*(i%4))*onep_screen_w,2*onep_screen_h+20*onep_screen_w);
            pxlist[i]=(15+20*(i%4))*onep_screen_w;
            pylist[i]=(2*onep_screen_h+10*onep_screen_w);

        }
        for(int i=0;i<12;i++){
            rects[4+i]=new RectF((5+20*(i%4))*onep_screen_w,(2+(i/4)*5)*onep_screen_h+20*onep_screen_w,
                    (25+20*(i%4))*onep_screen_w,(7+(i/4)*5)*onep_screen_h+20*onep_screen_w);
            pxlist[4+i]=(15+20*(i%4))*onep_screen_w;
            pylist[4+i]=(4.5f+(i/4)*5)*onep_screen_h+20*onep_screen_w;
        }
        minrects[0]=new RectF((27)*onep_screen_w,2*onep_screen_h+2*onep_screen_w,
                (43)*onep_screen_w,2*onep_screen_h+18*onep_screen_w);
        minrects[1]=new RectF((47)*onep_screen_w,2*onep_screen_h+2*onep_screen_w,
                (63)*onep_screen_w,2*onep_screen_h+18*onep_screen_w);
        minrects[2]=new RectF((67)*onep_screen_w,2*onep_screen_h+2*onep_screen_w,
                (83)*onep_screen_w,2*onep_screen_h+18*onep_screen_w);
        path = new Path();
        path.addRect(rects[10], Path.Direction.CW);
        regions[0] = new Touchregion(path);
        path = new Path();
        path.addRect(rects[11], Path.Direction.CW);
        regions[1] = new Touchregion(path);
        path = new Path();
        path.addRect(rects[13], Path.Direction.CW);
        regions[2] = new Touchregion(path);
        path = new Path();
        path.addRect(rects[14], Path.Direction.CW);
        regions[3] = new Touchregion(path);
        path = new Path();
        path.addRect(rects[15], Path.Direction.CW);
        regions[4] = new Touchregion(path);
        regions[0].typename="360°";
        regions[1].typename="540°";
        regions[2].typename="60°";
        regions[3].typename="360°";
        regions[4].typename="108°";
        namelist[0]="正多边形";
        namelist[4]="三角形个数";
        namelist[5]="        1";
        namelist[6]="        2";
        namelist[7]="        3";
        namelist[8]="内角和";
        namelist[9]="      180°";
        namelist[10]="()=180°x2";
        namelist[11]="()=180°x3";
        namelist[12]="内角度数";
        namelist[13]="()=180°÷3";
        namelist[14]="90°=()÷4";
        namelist[15]="()=180°x3";
        //四个数字选择区域
        chooserectfs= new RectF[4];
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*i+10)*onep_screen_w,50*onep_screen_h-5*onep_screen_w,
                    (20*i+20)*onep_screen_w,50*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[4];

        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((20*i+15)*onep_screen_w);
            chooseregions[i].point_y=(int)(50*onep_screen_h);
        }
        chooseregions[0].typename="60°";
        chooseregions[1].typename="108°";
        chooseregions[2].typename="360°";
        chooseregions[3].typename="540°";
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
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page1_img1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page1_img1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page1_img2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page1_img2)).getBitmap() : null;
    Bitmap c3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.book3_section7_page1_img3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.book3_section7_page1_img3)).getBitmap() : null;
  ;
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
                        if(chooseregions[i].typename==curretypename&&!regions[curreindex].isright){
                            isright=true;
                            regions[curreindex].isright=true;
                            getright(chooseregions[i].point_x,chooseregions[i].point_y);
                            if(curreindex==0){
                                namelist[10]="      "+curretypename;
                            }
                            if(curreindex==1){
                                namelist[11]="      "+curretypename;
                            }
                            if(curreindex==2){
                                namelist[13]="      "+curretypename;
                            }
                            if(curreindex==3){
                                namelist[14]="      "+curretypename;
                            }
                            if(curreindex==4){
                                namelist[15]="      "+curretypename;
                            }
                        }
                        else if(chooseregions[i].typename!=curretypename&&!regions[curreindex].isright){
                            isright=false;
                            getwrong(chooseregions[i].point_x,chooseregions[i].point_y);
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
            shadowrect = new Rect(0, (int) (50*onep_screen_h+8*onep_screen_w-shadowprogress * 16 * onep_screen_w),
                    (int)(1.2*Screen_w), (int)(50*onep_screen_h+8*onep_screen_w));
            c.drawRect(shadowrect,shadowpaint);
        }
        //画外框
        for(int i=0;i<rects.length;i++){
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            c.drawRect(rects[i],paint);
        }

        //三个图形
        c.drawBitmap(c1,null,minrects[0],paint);
        c.drawBitmap(c2,null,minrects[1],paint);
        c.drawBitmap(c3,null,minrects[2],paint);

        //里面的文字
        for(int i=0;i<rects.length;i++){
            paint.setTextSize(40 * fontrite);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            if(i!=1&&i!=2&&i!=3) c.drawText(namelist[i],pxlist[i]-7*onep_screen_w,pylist[i]+onep_screen_h,paint);
//            if(i==0){
//                c.drawText(namelist[i],pxlist[i],pylist[i],paint);
//            }
//           else if(i>=4&&i<=9){
//                c.drawText(namelist[i],pxlist[i],pylist[i],paint);
//            }
//           else if(i==12){
//                c.drawText(namelist[i],pxlist[i],pylist[i],paint);
//            }
//            else if(i==10||i==11||i==13||i==14||i==15){
//                c.drawText(namelist[i],pxlist[i],pylist[i],paint);
//            }
        }
        //选择区域
        if(isshadow) {
            for (int i = 0; i < chooseregions.length; i++) {
                paint.setTextSize(40 * fontrite);
                c.drawText(chooseregions[i].typename, chooseregions[i].point_x - 3*onep_screen_w, chooseregions[i].point_y + 2 * onep_screen_w, paint);
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
