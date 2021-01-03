package com.example.magspace.Bean;

import android.graphics.Bitmap;

public class Question {
    //是否对
    public boolean isright;
    //图片
    public Bitmap pic;
    //题目
    public String qus;
    //选项
    public String choose[];
    //选项下标
    public int index;
    //答案下标
    public int rightindex;
    //分值
    public int score;

    public Question(String qus,String choose[],int rightindex,Bitmap pic,int score){
        this.qus=qus;
        this.choose=choose;
        this.rightindex=rightindex;
        this.pic=pic;
        this.score=score;
        this.isright=false;
        this.index=-1;
    }
}
