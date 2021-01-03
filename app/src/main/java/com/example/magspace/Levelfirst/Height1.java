package com.example.magspace.Levelfirst;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.DiyView.CustomView;
import com.example.magspace.R;

public class Height1 extends BasePageActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_height1);
        showtoplayout();
        init("4.高度比较", "利用MAGSPACE制作物体，并对各物体的高度进行比较", "做好准备", "掌握主要概念", "看图并理解框中的形容词");


    }




}
