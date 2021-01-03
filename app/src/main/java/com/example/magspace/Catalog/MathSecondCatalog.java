package com.example.magspace.Catalog;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.magspace.Base.BaseCatalogActivity;
import com.example.magspace.LevelSecond.Cylinder;
import com.example.magspace.LevelSecond.Dice;
import com.example.magspace.LevelSecond.Division;
import com.example.magspace.LevelSecond.Four;
import com.example.magspace.LevelSecond.MagicSquare;
import com.example.magspace.LevelSecond.Possibility;
import com.example.magspace.LevelSecond.Rectangular;
import com.example.magspace.LevelSecond.SimilarPic;
import com.example.magspace.LevelThird.Angle;
import com.example.magspace.LevelThird.Block;
import com.example.magspace.LevelThird.Chess;
import com.example.magspace.LevelThird.Circle;
import com.example.magspace.LevelThird.Half;
import com.example.magspace.LevelThird.Regular;
import com.example.magspace.LevelThird.Solid;
import com.example.magspace.LevelThird.Symmetric;
import com.example.magspace.Levelfirst.Find;
import com.example.magspace.Levelfirst.Height;
import com.example.magspace.Levelfirst.Number;
import com.example.magspace.Levelfirst.Pic;
import com.example.magspace.Levelfirst.Shape;
import com.example.magspace.Levelfirst.ThreeDPIC;
import com.example.magspace.Levelfirst.ViewPoint;
import com.example.magspace.Levelfirst.Weight;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class MathSecondCatalog extends BaseCatalogActivity implements View.OnTouchListener {
  private   MotionEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showbottomselector();
        initone();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.event=event;

        if (DataUtil.isvoiceplay&&event.getAction() == MotionEvent.ACTION_UP)
            DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);

        switch (v.getId()) {
            case R.id.text_view_1:
                startActivities( Number.class,SimilarPic.class, Symmetric.class);
                break;

            case R.id.text_view_2:
                startActivities( Shape.class, MagicSquare.class, Angle.class);
                break;

            case R.id.text_view_3:
                startActivities( Weight.class, Division.class, Chess.class);
                break;
            case R.id.text_view_4:
                startActivities( Height.class, Dice.class, Block.class);
                break;

            case R.id.text_view_5:
                startActivities( Pic.class, Rectangular.class, Circle.class);
                break;

            case R.id.text_view_6:
                startActivities(ThreeDPIC.class, Cylinder.class, Solid.class);
                break;

            case R.id.text_view_7:
                startActivities(ViewPoint.class, Four.class, Regular.class);
                break;

            case R.id.text_view_8:
                startActivities(Find.class, Possibility.class, Half.class);
                break;
        }
        return false;
    }

    private void startActivities(final Class<?>... cls)
    {
        if(i==1)
        {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathSecondCatalog.this, cls[0]));
                    }
                }, 500);
            }
        }

        if(i==2)
        {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathSecondCatalog.this, cls[1]));
                    }
                }, 500);
            }
        }

        if(i==3)
        {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MathSecondCatalog.this, cls[2]));
                    }
                }, 500);
            }
        }
    }

}
