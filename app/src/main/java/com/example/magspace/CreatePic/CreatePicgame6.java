package com.example.magspace.CreatePic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;
import android.view.Display;
import android.widget.Switch;

import com.example.magspace.Bean.Point;
import com.example.magspace.Bean.Touchregion;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class CreatePicgame6 extends CreatePicgameFather {
    public static CreatePicgame6 instance;
    public int res;
    public static  CreatePicgame6 getInstance(Context context, Display display){
        if (instance == null) {
            instance=new CreatePicgame6(context, display);
        }
        return instance;
    }

    public CreatePicgame6(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        super.init(display);
        shadowanimator = ObjectAnimator.ofFloat(CreatePicgame6.this, "shadowprogress", 0.0f, 1.0f);
        shadowanimator.setDuration(300);
        index=6;
        squresum=0;

        //15个判断区
        regions=new Touchregion[15];
        resnamerect=new String[3][5];
        rectfs =new RectF[3][5];
        resultrect=new int[3][5];
        for(int i=0;i<3;i++) {
            for(int j=0;j<5;j++) {
                rectfs[i][j]=new RectF((30+j*8) * onep_screen_w, 25 * onep_screen_h +i*8 * onep_screen_w,
                        (38+j*8)* onep_screen_w, 25 * onep_screen_h +(i+1)* 8 * onep_screen_w);
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                path = new Path();
                resultrect[i][j]=0;
                path.addRect(rectfs[i][j], Path.Direction.CW);
                regions[i*5+j]=new Touchregion(path,i,j);
            }
        }
        //4个数字选择区域
        chooserectf= new RectF[4];
        for(int i=0;i<4;i++){
            chooserectf[i]=new RectF((20*i+15)*onep_screen_w,85*onep_screen_h-5*onep_screen_w,
                    (20*i+25)*onep_screen_w,85*onep_screen_h+5*onep_screen_w);

        }
        chooseregions=new Touchregion[4];

        for(int i=0;i<4;i++){
            path = new Path();
            path.addRect(chooserectf[i], Path.Direction.CW);
            chooseregions[i]=new Touchregion(path);
        }
        chooseregions[0].typename="red";
        chooseregions[1].typename="orange";
        chooseregions[2].typename="green";
        chooseregions[3].typename="blue";
    }
    public void addpoint(int index,String typename){
        int row=index/5;
        int col=index%5;
        Point point=new Point(row,col);
        switch(typename){
            case "red": redpoint.add(point);break;
            case "orange": orangepoint.add(point);break;
            case "green": greenpoint.add(point);break;
            case "blue": bluepoint.add(point);break;
        }
    }
    public void delpoint(int index){
        String oldtypename;
        int row=index/5;
        int col=index%5;
        oldtypename=resnamerect[row][col];
        switch(oldtypename){
            case "red":
                for(int i=0;i<redpoint.size();i++){
                    if(redpoint.get(i).row==row&&redpoint.get(i).col==col){
                        redpoint.remove(i);
                    }
                }
                break;
            case "orange":
                for(int i=0;i<orangepoint.size();i++){
                    if(orangepoint.get(i).row==row&&orangepoint.get(i).col==col){
                        orangepoint.remove(i);
                    }
                }
                break;
            case "green":
                for(int i=0;i<greenpoint.size();i++){
                    if(greenpoint.get(i).row==row&&greenpoint.get(i).col==col){
                        greenpoint.remove(i);
                    }
                }
                break;
            case "blue":
                for(int i=0;i<bluepoint.size();i++){
                    if(bluepoint.get(i).row==row&&bluepoint.get(i).col==col){
                        bluepoint.remove(i);
                    }
                }
                break;
        }
    }
    public boolean isright(ArrayList<Point> p) {
        if(p.size()==1){
            return true;
        }
        if(p.size()==4){
            int row=5,col=5;
            for(int i=0;i<4;i++){
                if(p.get(i).row<row){
                    row=p.get(i).row;
                }
                if(p.get(i).col<col){
                    col=p.get(i).col;
                }
            }
            if(row==2||col==4) return false;
            if(resnamerect[row][col].equals(resnamerect[row+1][col])&&resnamerect[row+1][col].equals(resnamerect[row][col+1])&&
                    resnamerect[row][col+1].equals(resnamerect[row+1][col+1])){
                return true;
            }
        }
        if(p.size()==9){
            int row=5,col=5;
            for(int i=0;i<9;i++){
                if(p.get(i).row<row){
                    row=p.get(i).row;
                }
                if(p.get(i).col<col){
                    col=p.get(i).col;
                }
            }
            if(row!=0||col>2) return false;
            if(resnamerect[row][col].equals(resnamerect[row+1][col])&&resnamerect[row+1][col].equals(resnamerect[row][col+1])&&
                    resnamerect[row][col+1].equals(resnamerect[row+1][col+1])&&resnamerect[row+1][col+1].equals(resnamerect[row+2][col])
            &&resnamerect[row+2][col].equals(resnamerect[row+2][col+1])&&resnamerect[row+2][col+1].equals(resnamerect[row+2][col+2])&&
                    resnamerect[row+2][col+2].equals(resnamerect[row+1][col+2])&&resnamerect[row+1][col+2].equals(resnamerect[row][col+2])){
                return true;
            }
        }
        return  false;
    }
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
                        curreindex=i;
                        isshadow=true;
                        shadowflag=false;
                        curretypename=regions[i].typename;
                        curreid=regions[i].id;
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
                        //已经有颜色块
                        if(regions[curreindex].isright){
                            delpoint(curreindex);
                            addpoint(curreindex,chooseregions[i].typename);
                            resnamerect[curreindex/5][curreindex%5]=chooseregions[i].typename;
                            regions[curreindex].typename=chooseregions[i].typename;
                        }
                       else{
                           addpoint(curreindex,chooseregions[i].typename);
                            regions[curreindex].isright=true;
                            squresum++;
                            resnamerect[curreindex/5][curreindex%5]=chooseregions[i].typename;
                            regions[curreindex].typename=chooseregions[i].typename;
                        }
                        if(squresum==15){
                            gettypeflag=true;
                        }
                        if (DataUtil.isvoiceplay) {
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        }

                    }
                }
            }
        }
        //判断对错
        if(gettypeflag&&squresum==15){
            gettypeflag=false;
            Log.i("zh", redpoint.size()+" "+orangepoint.size()+" "+greenpoint.size()+" "+bluepoint.size());
            if(isright(redpoint)&&isright(orangepoint)&&isright(greenpoint)&&isright(bluepoint)) {
                if (DataUtil.isvoiceplay) {
                    DataUtil.soundPool.play(4, 1, 1, 1, 0, 1);
                }
            }
        }
        //判断区域
        for(int i=0;i<3;i++) {
            for(int j=0;j<5;j++) {
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
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                if(regions[5*i+j].typename!=null)
                    c.drawBitmap(GameUtil.getCommonBitmapbyname(regions[5*i+j].typename),null,rectfs[i][j],paint);
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
        if(isshadow){
            for(int i=0;i<chooseregions.length;i++){
                c.drawBitmap(GameUtil.getCommonBitmapbyname(chooseregions[i].getTypename()), null, chooserectf[i], paint);
            }
        }
    }
}
