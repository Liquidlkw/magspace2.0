package com.example.magspace.Bean;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Typeface;
import android.util.Log;

public class Touchregion {


    //路径
    public Path path,anopath;
    //画笔
    public Paint paint;
    //数字笔
    public Paint numpaint;
    //长度
    public float length;
    //是否已经被触发
    public boolean istouch;
    //是否正确，如认识数字第一关
    public boolean isright;
    //动画播放器
   public ObjectAnimator animator;
   //类型
    public String typename;
    //剩余选择数量
    public int leftnum;
    //数组横竖坐标
    public int rown,coln;

    //最大剩余选择数量,用于reset
    public int maxnum;
    //备用存储点
    public int point_x,point_y;
    //备用数字
    public int id;

//判断区域
    public Touchregion(Path path){
        this.istouch=false;
        this.paint=new Paint();
        this.paint.setColor(Color.GREEN) ;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(6);
//        this.paint.setAlpha(0);
        this.path=path;
        PathMeasure measure = new PathMeasure(path, false);
        this.setLength(measure.getLength());

    }
    //双重路径
    public Touchregion(Path path ,Path anopath){
        this.istouch=false;
        this.paint=new Paint();
        this.paint.setColor(Color.RED) ;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(6);
//        this.paint.setAlpha(0);
        this.path=path;
        this.anopath=anopath;
        PathMeasure measure = new PathMeasure(anopath, false);
        this.setLength(measure.getLength());

    }
    //特殊判断区域，如创造图形第二关
    public Touchregion(Path path,int row,int col){
        this.istouch=false;
        this.paint=new Paint();
        this.paint.setColor(Color.GREEN) ;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(6);
//        this.paint.setAlpha(0);
        this.path=path;
        this.rown=row;
        this.coln=col;
        PathMeasure measure = new PathMeasure(path, false);
        this.setLength(measure.getLength());

    }
    //选择区域
    public Touchregion(Path path,String typename,int leftnum){
        this.istouch=false;
        this.paint=new Paint();
        this.paint.setColor(Color.GRAY) ;
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAlpha(100);
        this.numpaint=new Paint();
        this.numpaint.setColor(Color.BLACK);
        this.numpaint.setStyle(Paint.Style.FILL);
        this.numpaint.setTextSize(20);

//        this.paint.setAlpha(0);
        this.typename=typename;
        this.leftnum=leftnum;
        this.maxnum=leftnum;
        this.path=path;
        PathMeasure measure = new PathMeasure(path, false);
        this.setLength(measure.getLength());

    }
    //数字区域
    public Touchregion(Path path,int id){
        this.istouch=false;
       this.path=path;
       this.id=id;
    }
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public boolean isIstouch() {
        return istouch;
    }

    public void setIstouch(boolean istouch) {
        this.istouch = istouch;
    }

    public ObjectAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(ObjectAnimator animator) {
        this.animator = animator;
    }
    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
    public int getLeftnum() {
        return leftnum;
    }

    public void setLeftnum(int leftnum) {
        this.leftnum = leftnum;
    }

    public Paint getNumpaint() {
        return numpaint;
    }

    public void setNumpaint(Paint numpaint) {
        this.numpaint = numpaint;
    }

    public int getPoint_x() {
        return point_x;
    }

    public void setPoint_x(int point_x) {
        this.point_x = point_x;
    }

    public int getPoint_y() {
        return point_y;
    }

    public void setPoint_y(int point_y) {
        this.point_y = point_y;
    }
    public int getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(int maxnum) {
        this.maxnum = maxnum;
    }

}
