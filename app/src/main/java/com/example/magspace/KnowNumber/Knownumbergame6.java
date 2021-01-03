package com.example.magspace.KnowNumber;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;

import com.example.magspace.Bean.Point;
import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Knownumbergame6  extends KnownumbergameFather {
    public static Knownumbergame6 instance;

    public static  Knownumbergame6 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Knownumbergame6(context, display);
        }
        return instance;
    }

    public Knownumbergame6(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public boolean isright(){
        if(regions[0].typename.equals(regions[1].typename)&&regions[1].typename.equals(regions[4].typename)&&regions[8].typename.equals(regions[8].typename)&&
                regions[2].typename.equals(regions[3].typename)&&regions[3].typename.equals(regions[7].typename)&&regions[7].typename.equals(regions[11].typename)&&
                regions[5].typename.equals(regions[9].typename)&&regions[9].typename.equals(regions[13].typename)&&regions[13].typename.equals(regions[12].typename)&&
                regions[6].typename.equals(regions[10].typename)&&regions[10].typename.equals(regions[14].typename)&&regions[14].typename.equals(regions[15].typename))
            return true;
        return false;
    }
    public void init(Display display) {
        super.init(display);
        index=6;
        squresum=0;
        pic_num=maxpic_num=1;
        //16个判断区
        regions=new Touchregion[16];
        rectfs=new RectF[16];
        for(int i=0;i<16;i++) {
            rectfs[i]=new RectF((45+(i%4)*8) * onep_screen_w, 15 * onep_screen_h +(i/4)*8 * onep_screen_w,
                    (53+(i%4)*8)* onep_screen_w, 15 * onep_screen_h +(i/4+1)*8 * onep_screen_w);


        }
        for(int i=0;i<16;i++){
            Path path=new Path();
            path.addRect(rectfs[i], Path.Direction.CW);
            regions[i]=new Touchregion(path);
            regions[i].point_x=(int)((49+(i%4)*8) * onep_screen_w);
            regions[i].point_y=(int)(15 * onep_screen_h +((i/4)*8+4) * onep_screen_w);
        }
        regions[0].id=regions[1].id=1;
        regions[2].id=regions[3].id=regions[4].id=regions[10].id=regions[11].id=regions[12].id=regions[15].id=2;
        regions[5].id=regions[6].id=regions[13].id=3;
        regions[9].id=4;
        regions[14].id=5;
        regions[7].id=6;
        regions[8].id=8;

        //四个选择区域
        chooseregions= new Touchregion[4];
        chooserectfs= new RectF[4];
        minrectfs =new RectF[4];
        //蓝红绿黄
        leftcolor = new int[]{4,4,4,4};
        for(int i=0;i<4;i++){
            chooserectfs[i]=new RectF((20*onep_screen_w),(15*onep_screen_h+i*10*onep_screen_w),
                    (32*onep_screen_w),(15*onep_screen_h+((i+1)*10+1)*onep_screen_w));
            minrectfs[i]=new RectF((20*onep_screen_w),(15*onep_screen_h+((i*10+1)*onep_screen_w)),
                    (32*onep_screen_w),(15*onep_screen_h+((i+1)*10-1)*onep_screen_w));
        }
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectfs[i],Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
        }
        chooseregions[0].typename="blue";
        chooseregions[1].typename="red";
        chooseregions[2].typename="light_green";
        chooseregions[3].typename="yellow";
    }
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page6image1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page6image1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page6image2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page6image2)).getBitmap() : null;
    Bitmap choose3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page6image3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page6image3)).getBitmap() : null;
    Bitmap choose4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page6image4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page6image4)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //是否点击在正确区域内
        isright = false;
        //区域判定,选择区域判断
        if (isdown&&pic_num!=0) {
            for (int i = 0; i < regions.length ; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)&&curretypename!=null) {
                    isright = true;
                    isdown = false;
                    //没有方块
                    if(regions[i].typename==null&&leftcolor[leftindex]!=0){
                        regions[i].typename=curretypename;
                        leftcolor[leftindex]--;
                        squresum++;
                        if(squresum==16){
                            gettypeflag=true;
                        }
                    }
                    //相同块,消除当前方块
                    else if(regions[i].typename!=null){
                        if(regions[i].typename=="blue")
                            leftcolor[0]++;
                        else if(regions[i].typename=="red"){
                            leftcolor[1]++;
                        }
                        else if(regions[i].typename=="light_green"){
                            leftcolor[2]++;
                        }
                        else
                            leftcolor[3]++;
                        regions[i].typename=null;
                        squresum--;

                    }
                }

            }

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
                    if(curretypename=="red")
                        leftindex=1;
                    if(curretypename=="yellow")
                        leftindex=3;
                    if(curretypename=="blue")
                        leftindex=0;
                    if(curretypename=="light_green")
                        leftindex=2;
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
        if(squresum==16&&gettypeflag){
            gettypeflag=false;
            if(isright()){
                pic_num=0;
                if(DataUtil.isvoiceplay){
                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                }
            }
        }
        //判断区域
        for(int i=0;i<16;i++) {
                paint.setStyle(Paint.Style.FILL);
                paint.setARGB(185, 240, 230, 140);
                c.drawRect(rectfs[i], paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(1);
                c.drawRect(rectfs[i], paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            paint.setTextSize(60 * fontrite);
            c.drawText(regions[i].id + "", regions[i].point_x - 1.5f * onep_screen_w, regions[i].point_y + 1.5f * onep_screen_w, paint);
        }
        //色块
        for(int i=0;i<16;i++){
                if(regions[i].typename!=null)
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[i].typename),null,rectfs[i],paint);
        }
        //选中阴影
        for(int i=0;i<chooseregions.length;i++){
            if(chooseregions[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(chooserectfs[i],paint);
                paint.setAlpha(255);
            }
        }
        //选择区域
        c.drawBitmap(choose1,null,minrectfs[0],paint);
        c.drawBitmap(choose2,null,minrectfs[1],paint);
        c.drawBitmap(choose3,null,minrectfs[2],paint);
        c.drawBitmap(choose4,null,minrectfs[3],paint);
    }

}
