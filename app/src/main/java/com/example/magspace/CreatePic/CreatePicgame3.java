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
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;

import com.example.magspace.Bean.Point;
import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class CreatePicgame3 extends CreatePicgameFather {
    public static CreatePicgame3 instance;
    public static  CreatePicgame3 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new CreatePicgame3(context, display);
        }
        return instance;
    }

    public CreatePicgame3(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        super.init(display);
        index=3;
        isneedchoose = true;
        isneedcut=true;
        squresum=0;
        gettypeflag=false;
        res=0;
        getpicflag=new boolean[]{true,true,true,true,true};
        getpiccut=new boolean[]{false,false,false,false,false};
        shotpics=new Bitmap[5];
        regions=new Touchregion[8];
//        rects =new Rect[2][3];
        chooserectf= new RectF[4];
        minrectf =new RectF[4];
        //红黄橙蓝
        leftcolor = new int[]{1,1,1,1};
        for(int i=0;i<4;i++){
            chooserectf[i]=new RectF((20*onep_screen_w),(15*onep_screen_h+i*10*onep_screen_w),
                   (30*onep_screen_w),(15*onep_screen_h+(i+1)*10*onep_screen_w));
            minrectf[i]=new RectF((22*onep_screen_w),(15*onep_screen_h+i*10*onep_screen_w+2*onep_screen_w),
                    (28*onep_screen_w),(15*onep_screen_h+(i+1)*10*onep_screen_w-2*onep_screen_w));
        }
        rectfs =new RectF[2][4];
        resultrect=new int[2][4];
        for(int i=0;i<2;i++) {
            for(int j=0;j<4;j++) {
                rectfs[i][j]=new RectF((40+j*10) * onep_screen_w, 20 * onep_screen_h +i*10 * onep_screen_w,
                        (50+j*10)* onep_screen_w, 20 * onep_screen_h +(i+1)* 10 * onep_screen_w);
            }
        }
        //八个判定区域
        for(int i=0;i<2;i++){
            for(int j=0;j<4;j++){
                path = new Path();
                resultrect[i][j]=0;
                path.addRect(rectfs[i][j], Path.Direction.CW);
                regions[i*4+j]=new Touchregion(path,i,j);
            }
        }

        chooseregions= new Touchregion[4];

        redpoint.add(new Point(0,0));
        yellowpoint.add(new Point(0,0));
        greenpoint.add(new Point(0,0));
        bluepoint.add(new Point(0,0));
        //四个选择区域
        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectf[i],Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
        }
        chooseregions[0].typename="red";
        chooseregions[1].typename="yellow";
        chooseregions[2].typename="green";
        chooseregions[3].typename="blue";
        curretypename= chooseregions[0].typename;
        chooseregions[0].istouch=true;
    }
    //截图类型
    public int gettype(){
        int sum1=0,sum2=0;
        respoint.clear();
        respoint.add(redpoint.get(0));
        respoint.add(yellowpoint.get(0));
        respoint.add(greenpoint.get(0));
        respoint.add(bluepoint.get(0));

        for(int i=0;i<4;i++){
            if(respoint.get(i).row==0)
                sum1++;
            else
                sum2++;
        }
        //一条状
        if(sum1==4||sum2==4){
            return 1;
        }
        //上3
        else if(sum1==3){
            int min=9,max=-1,ano=-1;
            for(int i=0;i<4;i++){
                    if(respoint.get(i).row==0){
                        if(respoint.get(i).col<min){
                            min=respoint.get(i).col;
                        }
                        if(respoint.get(i).col>max){
                            max=respoint.get(i).col;
                        }
                    }
                    else{
                        ano=respoint.get(i).col;
                    }
                    //左边
                    if(max-min==2&&(ano==max||ano==min)){
                        return 2;
                    }
                    //中间
                     if(max-min==2&&(ano==max||ano==min+1)){
                        return 3;
                     }
            }
        }
        //下3
        else if(sum2==3){
            int min=9,max=-1,ano=-1;
            for(int i=0;i<4;i++){
                if(respoint.get(i).row==1){
                    if(respoint.get(i).col<min){
                        min=respoint.get(i).col;
                    }
                    if(respoint.get(i).col>max){
                        max=respoint.get(i).col;
                    }
                }
                else{
                    ano=respoint.get(i).col;
                }
                //左边
                if(max-min==2&&(ano==max||ano==min)){
                    return 2;
                }
                //中间
                if(max-min==2&&(ano==max||ano==min+1)){
                    return 3;
                }
            }
        }
        if(sum1==2){
            int min1=9,max1=-1, min2=9,max2=-1;
            for(int i=0;i<4;i++){
                if(respoint.get(i).row==0){
                    if(respoint.get(i).col<min1){
                        min1=respoint.get(i).col;
                    }
                    if(respoint.get(i).col>max1){
                        max1=respoint.get(i).col;
                    }
                }
                if(respoint.get(i).row==1){
                    if(respoint.get(i).col<min2){
                        min2=respoint.get(i).col;
                    }
                    if(respoint.get(i).col>max2){
                        max2=respoint.get(i).col;
                    }
                }
                if(min1==min2&&max1==max2){
                    return 4;
                }
                if((max2==(min1+2)&&max1==min2)||(max1==(min2+2)&&max2==min1)){
                    return 5;
                }
            }
        }
        return 0;
    }
    public void onDraw(Canvas c) {
        //八个矩形
        for(int i=0;i<2;i++) {
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

        //截屏展示区
        paint.setTextSize(20*fontrite);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        for(int i=0;i<5;i++){
            c.drawRect(43*onep_screen_w+(7*i*onep_screen_w),80*onep_screen_h-3*onep_screen_w,49*onep_screen_w+(7*i*onep_screen_w),
                    80*onep_screen_h+3*onep_screen_w,paint);
        }

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
                if (re.contains((int) Point_x, (int) Point_y)) {
                    isright = true;
                    isdown = false;
                    Log.i("zh", "contain");
                    //没有方块
                    if(regions[i].typename==null&&leftcolor[leftindex]!=0){
                        Log.i("zh", "no");
                        regions[i].typename=curretypename;
                        leftcolor[leftindex]--;
                        squresum++;
                        if(curretypename=="red"){
                            redpoint.get(0).row=i/4;
                            redpoint.get(0).col=i%4;
                        }
                        if(curretypename=="yellow"){
                            yellowpoint.get(0).row=i/4;
                            yellowpoint.get(0).col=i%4;
                        }
                        if(curretypename=="green"){
                            greenpoint.get(0).row=i/4;
                            greenpoint.get(0).col=i%4;
                        }
                        if(curretypename=="blue"){
                            bluepoint.get(0).row=i/4;
                            bluepoint.get(0).col=i%4;
                        }
                    }
                    //相同块
                    else if(regions[i].typename==curretypename){
                        Log.i("zh", "same");
                        regions[i].typename=null;
                        squresum--;
                        leftcolor[leftindex]++;
                    }
                    if(squresum==4){
                        gettypeflag=true;
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
                        if(curretypename=="red")
                            leftindex=0;
                        if(curretypename=="yellow")
                            leftindex=1;
                        if(curretypename=="green")
                            leftindex=2;
                        if(curretypename=="blue")
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
        //判定区域
        for(int i=0;i<2;i++){
            for(int j=0;j<4;j++){
                if(regions[4*i+j].typename!=null)
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[4*i+j].typename),null,rectfs[i][j],paint);
            }
        }
        //选择区域

            for (int i = 0; i < chooseregions.length; i++) {
                c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].typename),null,minrectf[i],paint);
            }
        //判断是否达到要求
        if(squresum==4&&gettypeflag) {
            gettypeflag=false;
            res=gettype();
            Log.i("zh", "onDraw: "+res);
            if(res!=0) {
                if (getpicflag[res - 1]) {
                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                    getpicflag[res - 1] = false;
                    getShotPic(res - 1);
                    this.destroyDrawingCache();
                }
            }
        }
        //画截图
        for(int i=0;i<5;i++){
            if(getpicflag[i]==false&&shotpics[i]!=null){
            if(getpiccut[i]==false) {
                shotpics[i] = Bitmap.createBitmap(shotpics[i], (int) (40 * onep_screen_w), (int) (20 * onep_screen_h), (int) (40 * onep_screen_w), (int) (20 * onep_screen_w));
                getpiccut[i]=true;
            }
            c.drawBitmap(shotpics[i], null, new RectF(43.5f*onep_screen_w+(7*i*onep_screen_w),80*onep_screen_h-2.5f*onep_screen_w,48.5f*onep_screen_w+(7*i*onep_screen_w),
                    80*onep_screen_h+2.5f*onep_screen_w), paint);
        }
        }
//        if(getpicflag[0]==false&&shotpics[0]!=null){
//            if(getpiccut[0]==false) {
//                shotpics[0] = Bitmap.createBitmap(shotpics[0], (int) (40 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
//                getpiccut[0]=true;
//            }
//            c.drawBitmap(shotpics[0], null, new Rect((int) (20.5*onep_screen_w), (int) (35*onep_screen_h-2.5*onep_screen_w),
//                    (int) (25.5*onep_screen_w), (int) (35*onep_screen_h+2.5*onep_screen_w)), paint);
//        }
//        if(getpicflag[1]==false&&shotpics[1]!=null){
//            if(getpiccut[1]==false) {
//                shotpics[1] = Bitmap.createBitmap(shotpics[1], (int) (40 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
//                getpiccut[1]=true;
//            }
//            c.drawBitmap(shotpics[1], null, new Rect((int) (27.5*onep_screen_w), (int) (35*onep_screen_h-2.5*onep_screen_w),
//                    (int) (32.5*onep_screen_w), (int) (35*onep_screen_h+2.5*onep_screen_w)), paint);
//        }
//        if(getpicflag[2]==false&&shotpics[2]!=null){
//            if(getpiccut[2]==false) {
//                shotpics[2] = Bitmap.createBitmap(shotpics[2], (int) (40 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
//                getpiccut[2]=true;
//            }
//            c.drawBitmap(shotpics[2], null, new Rect((int) (20.5*onep_screen_w), (int) (50*onep_screen_h-2.5*onep_screen_w),
//                    (int) (25.5*onep_screen_w), (int) (50*onep_screen_h+2.5*onep_screen_w)), paint);
//        }
//        if(getpicflag[3]==false&&shotpics[3]!=null){
//            if(getpiccut[3]==false) {
//                shotpics[3] = Bitmap.createBitmap(shotpics[3], (int) (40 * onep_screen_w), (int) (30 * onep_screen_h), (int) (30 * onep_screen_w), (int) (20 * onep_screen_w));
//                getpiccut[3]=true;
//            }
//            c.drawBitmap(shotpics[3], null, new Rect((int) (27.5*onep_screen_w), (int) (50*onep_screen_h-2.5*onep_screen_w),
//                    (int) (32.5*onep_screen_w), (int) (50*onep_screen_h+2.5*onep_screen_w)), paint);
//        }
    }
}
