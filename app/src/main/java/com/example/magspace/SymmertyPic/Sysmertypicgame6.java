package com.example.magspace.SymmertyPic;

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
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class Sysmertypicgame6 extends SymmertypicgameFather {
    public static Sysmertypicgame6 instance;
    public Path rectpath;
    public float topdis;
    public static  Sysmertypicgame6 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new Sysmertypicgame6(context, display);
        }
        return instance;
    }

    public Sysmertypicgame6(Context context,Display display) {
        super(context);
        this.init(display);
        index=6;
        pic_num=maxpic_num=2;
        getpicflag=new boolean[]{true,true};
        getpiccut=new boolean[]{false,false};
        shotpics=new Bitmap[2];
        topdis=10*onep_screen_h;
        rightcheckanimator = ObjectAnimator.ofFloat(Sysmertypicgame6.this, "rightcheckprogress", 0.0f, 1.0f);
        rightcheckanimator.setDuration(750);
        checkanimator = ObjectAnimator.ofFloat(Sysmertypicgame6.this, "checkprogress", 0.0f, 1.0f);
        checkanimator.setDuration(750);
        shadowanimator = ObjectAnimator.ofFloat(Sysmertypicgame6.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        //外框
        rectpath=new Path();
        rectpath.moveTo(50*onep_screen_w,topdis);
        rectpath.lineTo(50*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.lineTo(82*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.lineTo(82*onep_screen_w,topdis);
        rectpath.lineTo(50*onep_screen_w,topdis);
        rectpath.moveTo(50*onep_screen_w,topdis+8*onep_screen_w);
        rectpath.lineTo(82*onep_screen_w,topdis+8*onep_screen_w);
        rectpath.moveTo(50*onep_screen_w,topdis+16*onep_screen_w);
        rectpath.lineTo(82*onep_screen_w,topdis+16*onep_screen_w);
        rectpath.moveTo(50*onep_screen_w,topdis+24*onep_screen_w);
        rectpath.lineTo(82*onep_screen_w,topdis+24*onep_screen_w);

        rectpath.moveTo(50*onep_screen_w,topdis+16*onep_screen_w);
        rectpath.lineTo(58*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(50*onep_screen_w,topdis);
        rectpath.lineTo(66*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(58*onep_screen_w,topdis);
        rectpath.lineTo(74*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(66*onep_screen_w,topdis);
        rectpath.lineTo(82*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(74*onep_screen_w,topdis);
        rectpath.lineTo(82*onep_screen_w,topdis+16*onep_screen_w);

        rectpath.moveTo(58*onep_screen_w,topdis);
        rectpath.lineTo(50*onep_screen_w,topdis+16*onep_screen_w);
        rectpath.moveTo(66*onep_screen_w,topdis);
        rectpath.lineTo(50*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(74*onep_screen_w,topdis);
        rectpath.lineTo(58*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(82*onep_screen_w,topdis);
        rectpath.lineTo(66*onep_screen_w,topdis+32*onep_screen_w);
        rectpath.moveTo(82*onep_screen_w,topdis+16*onep_screen_w);
        rectpath.lineTo(74*onep_screen_w,topdis+32*onep_screen_w);
        //16个判断区域
        regions=new Touchregion[16];
        //画图矩形与判断区域对应
        rectfs= new RectF[16];

        rectfs[0]=new RectF(54*onep_screen_w,topdis,70*onep_screen_w,topdis+16*onep_screen_w);
        path=new Path();
        path.moveTo(58*onep_screen_w,topdis);
        path.lineTo(54*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(70*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis);
        path.close();
        regions[0]=new Touchregion(path);
        regions[0].typename="liu";

        rectfs[1]=new RectF(66*onep_screen_w,topdis,78*onep_screen_w,topdis+8*onep_screen_w);
        path=new Path();
        path.moveTo(66*onep_screen_w,topdis);
        path.lineTo(70*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(78*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(74*onep_screen_w,topdis);
        path.close();
        regions[1]=new Touchregion(path);
        regions[1].typename="si";

        rectfs[2]=new RectF(70*onep_screen_w,topdis+8*onep_screen_w,78*onep_screen_w,topdis+16*onep_screen_w);
        path=new Path();
        path.moveTo(70*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(74*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(78*onep_screen_w,topdis+8*onep_screen_w);
        path.close();
        regions[2]=new Touchregion(path);
        regions[2].typename="huangsanxia";

        rectfs[3]=new RectF(54*onep_screen_w,topdis+16*onep_screen_w,70*onep_screen_w,topdis+24*onep_screen_w);
        path=new Path();
        path.moveTo(58*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(54*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(70*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+16*onep_screen_w);
        path.close();
        regions[3]=new Touchregion(path);
        regions[3].typename="ti";

        rectfs[4]=new RectF(54*onep_screen_w,topdis+24*onep_screen_w,62*onep_screen_w,topdis+32*onep_screen_w);
        path=new Path();
        path.moveTo(54*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis+32*onep_screen_w);
        path.lineTo(62*onep_screen_w,topdis+24*onep_screen_w);
        path.close();
        regions[4]=new Touchregion(path);
        regions[4].typename="fensanxia";

              rectfs[5]=new RectF(62*onep_screen_w,topdis+24*onep_screen_w,70*onep_screen_w,topdis+32*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+32*onep_screen_w);
        path.lineTo(70*onep_screen_w,topdis+24*onep_screen_w);
        path.close();
        regions[5]=new Touchregion(path);
        regions[5].typename="fensanxia";

        rectfs[6]=new RectF(54*onep_screen_w,topdis,62*onep_screen_w,topdis+8*onep_screen_w);
        path=new Path();
        path.moveTo(54*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis);
        path.lineTo(62*onep_screen_w,topdis+8*onep_screen_w);
        path.close();
        regions[6]=new Touchregion(path);
        regions[6].typename="lansanshang";

        rectfs[7]=new RectF(58*onep_screen_w,topdis,66*onep_screen_w,topdis+8*onep_screen_w);
        path=new Path();
        path.moveTo(58*onep_screen_w,topdis);
        path.lineTo(66*onep_screen_w,topdis);
        path.lineTo(62*onep_screen_w,topdis+8*onep_screen_w);
        path.close();
        regions[7]=new Touchregion(path);
        regions[7].typename="lvsanxia";

        rectfs[8]=new RectF(62*onep_screen_w,topdis,70*onep_screen_w,topdis+8*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis);
        path.lineTo(70*onep_screen_w,topdis+8*onep_screen_w);
        path.close();
        regions[8]=new Touchregion(path);
        regions[8].typename="fensanshang";

        rectfs[9]=new RectF(66*onep_screen_w,topdis,74*onep_screen_w,topdis+8*onep_screen_w);
        path=new Path();
        path.moveTo(66*onep_screen_w,topdis);
        path.lineTo(74*onep_screen_w,topdis);
        path.lineTo(70*onep_screen_w,topdis+8*onep_screen_w);
        path.close();
        regions[9]=new Touchregion(path);
        regions[9].typename="lansanxia";

        rectfs[10]=new RectF(54*onep_screen_w,topdis+8*onep_screen_w,62*onep_screen_w,topdis+16*onep_screen_w);
        path=new Path();
        path.moveTo(54*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(62*onep_screen_w,topdis+8*onep_screen_w);
        path.close();
        regions[10]=new Touchregion(path);
        regions[10].typename="chengsanxia";

        rectfs[11]=new RectF(58*onep_screen_w,topdis+8*onep_screen_w,66*onep_screen_w,topdis+16*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+8*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+16*onep_screen_w);
        path.close();
        regions[11]=new Touchregion(path);
        regions[11].typename="huangsanshang";

        rectfs[12]=new RectF(58*onep_screen_w,topdis+16*onep_screen_w,66*onep_screen_w,topdis+24*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+16*onep_screen_w);
        path.close();
        regions[12]=new Touchregion(path);
        regions[12].typename="hongsanxia";

        rectfs[13]=new RectF(62*onep_screen_w,topdis+16*onep_screen_w,70*onep_screen_w,topdis+24*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+16*onep_screen_w);
        path.lineTo(70*onep_screen_w,topdis+24*onep_screen_w);
        path.close();
        regions[13]=new Touchregion(path);
        regions[13].typename="fensanshang";

        rectfs[14]=new RectF(62*onep_screen_w,topdis+24*onep_screen_w,70*onep_screen_w,topdis+32*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+32*onep_screen_w);
        path.lineTo(70*onep_screen_w,topdis+24*onep_screen_w);
        path.close();
        regions[14]=new Touchregion(path);
        regions[14].typename="chengsanxia";

        rectfs[15]=new RectF(58*onep_screen_w,topdis+24*onep_screen_w,66*onep_screen_w,topdis+32*onep_screen_w);
        path=new Path();
        path.moveTo(62*onep_screen_w,topdis+24*onep_screen_w);
        path.lineTo(58*onep_screen_w,topdis+32*onep_screen_w);
        path.lineTo(66*onep_screen_w,topdis+32*onep_screen_w);
        path.close();
        regions[15]=new Touchregion(path);
        regions[15].typename="huangsanshang";
        //12个数字选择区域
        chooserectfs= new RectF[12];
        for(int i=0;i<5;i++){
            chooserectfs[i]=new RectF((18*i+10)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (18*i+20)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);

        }
        for(int i=0;i<7;i++){
            chooserectfs[i+5]=new RectF((12*i+8)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (12*i+18)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[12];

        for(int i=0;i<5;i++){
            path = new Path();
            path.addRect(chooserectfs[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
            chooseregions[i].point_x=(int)((18*i+15)*onep_screen_w);
            chooseregions[i].point_y=(int)(85*onep_screen_h);
        }
        for(int i=0;i<7;i++){
            path = new Path();
            path.addRect(chooserectfs[i+5], Path.Direction.CW);
            chooseregions[i+5]=new Touchregion(path);
            chooseregions[i+5].point_x=(int)((12*i+13)*onep_screen_w);
            chooseregions[i+5].point_y=(int)(85*onep_screen_h);
        }
        chooseregions[0].typename="fensanxia";
        chooseregions[1].typename="ti";
        chooseregions[2].typename="liu";
        chooseregions[3].typename="huangsanxia";
        chooseregions[4].typename="si";
        chooseregions[5].typename="huangsanshang";
        chooseregions[6].typename="lansanshang";
        chooseregions[7].typename="lansanxia";
        chooseregions[8].typename="lvsanxia";
        chooseregions[9].typename="fensanshang";
        chooseregions[10].typename="hongsanxia";
        chooseregions[11].typename="chengsanxia";
        //2个第二区域
        regions2=new Touchregion[2];
        rectfs2= new RectF[2];
        for(int i=0;i<2;i++){
            rectfs2[i]=new RectF((20+13*i) * onep_screen_w, 40 * onep_screen_h ,
                    (32+13*i)* onep_screen_w , 40 * onep_screen_h +12 * onep_screen_w);

        }
        for(int i=0;i<2;i++){
            path = new Path();
            path.addRect(rectfs2[i], Path.Direction.CW);
            regions2[i]=new Touchregion(path);
        }
        for(int i=0;i<regions.length;i++){
            regions[i].isright=false;
        }
        regions2[0].istouch=true;
        getrightpic(0);
    }
    public void getrightpic(int index){
        regionindex=index;
        squresum=0;
        isshadow=false;
        checkpaint.setAlpha(0);
        rightcheckpaint.setAlpha(0);
        for(int i=0;i<regions.length;i++){
            regions[i].istouch=false;
            regions[i].isright=false;
        }

    }
    public void init(Display display) {
        super.init(display);
    }
    public  Bitmap getBitmapbyname(String name){
            switch (name){
                case "huangsanxia": return huangsanxia;
                case "fensanxia": return fensanxia;
                case "lansanshang": return lansanshang;
                case "lansanxia": return lansanxia;
                case "lvsanxia": return lvsanxia;
                case "hongsanxia": return hongsanxia;
                case "chengsanxia": return chengsanxia;
                case "fensanshang": return fensanshang;
                case "huangsanshang": return huangsanshang;
                case "ti": return ti;
                case "si": return si;
                case "liu": return liu;
            }
        return huangsanxia;
    }
    Bitmap c1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6number1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6number1)).getBitmap() : null;
    Bitmap c2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6number2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6number2)).getBitmap() : null;
    Bitmap huangsanxia = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose1)).getBitmap() : null;
    Bitmap fensanxia = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose2)).getBitmap() : null;
    Bitmap lansanshang = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose3)).getBitmap() : null;
    Bitmap lansanxia = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose4)).getBitmap() : null;
    Bitmap lvsanxia = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose5)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose5)).getBitmap() : null;
    Bitmap hongsanxia = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose6)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose6)).getBitmap() : null;
    Bitmap chengsanxia = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose7)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose7)).getBitmap() : null;
    Bitmap fensanshang = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose8)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose8)).getBitmap() : null;
    Bitmap huangsanshang = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose9)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose9)).getBitmap() : null;
    Bitmap ti = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose10)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose10)).getBitmap() : null;
    Bitmap si = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose11)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose11)).getBitmap() : null;
    Bitmap liu = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.s4page6imgchoose12)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.s4page6imgchoose12)).getBitmap() : null;

    public void onDraw(Canvas c) {
        //区域判定,选择区域判断
        if (isdown) {
            if(regionindex==0) {
                for (int i = 0; i < 6; i++) {
                    //计算控制点的边界
                    regions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //选中类型需要与当前正确类型相同
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        for (int j = 0; j < regions.length; j++) {
                            regions[j].istouch = false;
                        }
                        regions[i].istouch = true;
                        isdown = false;
                        isshadow = false;
                        checkpaint.setAlpha(0);
                        rightcheckpaint.setAlpha(0);
                        if (!regions[i].isright) {
                            Log.i("zh", "right touch"+i+regions[i].typename);
                            curreindex = i;
                            isshadow = true;
                            shadowflag = false;
                            curretypename = regions[i].typename;
                            curreid = regions[i].id;
                        } else {
                        }
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        break;
                    }
                }
            }
            if(regionindex==1)  {
                for (int i = 6; i < 16; i++) {
                    //计算控制点的边界
                    regions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //选中类型需要与当前正确类型相同
                    if (re.contains((int) Point_x, (int) Point_y)) {
                        for (int j = 0; j < regions.length; j++) {
                            regions[j].istouch = false;
                        }
                        regions[i].istouch = true;
                        isdown = false;
                        isshadow = false;
                        checkpaint.setAlpha(0);
                        rightcheckpaint.setAlpha(0);
                        if (!regions[i].isright) {
                            Log.i("zh", "right touch"+i+regions[i].typename);
                            curreindex = i;
                            isshadow = true;
                            shadowflag = false;
                            curretypename = regions[i].typename;
                            curreid = regions[i].id;
                        } else {
                        }
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        break;
                    }
                }
            }
            for (int i = 0; i < regions2.length; i++) {
                //计算控制点的边界
                regions2[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(regions2[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //选中类型需要与当前正确类型相同
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isdown = false;
                    regions2[i].istouch = true;
                    for (int j = 0; j < regions2.length; j++) {
                        if (j != i)
                            regions2[j].istouch = false;
                    }
                    getrightpic(i);
                    if (DataUtil.isvoiceplay)
                        DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                    break;
                }
            }
            if (isshadow && isdown) {
                isdown = false;
                if(regionindex==0) {
                    for (int i = 0; i <5; i++) {
                        //计算控制点的边界
                        chooseregions[i].path.computeBounds(r, true);
                        //设置区域路径和剪辑描述的区域
                        re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                        //点击到正确区域
                        if (re.contains((int) Point_x, (int) Point_y)) {
                            Log.i("zh", "chooseregion typename" + chooseregions[i].typename + " curretypename" + curretypename);
                            if (chooseregions[i].typename == curretypename && !regions[curreindex].isright) {
                                squresum++;
                                Log.i("zh", "squrenum: " + squresum);
                                isright = true;
                                regions[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                                if ((squresum == 6 && regionindex == 0) || (squresum == 10 && regionindex == 1) && DataUtil.isvoiceplay) {
                                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                }
                                if ((squresum == 6 && regionindex == 0) || (squresum == 10 && regionindex == 1)) {
                                    if (regions2[regionindex].isright == false) {
                                        if (getpicflag[regionindex]) {
                                            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                            getpicflag[regionindex] = false;
                                            getShotPic(regionindex);
                                            this.destroyDrawingCache();
                                        }
                                        pic_num--;
                                        regions2[regionindex].isright = true;
                                    }
                                }
                            } else if (chooseregions[i].typename != curretypename && !regions[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                    }
                }
                if(regionindex==1) {
                    for (int i = 5; i <12; i++) {
                        //计算控制点的边界
                        chooseregions[i].path.computeBounds(r, true);
                        //设置区域路径和剪辑描述的区域
                        re.setPath(chooseregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                        //点击到正确区域
                        if (re.contains((int) Point_x, (int) Point_y)) {
                            Log.i("zh", "chooseregion typename" + chooseregions[i].typename + " curretypename" + curretypename);
                            if (chooseregions[i].typename == curretypename && !regions[curreindex].isright) {
                                Log.i("zh", "squrenum: " + squresum);
                                squresum++;
                                isright = true;
                                regions[curreindex].isright = true;
                                getright(chooseregions[i].point_x, chooseregions[i].point_y);
                                if ((squresum == 6 && regionindex == 0) || (squresum == 10 && regionindex == 1) && DataUtil.isvoiceplay) {
                                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                }
                                if ((squresum == 6 && regionindex == 0) || (squresum == 10 && regionindex == 1)) {
                                    if (regions2[regionindex].isright == false) {
                                        if (getpicflag[regionindex]) {
                                            DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                                            getpicflag[regionindex] = false;
                                            getShotPic(regionindex);
                                            this.destroyDrawingCache();
                                        }
                                        pic_num--;
                                        regions2[regionindex].isright = true;
                                    }
                                }
                            } else if (chooseregions[i].typename != curretypename && !regions[curreindex].isright) {
                                isright = false;
                                getwrong(chooseregions[i].point_x, chooseregions[i].point_y);
                            }
                        }
                    }
                }
            }
        }

        //画外框
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        c.drawPath(rectpath,paint);
        //画图形
        for(int i=0;i<6;i++){
            if(regions[i].isright)
                c.drawBitmap(getBitmapbyname(regions[i].typename),null,rectfs[i],paint);
        }
        for(int i=6;i<16;i++){
            if(regions[i].isright)
            c.drawBitmap(getBitmapbyname(regions[i].typename),null,rectfs[i],paint);
        }

        //左侧选择图形
        c.drawBitmap(c1,null,new RectF(22*onep_screen_w,40*onep_screen_h-14*onep_screen_w,30*onep_screen_w,
                40*onep_screen_h-2*onep_screen_w),paint);
        c.drawBitmap(c2,null,new RectF(35*onep_screen_w,40*onep_screen_h-14*onep_screen_w,43*onep_screen_w,
                40*onep_screen_h-2*onep_screen_w),paint);
        //截图区域
        for(int i=0;i<regions2.length;i++){
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(2);
            c.drawRect(rectfs2[i],paint);
            if(regions2[i].istouch){
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawRect(rectfs2[i],paint);
                paint.setAlpha(255);
            }
            if(regions2[i].isright){
                if(getpicflag[i]==false&&shotpics[i]!=null){
                    if(getpiccut[i]==false) {
                        shotpics[i] = Bitmap.createBitmap(shotpics[i], (int) (50 * onep_screen_w), (int) (10 * onep_screen_h), (int) (32 * onep_screen_w), (int) (32 * onep_screen_w));
                        getpiccut[i]=true;
                    }
                    c.drawBitmap(shotpics[i], null, rectfs2[i], paint);
                }
            }
            else {
                c.drawBitmap(GameUtil.getCommonBitmapbyname("wenhao"),null,rectfs2[i],paint);
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
            shadowrect = new Rect(0, Screen_h - (int) (shadowprogress * 25 * onep_screen_h), (int)(1.2*Screen_w), Screen_h);
            c.drawRect(shadowrect,shadowpaint);
        }
        //下方选项
        if(isshadow){
            if(regionindex==0){
                for (int i = 0; i < 5; i++) {
                    c.drawBitmap(getBitmapbyname(chooseregions[i].typename), null, chooserectfs[i], paint);
                }
            }
            if(regionindex==1) {
                for (int i = 5; i < 12; i++) {
                    c.drawBitmap(getBitmapbyname(chooseregions[i].typename), null, chooserectfs[i], paint);
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
