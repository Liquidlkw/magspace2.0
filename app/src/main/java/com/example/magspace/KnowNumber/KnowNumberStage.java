package com.example.magspace.KnowNumber;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.magspace.MyApplication;
import com.example.magspace.Base.MathFirstBaseActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class KnowNumberStage  extends MathFirstBaseActivity implements View.OnTouchListener {
    public KnownumbergameFather currgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameUtil.addKnownumbergames(Knownumbergame1.getInstance(this, display));
        GameUtil.addKnownumbergames(Knownumbergame2.getInstance(this, display));
        GameUtil.addKnownumbergames(Knownumbergame3.getInstance(this, display));
        GameUtil.addKnownumbergames(Knownumbergame4.getInstance(this, display));
        GameUtil.addKnownumbergames(Knownumbergame5.getInstance(this, display));
        GameUtil.addKnownumbergames(Knownumbergame6.getInstance(this, display));
        AnimUtil.setShowAnimation(GameUtil.getKnownumberGames(1),400);
        initGame(GameUtil.getKnownumberGames(1),leftnumbers[currentindex-1],1);

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
                        AnimUtil.setShowAnimation(GameUtil.getKnownumberGames(currentindex+1),400);
                        initGame(GameUtil.getKnownumberGames(currentindex+1),leftnumbers[currentindex],currentindex+1);
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
                        AnimUtil.setShowAnimation(GameUtil.getKnownumberGames(currentindex-1),400);
                        initGame(GameUtil.getKnownumberGames(currentindex-1),leftnumbers[currentindex-2],currentindex-1);
                    }}, 400);
            }
        });
    }
    @Override
    public void initData() {
        settitle("认识数字");
        settitlebackground(R.color.colorgreen);
        leftnumbers= new int[]{4, 3, 9, 3, 8, 1};
        subtitles =   new String[]{
                "数字排序",
                "数字排序",
                "分数",
                "数独",
                "多米诺骨牌",
                "相加得12"
        };
        descriptions = new String[] {
                "完成数列",
                "用MAGSPACE正方形补满大矩形",
                "用图片里的4个正方形，拼成新的几何图形",
                "补充下面表格，使每个数字每行每列只出现一次",
                "摆好多米诺骨牌，使得相加的结果如图所示的数字",
                "把图形放到数字表中，使得每个图形中数字和等于12"};
    }
    /**
     * 关卡初始化
     * @param view
     * @param number 剩余数量（分数）
     * @param index 当前关卡数
     */
    public void initGame(View view,int number,int index){
        super.initGame(view,number,index);
        currgame = (KnownumbergameFather) GameUtil.getKnownumberGames(currentindex);
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
//        KnownumbergameFather game = (KnownumbergameFather) GameUtil.getKnownumberGames(currentindex);
//        game.resetPath();
    }


}
