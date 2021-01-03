package com.example.magspace.CreatePic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Touchregion;
import com.example.magspace.KnowNumber.Knownumbergame1;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class CreatePicgame4 extends CreatePicgameFather {
    public static CreatePicgame4 instance;
    public int res;
    public static  CreatePicgame4 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new CreatePicgame4(context, display);
        }
        return instance;
    }

    public CreatePicgame4(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        super.init(display);
        rightcheckanimator = ObjectAnimator.ofFloat(CreatePicgame4.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(CreatePicgame4.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(CreatePicgame4.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=4;
        //16个判断区
        regions=new Touchregion[16];
        rectfs =new RectF[4][4];
        resultrect=new int[4][4];
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                rectfs[i][j]=new RectF((45+j*8) * onep_screen_w, 15 * onep_screen_h +i*8 * onep_screen_w,
                        (53+j*8)* onep_screen_w, 15 * onep_screen_h +(i+1)* 8 * onep_screen_w);
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
        //六个数字选择区域
        chooserectf= new RectF[6];
        for(int i=0;i<6;i++){
            chooserectf[i]=new RectF((12*i+14)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (12*i+24)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[6];

        for(int i=0;i<6;i++){
            path = new Path();
            path.addRect(chooserectf[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((12*i+19)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
        }
        chooseregions[0].typename="red";
        chooseregions[1].typename="orange";
        chooseregions[2].typename="green";
        chooseregions[3].typename="yellow";
        chooseregions[4].typename="blue";
        chooseregions[5].typename="light_blue";
        //3个第二选择区
        chooserectf2= new RectF[3];
        minrectf=new RectF[3];
        for(int i=0;i<3;i++){
            chooserectf2[i]=new RectF(20*onep_screen_w,20*i*onep_screen_h+15*onep_screen_h,
                    (34)*onep_screen_w,20*i*onep_screen_h+35*onep_screen_h);
            minrectf[i]=new RectF(21*onep_screen_w,20*i*onep_screen_h+16*onep_screen_h,
                    (33)*onep_screen_w,20*i*onep_screen_h+34*onep_screen_h);

        }
        chooseregions2=new Touchregion[3];
        for(int i=0;i<3;i++){
            path = new Path();
            path.addRect(chooserectf2[i], Path.Direction.CW);
            chooseregions2[i]=new Touchregion(path);
            chooseregions2[i].id=i;
        }
    }
    public void changgetypename(int id){
        if(id==0){
            squresum=10;
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if((i+j)%2==0){
                        regions[i*4+j].typename="red";
                    }
                    else
                        regions[i*4+j].typename="orange";
                }
            }
            regions[0].isright=true;
            regions[1].isright=true;
            regions[2].isright=true;
            regions[3].isright=false;
            regions[4].isright=true;
            regions[5].isright=true;
            regions[6].isright=false;
            regions[7].isright=false;
            regions[8].isright=true;
            regions[9].isright=true;
            regions[10].isright=true;
            regions[11].isright=false;
            regions[12].isright=true;
            regions[13].isright=true;
            regions[14].isright=false;
            regions[15].isright=false;
        }
        if(id==2){
            squresum=10;
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if((i+j)%2==0){
                        regions[i*4+j].typename="light_blue";
                    }
                    else
                        regions[i*4+j].typename="blue";
                }
            }
            regions[0].isright=false;
            regions[1].isright=false;
            regions[2].isright=false;
            regions[3].isright=true;
            regions[4].isright=false;
            regions[5].isright=false;
            regions[6].isright=true;
            regions[7].isright=true;
            regions[8].isright=false;
            regions[9].isright=true;
            regions[10].isright=true;
            regions[11].isright=true;
            regions[12].isright=true;
            regions[13].isright=true;
            regions[14].isright=true;
            regions[15].isright=true;
        }
        if(id==1){
            squresum=8;
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if((i+j)%2==0){
                        regions[i*4+j].typename="green";
                    }
                    else
                        regions[i*4+j].typename="yellow";
                }
            }
            regions[0].isright=true;
            regions[1].isright=true;
            regions[2].isright=false;
            regions[3].isright=false;
            regions[4].isright=true;
            regions[5].isright=true;
            regions[6].isright=true;
            regions[7].isright=false;
            regions[8].isright=true;
            regions[9].isright=false;
            regions[10].isright=false;
            regions[11].isright=false;
            regions[12].isright=true;
            regions[13].isright=true;
            regions[14].isright=false;
            regions[15].isright=false;
        }
    }
    Bitmap choose1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page4image1)) != null ? ((BitmapDrawable)
                this.getResources().getDrawable(R.drawable.s2page4image1)).getBitmap() : null;
    Bitmap choose2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page4image2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s2page4image2)).getBitmap() : null;
    Bitmap choose3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s2page4image3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s2page4image3)).getBitmap() : null;
    public void onDraw(Canvas c) {

        //区域判定,选择区域判断
        if (isdown) {
            for (int i = 0; i < regions.length ; i++) {
                //计算控制点的边界
                regions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown=false;
                    isshadow=false;
                    checkpaint.setAlpha(0);
                    rightcheckpaint.setAlpha(0);
                    if (!regions[i].isright) {
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
                        curretypename=regions[i].typename;
                        curreid=regions[i].id;
                    } else {
                    }
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }

            }
            for(int i=0;i<chooseregions2.length;i++){
                //计算控制点的边界
                chooseregions2[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(chooseregions2[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown=false;
                    chooseregions2[i].istouch=true;
                    for(int j=0;j<chooseregions2.length;j++){
                        if(j!=i)
                            chooseregions2[j].istouch=false;
                    }
                     changgetypename(chooseregions2[i].id);
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
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        if(chooseregions[i].typename==curretypename&&!regions[curreindex].isright){
                            squresum++;
                            isright=true;
                            regions[curreindex].isright=true;
                            getright(chooseregions[i].point_x,chooseregions[i].point_y);
                            if(squresum==16&&DataUtil.isvoiceplay){
                                DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
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
                if(regions[4*i+j].isright)
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[4*i+j].typename),null,rectfs[i][j],paint);
            }
        }
        //第二选择区域
        for(int i=0;i<chooseregions2.length;i++){
            if(chooseregions2[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(chooserectf2[i],paint);
                paint.setAlpha(255);
            }
        }
        c.drawBitmap(choose1,null,minrectf[0],paint);
        c.drawBitmap(choose2,null,minrectf[1],paint);
        c.drawBitmap(choose3,null,minrectf[2],paint);
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
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 25 * onep_screen_h), (int)(1.2*Screen_w), Screen_h);
            c.drawRect(shadowrect,shadowpaint);
        }
        if(isshadow){
            for(int i=0;i<chooseregions.length;i++){
                c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].getTypename()), null, chooserectf[i], paint);
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
