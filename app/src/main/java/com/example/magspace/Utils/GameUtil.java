package com.example.magspace.Utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Switch;

import com.example.magspace.FindPic.Findpicturegame1;
import com.example.magspace.FindPic.Findpicturegame2;
import com.example.magspace.MyApplication;
import com.example.magspace.R;

import java.security.PublicKey;
import java.util.ArrayList;

public class GameUtil extends View {
    public static ArrayList<View> findpicgames=new ArrayList<View>();
    /**
     * 返回指定关卡
     * @param index 关卡1 index =1
     * @return
     */
  public static View getFindPicGames(int index){
      return findpicgames.get(index - 1);
  }

    public static void addFindPicGames(View view){
        findpicgames.add(view);

    }

    public static ArrayList<View> createpicgames=new ArrayList<View>();
    /**
     * 返回指定关卡
     * @param index 关卡1 index =1
     * @return
     */
    public static View getCreatePicGames(int index){
        return createpicgames.get(index - 1);
    }

    public static void addCreatePicGames(View view){
        createpicgames.add(view);

    }
    public static ArrayList<View> knownumbergames=new ArrayList<View>();
    /**
     * 返回指定关卡
     * @param index 关卡1 index =1
     * @return
     */
    public static View getKnownumberGames(int index){
        return knownumbergames.get(index - 1);
    }

    public static void addKnownumbergames(View view){
        knownumbergames.add(view);

    }

    public static ArrayList<View> sysmertypicgames=new ArrayList<View>();
    /**
     * 返回指定关卡
     * @param index 关卡1 index =1
     * @return
     */
    public static View getSysmertyPicgame(int index){
        return sysmertypicgames.get(index - 1);
    }

    public static void addSysmertyPicgames(View view){
        sysmertypicgames.add(view);

    }
    public static ArrayList<View> threedpicgames=new ArrayList<View>();
    /**
     * 返回指定关卡
     * @param index 关卡1 index =1
     * @return
     */
    public static View getThreedPicgames(int index){
        return threedpicgames.get(index - 1);
    }

    public static void addThreedPicgames(View view){
        threedpicgames.add(view);

    }

    //score【0】是100 其余对应数组下标c
    static int [] score  = new int[] {
            R.drawable.score,
            R.drawable.number1,
            R.drawable.number2,
            R.drawable.number3,
            R.drawable.number4,
            R.drawable.number5,
            R.drawable.number6,
            R.drawable.number7,
            R.drawable.number8,
            R.drawable.number9,
           };

    public GameUtil(Context context) {
        super(context);
    }

    /**
     * 获得当前剩余的次数
     * @param number 当前剩余 剩余7个就传入7
     * @return
     */
   public static int getcurrentnumber(int number){
        return score[number];
    }

    /**
     * 返回满分
     * @return
     */
    public static int result(){
        return score[9];
    }
    public static   Bitmap square= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.square);
    public static     Bitmap square2= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.square2);
    public static     Bitmap square3= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.square3);
    public static     Bitmap square_big= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.square_big);
    public static     Bitmap tangle= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.tangle);
    public static     Bitmap tangle2= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.tangle2);
    public static     Bitmap tangle3= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.tangle3);
    public static     Bitmap tangle_big= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.tangle_big);
    public static    Bitmap prismatic= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.prismatic);
    public static    Bitmap trapezoid= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.trapezoid);
    public static    Bitmap hexagon= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.hexagon);
    public static    Bitmap blue= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.blue);
    public static    Bitmap red= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.red);
    public static    Bitmap yellow= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.yellow);
    public static    Bitmap pink= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.pink);
    public static    Bitmap orange= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.orange);
    public static    Bitmap green= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.green);
    public static   Bitmap light_blue= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.light_blue);
    public static    Bitmap light_green= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.light_green);
    public static    Bitmap wenhao= BitmapFactory.decodeResource(MyApplication.myContext.getResources(),R.drawable.wenhao);
    /**
     * 返回图像
     * @param id 输入square，返回方形图片资源id
     * @return
     */
    public static Bitmap getCommonBitmap(int id){
      return BitmapFactory.decodeResource(MyApplication.myContext.getResources(),id);
    }

    public static Bitmap getCommonBitmapbyname(String name){
        switch(name){
            case "square":return square;
            case "square2":return square2;
            case "square3":return square3;
            case "square_big":return square_big;
            case "tangle":return tangle;
            case "tangle2":return tangle2;
            case "tangle3":return tangle3;
            case "tangle_big":return tangle_big;
            case "prismatic":return prismatic;
            case "trapezoid":return trapezoid;
            case "hexagon":return hexagon;
            case "blue":return blue;
            case "red":return red;
            case "yellow":return yellow;
            case "pink":return pink;
            case "orange":return orange;
            case "green":return green;
            case "light_blue":return light_blue;
            case "light_green":return light_green;
            case "wenhao":return wenhao;
            default:break;
        }
        return red;
    }
}
