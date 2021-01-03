package com.example.magspace.FindPic;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.magspace.Base.MathFirstBaseActivity;
import com.example.magspace.MyApplication;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class FindPicStage extends MathFirstBaseActivity implements View.OnTouchListener {

     public FindpicturegameFather currgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加找出图形关卡集合
        GameUtil.addFindPicGames(Findpicturegame1.getInstance(this, display));
        GameUtil.addFindPicGames(Findpicturegame2.getInstance(this, display));
        GameUtil.addFindPicGames(Findpicturegame3.getInstance(this, display));
        GameUtil.addFindPicGames(Findpicturegame4.getInstance(this, display));
        GameUtil.addFindPicGames(Findpicturegame5.getInstance(this, display));
        GameUtil.addFindPicGames(Findpicturegame6.getInstance(this, display));
        //初始化（通过GameUtil.getFindPicGames(1)初始化第一关）
        AnimUtil.setShowAnimation(GameUtil.getFindPicGames(1),400);
        initGame(GameUtil.getFindPicGames(1),leftnumbers[currentindex-1],1);

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
                        AnimUtil.setShowAnimation(GameUtil.getFindPicGames(currentindex+1),400);
                        initGame(GameUtil.getFindPicGames(currentindex+1),leftnumbers[currentindex],currentindex+1);
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
                        AnimUtil.setShowAnimation(GameUtil.getFindPicGames(currentindex-1),400);
                        initGame(GameUtil.getFindPicGames(currentindex-1),leftnumbers[currentindex-2],currentindex-1);
                    }}, 400);
            }
        });
    }

    @Override
    public void initData() {
        settitlebackground(R.color.colorred);
        settitle("找出图形");
        leftnumbers= new int[]{6, 7, 9, 5, 5, 7};
        subtitles =   new String[]{"四边形","三角形","组成图形","组成图形","组成图形","组成图形"};
        descriptions = new String[] {"找出所有MAGSPACE四边形组件","找出所有MAGSPACE三角形组件",
                "用所有给出的部件组成下面这只迷人的猫","用所有给出的部件组成下面的圣诞树",
                "用所有给出的部件组成下面的小鱼","用所有给出的部件组成下面这座城堡"};
    }

    /**
     * 关卡初始化
     * @param view
     * @param number 剩余数量（分数）
     * @param index 当前关卡数
     */
    public void initGame(View view,int number,int index){
        super.initGame(view,number,index);
        currgame = (FindpicturegameFather) GameUtil.getFindPicGames(currentindex);
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
//        FindpicturegameFather game = (FindpicturegameFather) GameUtil.getFindPicGames(currentindex);
//        game.resetPath();

    }

    protected void onResume() {
        super.onResume();

//        initGame(GameUtil.getFindPicGames(currentindex),leftnumbers[currentindex-1],currentindex);
//        super.mArrowRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DataUtil.isvoiceplay)
//                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
//                AnimUtil.setHideAnimation(currgame,400);
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {
//                        currgame.resetPath();
//                        AnimUtil.setShowAnimation(GameUtil.getFindPicGames(currentindex+1),400);
//                        initGame(GameUtil.getFindPicGames(currentindex+1),leftnumbers[currentindex],currentindex+1);
//                    }}, 400);
//
//            }
//        });
//
//        super.mArrowLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DataUtil.isvoiceplay)
//                    DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
//                AnimUtil.setHideAnimation(currgame,400);
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {
//                        currgame.resetPath();
//                        AnimUtil.setShowAnimation(GameUtil.getFindPicGames(currentindex-1),400);
//                        initGame(GameUtil.getFindPicGames(currentindex-1),leftnumbers[currentindex-2],currentindex-1);
//                    }}, 400);
//            }
//        });
    }

}
