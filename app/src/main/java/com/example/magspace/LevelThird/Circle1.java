package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Circle1 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_circle1);
        showtoplayout();
        ChangSubtileColor(R.color.colorgreen);
        init("5.圆和π","理解π是如何计算圆的","做好准备","掌握主要概念","圆滚动一周走过的距离被称为圆周，圆周与直径之比叫做π。\nπ是无穷小数，3.1415926535...，无法完全精确，所以，分成两个半圆方便计算。\n通过改变π的公式发现圆的圆周：\n圆周=πx直径，即C=πd\n圆周=2πx半径，即C=2πr");
        setmPageNumber("01/06");

    }
}
