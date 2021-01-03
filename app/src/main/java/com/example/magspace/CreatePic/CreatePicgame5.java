package com.example.magspace.CreatePic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Point;
import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

import java.util.ArrayList;
import java.util.List;

public class CreatePicgame5 extends CreatePicgameFather{
    public static CreatePicgame5 instance;
    public int res;
    public static  CreatePicgame5 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new CreatePicgame5(context, display);
        }
        return instance;
    }

    public CreatePicgame5(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        super.init(display);
        index=5;
        squresum=0;
        regions=new Touchregion[16];
//        rects =new Rect[2][3];
        chooserectf= new RectF[4];
        minrectf =new RectF[4];
        //红黄蓝粉
        leftcolor = new int[]{4,4,4,4};
        for(int i=0;i<4;i++){
            chooserectf[i]=new RectF((20*onep_screen_w),((20+15*i)*onep_screen_h),
                    (35*onep_screen_w),(35+15*i)*onep_screen_h);
            minrectf[i]=new RectF((21*onep_screen_w),((21+15*i)*onep_screen_h),
                    (34*onep_screen_w),(34+15*i)*onep_screen_h);
        }
//        rectfs =new RectF[4][4];
//        resultrect=new int[4][4];
//        for(int i=0;i<2;i++) {
//            for(int j=0;j<4;j++) {
//                rectfs[i][j]=new RectF((30+j*10) * onep_screen_w, 20 * onep_screen_h +i*10 * onep_screen_w,
//                        (40+j*10)* onep_screen_w, 20 * onep_screen_h +(i+1)* 10 * onep_screen_w);
//            }
//        }
        //16个判断区
        regions=new Touchregion[16];
        rectfs =new RectF[4][4];
        resultrect=new int[4][4];
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                rectfs[i][j]=new RectF((45+j*8) * onep_screen_w, 20 * onep_screen_h +i*8 * onep_screen_w,
                        (53+j*8)* onep_screen_w, 20 * onep_screen_h +(i+1)* 8 * onep_screen_w);
            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                path = new Path();
                resultrect[i][j]=0;
                path.addRect(rectfs[i][j], Path.Direction.CW);
                regions[i*4+j]=new Touchregion(path,i,j);
            }
        }
        chooseregions= new Touchregion[4];
        for(int i=0;i<4;i++) {
            redpoint.add(new Point(0, 0));
            yellowpoint.add(new Point(0, 0));
            bluepoint.add(new Point(0, 0));
            pinkpoint.add(new Point(0, 0));
        }
        //四个选择区域
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectf[i],Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
        }
        chooseregions[0].typename="red";
        chooseregions[1].typename="yellow";
        chooseregions[2].typename="blue";
        chooseregions[3].typename="pink";
//        curretypename= chooseregions[0].typename;
//        chooseregions[0].istouch=true;
    }
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page5image1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s2page5image1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page5image2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s2page5image2)).getBitmap() : null;
    Bitmap choose3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page5image3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s2page5image3)).getBitmap() : null;
    Bitmap choose4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page5image4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s2page5image4)).getBitmap() : null;
    public boolean isright(ArrayList<Point> p){
        Log.i("zh", "isright: "+p.size());
        int x,y,anox=0,anoy=0,minx,maxx,miny,maxy;
        int sum;
        for(int i=0;i<4;i++){
            x=p.get(i).col;
            miny=maxy=y=p.get(i).row;
            sum=0;
            for(int j=0;j<4;j++){
                if(x==p.get(j).col){
                    sum++;
                    if (p.get(j).row<miny) {
                        miny=p.get(j).row;
                    }
                    if(p.get(j).row>maxy){
                        maxy=p.get(j).row;
                    }
                }
                else{
                    anox=p.get(j).col;
                    anoy=p.get(j).row;
                }
            }
            if(sum==3&&anoy==miny+1&&maxy==miny+2&&(anox==x+1||anox==x-1))
                return true;
        }
        for(int i=0;i<4;i++){
            y=p.get(i).row;
            minx=maxx=x=p.get(i).col;
            sum=0;
            for(int j=0;j<4;j++){
                if(y==p.get(j).row){
                    sum++;
                    if (p.get(j).col<minx) {
                        minx=p.get(j).col;
                    }
                    if(p.get(j).col>maxx){
                        maxy=p.get(j).col;
                    }
                }
                else{
                    anox=p.get(j).col;
                    anoy=p.get(j).row;
                }
            }
            if(sum==3&&anox==minx+1&&maxx==minx+2&&(anoy==y+1||anoy==y-1))
            return true;
        }
        return false;
    }
    public void onDraw(Canvas c) {
        //是否点击在正确区域内
        isright = false;
        //区域判定,选择区域判断
        if (isdown) {
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
                        if(curretypename=="red"){
                            redpoint.get(3-leftcolor[leftindex]).row=i/4;
                            redpoint.get(3-leftcolor[leftindex]).col=i%4;
                        }
                        if(curretypename=="yellow"){
                            yellowpoint.get(3-leftcolor[leftindex]).row=i/4;
                            yellowpoint.get(3-leftcolor[leftindex]).col=i%4;
                        }
                        if(curretypename=="blue"){
                            bluepoint.get(3-leftcolor[leftindex]).row=i/4;
                           bluepoint.get(3-leftcolor[leftindex]).col=i%4;
                        }
                        if(curretypename=="pink"){
                            pinkpoint.get(3-leftcolor[leftindex]).row=i/4;
                            pinkpoint.get(3-leftcolor[leftindex]).col=i%4;
                        }
                        if(squresum==16){
                            gettypeflag=true;
                        }
                    }
                    //相同块,消除当前方块
                    else if(regions[i].typename==curretypename){

                        regions[i].typename=null;
                        squresum--;
                        leftcolor[leftindex]++;
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
                            leftindex=0;
                        if(curretypename=="yellow")
                            leftindex=1;
                        if(curretypename=="blue")
                            leftindex=2;
                        if(curretypename=="pink")
                            leftindex=3;
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
            if(isright(redpoint)&&isright(yellowpoint)&&isright(bluepoint)&&isright(pinkpoint)){
                if(DataUtil.isvoiceplay){
                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                }
            }
        }
        //判断区域
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                paint.setStyle(Paint.Style.FILL);
                paint.setARGB(185, 240, 230, 140);
                c.drawRect(rectfs[i][j], paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(1);
                c.drawRect(rectfs[i][j], paint);
            }
        }
        //色块
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(regions[4*i+j].typename!=null)
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[4*i+j].typename),null,rectfs[i][j],paint);
            }
        }
        //选中阴影
        for(int i=0;i<chooseregions.length;i++){
            if(chooseregions[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(chooserectf[i],paint);
                paint.setAlpha(255);
            }
        }
        //选择区域
        c.drawBitmap(choose1,null,minrectf[0],paint);
        c.drawBitmap(choose2,null,minrectf[1],paint);
        c.drawBitmap(choose3,null,minrectf[2],paint);
        c.drawBitmap(choose4,null,minrectf[3],paint);
    }
}
