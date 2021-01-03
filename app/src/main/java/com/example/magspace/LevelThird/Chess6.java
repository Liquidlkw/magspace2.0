package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Chess6 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_chess6);
        init("复习","","下面8个半常规镶嵌图形，通过不同类别的正多边形构成。通过找出图形内部某一点的角度之和检查常规的镶嵌图形。");
        ChangSubtileColor(R.color.colorgreen);
        setmPageNumber("06/06");
    }
}
