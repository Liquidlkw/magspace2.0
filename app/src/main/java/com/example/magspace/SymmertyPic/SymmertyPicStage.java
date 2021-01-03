package com.example.magspace.SymmertyPic;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.magspace.Base.MathFirstBaseActivity;
import com.example.magspace.MyApplication;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class SymmertyPicStage extends MathFirstBaseActivity implements View.OnTouchListener{
    public SymmertypicgameFather currgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameUtil.addSysmertyPicgames(Sysmertypicgame1.getInstance(this, display));
        GameUtil.addSysmertyPicgames(Sysmertypicgame2.getInstance(this, display));
        GameUtil.addSysmertyPicgames(Sysmertypicgame3.getInstance(this, display));
        GameUtil.addSysmertyPicgames(Sysmertypicgame4.getInstance(this, display));
        GameUtil.addSysmertyPicgames(Sysmertypicgame5.getInstance(this, display));
        GameUtil.addSysmertyPicgames(Sysmertypicgame6.getInstance(this, display));
        AnimUtil.setShowAnimation(GameUtil.getSysmertyPicgame(1),400);
        initGame(GameUtil.getSysmertyPicgame(1),leftnumbers[currentindex-1],1);

        super.mArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                AnimUtil.setHideAnimation(currgame,400);
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        currgame.resetPath();
                        currgame.resetflag=true;
                        AnimUtil.setShowAnimation(GameUtil.getSysmertyPicgame(currentindex+1),400);
                        initGame(GameUtil.getSysmertyPicgame(currentindex+1),leftnumbers[currentindex],currentindex+1);
                    }}, 400);

            }
        });

        super.mArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                AnimUtil.setHideAnimation(currgame,400);
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        currgame.resetPath();
                        currgame.resetflag=true;
                        AnimUtil.setShowAnimation(GameUtil.getSysmertyPicgame(currentindex-1),400);
                        initGame(GameUtil.getSysmertyPicgame(currentindex-1),leftnumbers[currentindex-2],currentindex-1);
                    }}, 400);
            }
        });
    }
    @Override
    public void initData() {
        settitle("图形与对称");
        settitlebackground(R.color.colorblue);
        leftnumbers= new int[]{2, 2, 6, 3, 2, 2};
        subtitles =   new String[]{
                "排顺序",
                "排顺序",
                "补满图形",
                "马赛克镶嵌画",
                "对称",
                "对称"
        };
        descriptions = new String[] {
                "明确构造规则，用MAGSPASE部件补满一行",
                "明确构造规则，用MAGSPASE部件补满一行",
                "明确构造规则，用MAGSPASE部件补满一行",
                "按给出规则把MAGSPACE部件放到马赛克镶嵌画里",
                "根据下面图形，组成其镜像图形",
                "根据下面图形，组成其镜像图形"};
    }
    /**
     * 关卡初始化
     * @param view
     * @param number 剩余数量（分数）
     * @param index 当前关卡数
     */
    public void initGame(View view,int number,int index){
        super.initGame(view,number,index);
        currgame = (SymmertypicgameFather) GameUtil.getSysmertyPicgame(currentindex);
        if(currgame.resetflag){
            currgame.resetflag=false;
        }
        else{
            currgame.resetPath();
        }
        view.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currgame.isdown = true;
            currgame.ismove= false;
            currgame.invalidate();
        }
        currgame.Point_x = (int) event.getRawX();
        currgame.Point_y = (int) event.getRawY();
        //延迟执行剩余数量的刷新，保证在判定点击正确区域后再刷新
        new Handler().postDelayed(new Runnable(){
            public void run() {
                if(currgame.pic_num==0){
                    /**
                     * TO DO 100的动画
                     */
                    hideshengyv();
                    mScore.setAnimation(AnimationUtils.loadAnimation(MyApplication.myContext,R.anim.score));
                }
                setmScore(GameUtil.getcurrentnumber(currgame.pic_num));
            }}, 150);
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
//        SymmertypicgameFather game = (SymmertypicgameFather) GameUtil.getSysmertyPicgame(currentindex);
//        game.resetPath();
    }


}
