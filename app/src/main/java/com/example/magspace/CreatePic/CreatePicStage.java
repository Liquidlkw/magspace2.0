package com.example.magspace.CreatePic;

import android.os.Handler;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.magspace.Base.MathFirstBaseActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.AnimUtil;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.GameUtil;

public class CreatePicStage extends MathFirstBaseActivity implements View.OnTouchListener {
    public CreatePicgameFather currgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加找出图形关卡集合
        GameUtil.addCreatePicGames(CreatePicgame1.getInstance(this, display));
        GameUtil.addCreatePicGames(CreatePicgame2.getInstance(this, display));
        GameUtil.addCreatePicGames(CreatePicgame3.getInstance(this, display));
        GameUtil.addCreatePicGames(CreatePicgame4.getInstance(this, display));
        GameUtil.addCreatePicGames(CreatePicgame5.getInstance(this, display));
        GameUtil.addCreatePicGames(CreatePicgame6.getInstance(this, display));
        //初始化（通过GameUtil初始化第一关）
        AnimUtil.setShowAnimation(GameUtil.getCreatePicGames(1),400);
        initGame(GameUtil.getCreatePicGames(1),1);

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
                        AnimUtil.setShowAnimation(GameUtil.getCreatePicGames(currentindex+1),400);
                        initGame(GameUtil.getCreatePicGames(currentindex+1),currentindex+1);
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
                        AnimUtil.setShowAnimation(GameUtil.getCreatePicGames(currentindex-1),400);
                        initGame(GameUtil.getCreatePicGames(currentindex-1),currentindex-1);
                    }}, 400);
            }
        });
    }

    @Override
    public void initData() {
        settitle("创造图形");
        settitlebackground(R.color.colororange);
        subtitles =   new String[]{"将下图中的六边形分成相同的部分",
                                    "分成各种部分",
                                    "创造新的图形",
                                    "组成正方形",
                                    "组成正方形",
                                    "把矩形分成4个正方形"
                                    };
        descriptions = new String[] {
                "用合适的部件将六边形分成 2 3 6个相同部分",
                "用合适的部件将下图分成2个部分",
                "用图片里的4个正方形，拼成新的几何图形",
                "补充下面给出的图片，使他成为正方形",
                "补充下面给出的图片，使他成为正方形",
                "提示：正方形可以是不同尺寸"};
    }

    /**
     * 关卡初始化
     * @param view
     * @param index 当前关卡数
     */
    public void initGame(View view,int index){
        super.initGame(view,index);
        currgame = (CreatePicgameFather) GameUtil.getCreatePicGames(currentindex);
        if(currgame.resetflag){
            currgame.resetflag=false;
        }
        else{
            currgame.resetPath();
        }
        view.setOnTouchListener(this);
    }
//    public Bitmap getShotPic(View view){
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap b1 = view.getDrawingCache();
//        return b1;
//    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currgame.isdown = true;
            currgame.ismove= false;
            currgame.invalidate();
//            currgame.destroyDrawingCache();
//            currgame.shotpic=getShotPic(currgame);
        }
        currgame.Point_x = (int) event.getRawX();
        currgame.Point_y = (int) event.getRawY();
       return  false;
    }

    @Override
    protected void onStop() {
        super.onStop();
//        CreatePicgameFather game = (CreatePicgameFather) GameUtil.getCreatePicGames(currentindex);
//        game.resetPath();
    }

}
