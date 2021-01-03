package com.example.magspace.ThreeDPic;

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

public class ThreeDStage extends MathFirstBaseActivity implements View.OnTouchListener{
    public ThreeDgameFather currgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameUtil.addThreedPicgames(ThreeDgame1.getInstance(this, display));
        GameUtil.addThreedPicgames(ThreeDgame2.getInstance(this, display));
        GameUtil.addThreedPicgames(ThreeDgame3.getInstance(this, display));
        GameUtil.addThreedPicgames(ThreeDgame4.getInstance(this, display));
        GameUtil.addThreedPicgames(ThreeDgame5.getInstance(this, display));
        GameUtil.addThreedPicgames(ThreeDgame6.getInstance(this, display));
        AnimUtil.setShowAnimation(GameUtil.getThreedPicgames(1),400);
        initGame(GameUtil.getThreedPicgames(1),leftnumbers[currentindex-1],1);

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
                        AnimUtil.setShowAnimation(GameUtil.getThreedPicgames(currentindex+1),400);
                        initGame(GameUtil.getThreedPicgames(currentindex+1),leftnumbers[currentindex],currentindex+1);
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
                        AnimUtil.setShowAnimation(GameUtil.getThreedPicgames(currentindex-1),400);
                        initGame(GameUtil.getThreedPicgames(currentindex-1),leftnumbers[currentindex-2],currentindex-1);
                    }}, 400);
            }
        });
        ;
    }
    @Override
    public void initData() {
        settitlebackground(R.color.colorAccent);
        settitle("三维图形");
        leftnumbers= new int[]{1, 1, 1, 1, 1, 1};
        subtitles =   new String[]{
                "组成立体",
                "组成立体",
                "得到还是得不到",
                "得到还是得不到",
                "得到还是得不到",
                "得到还是得不到"
        };
        descriptions = new String[] {
                "以下示意图和那个给出的实物最相符",
                "以下示意图和那个给出的实物最相符",
                "指出那个示意图得不到给出的图形",
                "指出那个示意图得不到给出的图形",
                "指出那个示意图得不到给出的图形",
                "指出那个示意图得不到给出的图形"};

    }
    /**
     * 关卡初始化
     * @param view
     * @param number 剩余数量（分数）
     * @param index 当前关卡数
     */
    public void initGame(View view,int number,int index){
        super.initGame(view,number,index);
        currgame = (ThreeDgameFather) GameUtil.getThreedPicgames(currentindex);
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
//        ThreeDgameFather game = (ThreeDgameFather) GameUtil.getThreedPicgames(currentindex);
//        game.resetPath();
    }

}
