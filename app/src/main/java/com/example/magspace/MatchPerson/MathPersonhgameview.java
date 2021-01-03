package com.example.magspace.MatchPerson;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.magspace.Bean.Question;
import com.example.magspace.Bean.Touchregion;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class MathPersonhgameview extends View {
    Path path,anopath;
    Paint paint;
    public Touchregion regions[],buttonregions[],ansregions[];
    public Question questions[];
    public String choosestr[];
    public int Screen_w;
    public  int Screen_h;
    public int Viewwith;
    public int Viewheight;
    public int startx;
    public int starty;
    public  float onep_screen_w;
    public  float onep_screen_h;
    public  float fontrite;
    public boolean isback;
    //判断是否在区域内的方法
    Region re;
    //构造一个区域对象，左闭右开的。
    RectF r;
    //是否需要判定选择图形类型
    public RectF rects[],buttonrects[],ansrects[],minrects[];
    //当前选中的图形类型
    String curretypename;
    int curreindex,qusindex,scoresum;
    boolean isright,isres;
    //触摸检测数据
    public  static int Point_x;
    public static int Point_y;
    public static boolean ismove,isup;
    public  static boolean isdown;
    //按键阴影调整
    boolean buttonflag,inbutton;
    //选项效果
    ObjectAnimator chooseanimator;
    boolean ischoosestart;
    boolean chooseflag;
    float chooseprogress;
    //转场效果
    ObjectAnimator showanimator;
    boolean isshowstart;
    boolean showflag;
    float showprogress;
    public static MathPersonhgameview instance;
    public static  MathPersonhgameview getInstance(Context context, Display display){
        if (instance == null) {
            instance=new MathPersonhgameview(context, display);
        }
        return instance;
    }
    public MathPersonhgameview(Context context) {
        super(context);
    }
    public MathPersonhgameview(Context context,Display display) {
        super(context);
        this.init(display);
    }
    public void init(Display display) {
        paint =new Paint();
        paint.setAntiAlias(true);
//        this.setBackgroundColor(Color.WHITE);
        Screen_w = display.getWidth();
        Screen_h = display.getHeight();
        if(Screen_h<Screen_w){
            int x=Screen_w;
            Screen_w=Screen_h;
            Screen_h=x;
        }
        onep_screen_w=Screen_w*1.0f/100;
        onep_screen_h=Screen_h*1.0f/100;
        fontrite=Screen_w*1.0f/1280;
        chooseanimator = ObjectAnimator.ofFloat(MathPersonhgameview.this, "chooseprogress", 0.0f, 1.0f);
        chooseanimator.setDuration(1000);
        showanimator = ObjectAnimator.ofFloat(MathPersonhgameview.this, "showprogress", 0.0f, 1.0f);
        showanimator.setDuration(1000);
        re = new Region();
        r = new RectF();
        regions=new Touchregion[3];
        buttonregions=new Touchregion[5];
        ansregions=new Touchregion[8];

        rects=new RectF[3];
        buttonrects=new RectF[5];
        ansrects=new RectF[8];
        minrects=new RectF[11];
        questions=new Question[8];
        //选项
        for(int i=0;i<3;i++){
            rects[i]=new RectF(5*onep_screen_w,60*onep_screen_h+10*i*onep_screen_w,
                    50*onep_screen_w,60*onep_screen_h+10*(i+1)*onep_screen_w);
            minrects[i]=new RectF(10*onep_screen_w,60*onep_screen_h+10*i*onep_screen_w,
                    18*onep_screen_w,60*onep_screen_h+10*i*onep_screen_w+8*onep_screen_w);
        }
        for(int i=0;i<3;i++){
            path =new Path();
            path.addRect(rects[i],Path.Direction.CW);
            regions[i] = new Touchregion(path);
            regions[i].point_x=(int)(15*onep_screen_w);
            regions[i].point_y=(int)(60*onep_screen_h+10*i*onep_screen_w+5*onep_screen_w);
        }
        //按钮
        buttonrects[0]=new RectF(85*onep_screen_w,1*onep_screen_h,
                95*onep_screen_w,1*onep_screen_h+10*onep_screen_w);
        for(int i=0;i<3;i++){
            buttonrects[i+1]=new RectF((25+20*i)*onep_screen_w,80*onep_screen_h,(35+20*i)*onep_screen_w,80*onep_screen_h+10*onep_screen_w);
        }
        buttonrects[4]=new RectF(5*onep_screen_w,1*onep_screen_h,
                15*onep_screen_w,1*onep_screen_h+10*onep_screen_w);
        for(int i=0;i<5;i++){
            path =new Path();
            path.addRect(buttonrects[i],Path.Direction.CW);
            buttonregions[i] = new Touchregion(path);
        }
        buttonregions[0].point_x=(int)(90*onep_screen_w);
        buttonregions[0].point_y=(int)(1*onep_screen_h+5*onep_screen_w);
        buttonregions[4].point_x=(int)(10*onep_screen_w);
        buttonregions[4].point_y=(int)(1*onep_screen_h+5*onep_screen_w);
        for(int i=0;i<3;i++){
            buttonregions[i+1].point_x=(int)((30+20*i)*onep_screen_w);
            buttonregions[i+1].point_y=(int)(80*onep_screen_h+5*onep_screen_w);
        }
        //答案
        for(int i=0;i<8;i++){
            ansrects[i]=new RectF(18*(i%5)*onep_screen_w+5*onep_screen_w,30*onep_screen_h+20*(i/5)*onep_screen_w,
                    18*(i%5)*onep_screen_w+23*onep_screen_w,30*onep_screen_h+20*(i/5)*onep_screen_w+15*onep_screen_w);
            minrects[i+3]=new RectF(18*(i%5)*onep_screen_w+9*onep_screen_w,30*onep_screen_h+20*(i/5)*onep_screen_w,
                    18*(i%5)*onep_screen_w+19*onep_screen_w,30*onep_screen_h+20*(i/5)*onep_screen_w+10*onep_screen_w);
        }
        for(int i=0;i<8;i++){
            path =new Path();
            path.addRect(ansrects[i],Path.Direction.CW);
            ansregions[i] = new Touchregion(path);
            ansregions[i].point_x=(int)((18*(i%5)+14)*onep_screen_w);
            ansregions[i].point_y=(int)(30*onep_screen_h+20*(i/5)*onep_screen_w+5*onep_screen_w);
        }
        choosestr=new String[]{"36、90、9","36、30、9","36、90、10"};
        questions[0]=new Question("九宫格中内角有（）个？各角有（）度？由（）片正方形组成？",choosestr,0,p1,12);
        choosestr=new String[]{"42、18、24","24、18、18","42、24、18"};
        questions[1]=new Question("十二边形中内角有（）个？由（）个90度和（）个60度组成？"
                ,choosestr,2,p2,12);
        choosestr=new String[]{"3","4","5"};
        questions[2]=new Question("在正四面体的展开图中共有（）个三角形？",choosestr,1,p3,12);
        choosestr=new String[]{"3、1","3、2","4、1"};
        questions[3]=new Question("在三角锥的展开图中共有（）个三角形？共有（）个四边形？",choosestr,2,p4,12);
        choosestr=new String[]{"5","6","7"};
        questions[4]=new Question("在正六面体的展开图中共有（）个四边形？",choosestr,1,p5,12);
        choosestr=new String[]{"8","9","10"};
        questions[5]=new Question("在正八面的展开图中共有（）个三角形？",choosestr,0,p6,12);
        choosestr=new String[]{"6、8","7、7","8、6"};
        questions[6]=new Question("在十四面体的展开图中共有（）个三角形？共有（）个四边形？",choosestr,2,p7,12);
        choosestr=new String[]{"20","21","22"};
        questions[7]=new Question("在正二十四面体的展开图中共有（）个三角形？",choosestr,0,p8,12);

        qusindex=0;
        isres=false;
        scoresum=0;
        isback=false;
        ischoosestart=true;
        chooseflag=false;
        isshowstart=true;
        showflag=false;
    }
    public void setChooseprogress(float chooseprogress){
        this.chooseprogress=chooseprogress;
//       shadowpaint.setPathEffect(createPathEffect(shadowlength, shadowprogress, 0.0f));
        invalidate();
    }
    public void setShowprogress(float showprogress){
        this.showprogress=showprogress;
//       shadowpaint.setPathEffect(createPathEffect(shadowlength, shadowprogress, 0.0f));
        invalidate();
    }
    public static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                pathLength - phase * pathLength);
    }
    Bitmap submit = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.submit)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.submit)).getBitmap() : null;
    Bitmap test_bg = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.test_bg)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.test_bg)).getBitmap() : null;
    Bitmap title_bg = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.title_bg)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.title_bg)).getBitmap() : null;
    Bitmap page_menu = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page_menu)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page_menu)).getBitmap() : null;
    Bitmap page_next = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page_next)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page_next)).getBitmap() : null;
    Bitmap page_pre = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.page_pre)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.page_pre)).getBitmap() : null;
    Bitmap show_back = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.show_back)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.show_back)).getBitmap() : null;
    Bitmap p1 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_1)).getBitmap() : null;
    Bitmap p2 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_2)).getBitmap() : null;
    Bitmap p3 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_3)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_3)).getBitmap() : null;
    Bitmap p4 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_4)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_4)).getBitmap() : null;
    Bitmap p5 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_5)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_5)).getBitmap() : null;
    Bitmap p6 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_6)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_6)).getBitmap() : null;
    Bitmap p7 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_7)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_7)).getBitmap() : null;
    Bitmap p8 = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.ceshiti_8)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.ceshiti_8)).getBitmap() : null;
    Bitmap ansright = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.answer_right_bg)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.answer_right_bg)).getBitmap() : null;
    Bitmap anserror = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.answer_error_bg)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.answer_error_bg)).getBitmap() : null;
    Bitmap ansnull = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.answer_nor)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.answer_nor)).getBitmap() : null;
    Bitmap ischooseA = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.choose_pre_0)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.choose_pre_0)).getBitmap() : null;
    Bitmap ischooseB = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.choose_pre_1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.choose_pre_1)).getBitmap() : null;
    Bitmap ischooseC = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.choose_pre_2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.choose_pre_2)).getBitmap() : null;
    Bitmap nochooseA = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.choose_nor_0)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.choose_nor_0)).getBitmap() : null;
    Bitmap nochooseB = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.choose_nor_1)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.choose_nor_1)).getBitmap() : null;
    Bitmap nochooseC = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.choose_nor_2)) != null ? ((BitmapDrawable)
            this.getResources().getDrawable(R.drawable.choose_nor_2)).getBitmap() : null;
    public void onDraw(Canvas c) {
        //判断
        if(isdown||isup){
            //各个按钮
            inbutton=false;
            for(int i=0;i<buttonregions.length;i++){
                //计算控制点的边界
                buttonregions[i].path.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(buttonregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                //点击到正确区域
                if (re.contains((int) Point_x-startx, (int) Point_y-starty)) {
                    inbutton=true;
                    for(int j=0;j<buttonregions.length;j++){
                        buttonregions[j].istouch=false;
                    }
                    buttonregions[i].istouch=true;
                    buttonflag=true;
                    if(isup) {
                        buttonregions[i].istouch=false;
                        if ((i == 2 && (qusindex == 0 || isres)) || (i == 3 && (qusindex == 7 || isres))) {
                            //没声音
                        } else {
                            if (DataUtil.isvoiceplay)
                                DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        }
                        if (i == 0) {
                            isres = true;
                            isshowstart=true;
                            showflag=false;
                            invalidate();
                            scoresum = 0;
                            for (int j = 0; j < questions.length; j++) {
                                if (questions[j].isright) {
                                    scoresum += 12;
                                }
                            }
                            if (scoresum == 96) scoresum = 100;
                        }
                        if (i == 1) {
                            isshowstart=true;
                            showflag=false;
                            if(isres){
                                isres=false;
                                ischoosestart=true;
                                chooseflag=false;
                            }
                            else{
                                isres=true;
                            }
                            invalidate();
                            scoresum = 0;
                            for (int j = 0; j < questions.length; j++) {
                                if (questions[j].isright) {
                                    scoresum += 12;
                                }
                            }
                            if (scoresum == 96) scoresum = 100;
                        }
                        if (i == 2 && qusindex != 0 && !isres) {
                            ischoosestart=true;
                            chooseflag=false;
                            isshowstart=true;
                            showflag=false;
                            qusindex--;
                        }
                        if (i == 3 && qusindex != 7 && !isres) {
                            ischoosestart=true;
                            chooseflag=false;
                            isshowstart=true;
                            showflag=false;
                            qusindex++;
                        }
                        if (i == 4) {
                            isback = true;
                        }
                    }
                    break;
                }
            }
            if(buttonflag&&inbutton==false){
                for(int i=0;i<buttonregions.length;i++)
                    buttonregions[i].istouch=false;
            }
            //选项
            if(!isres&&isup) {
                for (int i = 0; i < regions.length; i++) {
                    //计算控制点的边界
                    regions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(regions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x - startx, (int) Point_y - starty)) {
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        questions[qusindex].index = i;
                        //选对了
                        if (questions[qusindex].rightindex == i) {
                            questions[qusindex].isright = true;
//                        if (DataUtil.isvoiceplay)
//                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        }
                        else{
                            questions[qusindex].isright = false;
                        }
                        break;
                    }
                }
            }
            //答题卡
            if(isres&&isdown) {
                for (int i = 0; i < ansregions.length; i++) {
                    //计算控制点的边界
                    ansregions[i].path.computeBounds(r, true);
                    //设置区域路径和剪辑描述的区域
                    re.setPath(ansregions[i].path, new Region((int) r.left, (int) r.top, (int) r.right, (int) r.bottom));
                    //点击到正确区域
                    if (re.contains((int) Point_x - startx, (int) Point_y - starty)) {
                        ischoosestart=true;
                        chooseflag=false;
                        isshowstart=true;
                        showflag=false;
                        if (DataUtil.isvoiceplay)
                            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
                        isres = false;
                        qusindex = i;
                        invalidate();
                        break;
                    }
                }
            }

            isdown=false;
            isup=false;
        }
        //转场控制
        if(isshowstart){
            //重新开始
            if(!showflag) {
                Log.i("zh", "show start");
                showprogress = 0;
                showanimator.start();
                showflag=true;
            }
            if(showprogress>=0.99f){
                isshowstart=false;
            }
        }
        //背景
        c.drawBitmap(test_bg,null,new RectF(5*onep_screen_w,20*onep_screen_h,95*onep_screen_w,80*onep_screen_h),paint);
        //标题
        c.drawBitmap(title_bg,null,new RectF(25*onep_screen_w,15*onep_screen_h,75*onep_screen_w,25*onep_screen_h),paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(80*fontrite);
        paint.setAlpha(255);
        if(isshowstart) {
            paint.setAlpha((int)(showprogress*255));
        }
        if(!isres){
            c.drawText("第 "+(qusindex+1)+" 题",40*onep_screen_w,20*onep_screen_h,paint);
        }
        else{
            c.drawText("答 题 卡",40*onep_screen_w,20*onep_screen_h,paint);
        }
        paint.setAlpha(255);
        //按钮
        c.drawBitmap(submit,null,buttonrects[0],paint);
        c.drawBitmap(page_menu,null,buttonrects[1],paint);
        if(qusindex==0||isres){
            paint.setAlpha(100);
            c.drawBitmap(page_pre,null,buttonrects[2],paint);
            paint.setAlpha(255);
        }
        else
        c.drawBitmap(page_pre,null,buttonrects[2],paint);
        if(qusindex==7||isres){
            paint.setAlpha(100);
            c.drawBitmap(page_next,null,buttonrects[3],paint);
            paint.setAlpha(255);
        }
        else
            c.drawBitmap(page_next,null,buttonrects[3],paint);
        c.drawBitmap(show_back,null,buttonrects[4],paint);
        paint.setColor(Color.WHITE);
        for(int i=0;i<buttonregions.length;i++){
            if(buttonregions[i].istouch) {
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.FILL);
                paint.setAlpha(100);
                c.drawCircle(buttonregions[i].point_x, buttonregions[i].point_y, 5 * onep_screen_w, paint);
                paint.setAlpha(255);
            }
        }
        if(!isres) {
            //题目
            paint.setAlpha(255);
            paint.setColor(Color.WHITE);
            if(isshowstart) {
                paint.setAlpha((int)(showprogress*255));
            }
            paint.setTextSize(50 * fontrite);
            if(questions[qusindex].qus.length()<=20)
            c.drawText(questions[qusindex].qus, 12 * onep_screen_w, 30 * onep_screen_h, paint);
            else{
                c.drawText(questions[qusindex].qus.substring(0,20), 12 * onep_screen_w, 30 * onep_screen_h, paint);
                c.drawText(questions[qusindex].qus.substring(20), 12 * onep_screen_w, 35 * onep_screen_h, paint);
            }
            //图片
            if (qusindex < 2) {
            c.drawBitmap(questions[qusindex].pic,null,new RectF(50*onep_screen_w-10*onep_screen_h,
                    38*onep_screen_h,50*onep_screen_w+10*onep_screen_h,58*onep_screen_h),paint);
            } else {
                c.drawBitmap(questions[qusindex].pic,null,new RectF(50*onep_screen_w-14*onep_screen_h,
                        41*onep_screen_h,50*onep_screen_w+14*onep_screen_h,55*onep_screen_h),paint);
            }
            paint.setAlpha(255);
            //选项
            //选项控制
            if(ischoosestart){
                //重新开始
                if(!chooseflag) {
                    Log.i("zh", "choose start");
                    chooseprogress = 0;
                    chooseanimator.start();
                    chooseflag=true;
                }
                if(chooseprogress>=0.99f){
                    ischoosestart=false;
                }
            }
            paint.setAlpha(255);
            if(ischoosestart){
                if(chooseprogress>0.5)
                    paint.setAlpha(255);
                else
                    paint.setAlpha((int)(chooseprogress*2*255));
            }
            if(questions[qusindex].index==0){
                c.drawBitmap(ischooseA,null,minrects[0],paint);
            }
            if(questions[qusindex].index!=0){
                c.drawBitmap(nochooseA,null,minrects[0],paint);
            }
            paint.setColor(this.getResources().getColor(R.color.colororange));
            if(ischoosestart){
                if(chooseprogress>0.5)
                    paint.setAlpha(255);
                else
                    paint.setAlpha((int)(chooseprogress*2*255));
            }
            paint.setTextSize(40*fontrite);
            c.drawText(questions[qusindex].choose[0],regions[0].point_x+4*onep_screen_w,regions[0].point_y+0*onep_screen_h,paint);
            if(ischoosestart){
                if(chooseprogress<=0.25){
                    paint.setAlpha(0);
                }
                else if(chooseprogress<=0.75)
                    paint.setAlpha((int)((chooseprogress-0.25)*2*255));
                else
                    paint.setAlpha(255);
            }
            if(questions[qusindex].index==1){
                c.drawBitmap(ischooseB,null,minrects[1],paint);
            }
            if(questions[qusindex].index!=1){
                c.drawBitmap(nochooseB,null,minrects[1],paint);
            }
            paint.setColor(this.getResources().getColor(R.color.colororange));
            if(ischoosestart){
                if(chooseprogress<=0.25){
                    paint.setAlpha(0);
                }
                else if(chooseprogress<=0.75)
                    paint.setAlpha((int)((chooseprogress-0.25)*2*255));
                else
                    paint.setAlpha(255);
            }
            paint.setTextSize(40*fontrite);
            c.drawText(questions[qusindex].choose[1],regions[1].point_x+4*onep_screen_w,regions[1].point_y+0*onep_screen_h,paint);
            if(ischoosestart){
                if(chooseprogress<=0.5){
                    paint.setAlpha(0);
                }
                else if(chooseprogress<=0.99)
                    paint.setAlpha((int)((chooseprogress-0.5)*2*255));
                else
                    paint.setAlpha(255);
            }
            if(questions[qusindex].index==2){
                c.drawBitmap(ischooseC,null,minrects[2],paint);
            }
            if(questions[qusindex].index!=2){
                c.drawBitmap(nochooseC,null,minrects[2],paint);
            }
            paint.setColor(this.getResources().getColor(R.color.colororange));
            if(ischoosestart){
                if(chooseprogress<=0.5){
                    paint.setAlpha(0);
                }
                else if(chooseprogress<=0.99)
                    paint.setAlpha((int)((chooseprogress-0.5)*2*255));
                else
                    paint.setAlpha(255);
            }
            paint.setTextSize(40*fontrite);
            c.drawText(questions[qusindex].choose[2],regions[2].point_x+4*onep_screen_w,regions[2].point_y+0*onep_screen_h,paint);
            paint.setAlpha(255);
        }
        //答题卡
        else{
            paint.setAlpha(255);
            if(isshowstart) {
                paint.setAlpha((int)(showprogress*255));
            }
                for(int i=0;i<8;i++){
                    if(questions[i].index!=-1) {
                        if (questions[i].isright)
                            c.drawBitmap(ansright, null, minrects[i + 3], paint);
                        else
                            c.drawBitmap(anserror, null, minrects[i + 3], paint);
                        paint.setTextSize(60 * fontrite);
                        paint.setColor(Color.WHITE);
                        if(isshowstart) {
                            paint.setAlpha((int)(showprogress*255));
                        }
                        if(questions[i].index==0)
                            c.drawText("A", ansregions[i].point_x - 1.3f * onep_screen_w, ansregions[i].point_y + 2.2f * onep_screen_w, paint);
                        if(questions[i].index==1)
                            c.drawText("B", ansregions[i].point_x - 1.3f * onep_screen_w, ansregions[i].point_y + 2.2f * onep_screen_w, paint);
                        if(questions[i].index==2)
                            c.drawText("C", ansregions[i].point_x - 1.3f * onep_screen_w, ansregions[i].point_y + 2.2f * onep_screen_w, paint);
                    }
                    else{
                        c.drawBitmap(ansnull, null, minrects[i + 3], paint);
                    }
                    paint.setTextSize(40*fontrite);
                    paint.setColor(Color.BLACK);
                    if(isshowstart) {
                        paint.setAlpha((int)(showprogress*255));
                    }
                    c.drawText(""+(1+i),ansregions[i].point_x-1*onep_screen_w,ansregions[i].point_y+10*onep_screen_w,paint);
                }
            paint.setTextSize(80*fontrite);
            paint.setColor(Color.WHITE);
            if(isshowstart) {
                paint.setAlpha((int)(showprogress*255));
            }
            c.drawText("成绩："+scoresum,40*onep_screen_w,70*onep_screen_h,paint);
            paint.setAlpha(255);
        }
    }
}
