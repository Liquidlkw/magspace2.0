package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Angle1 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_angle1);
        ChangSubtileColor(R.color.colorgreen);
        showtoplayout();
        init("2.内角图形","通过填充一个平面，没有任何剩余空间的方法找到一个多边形的内角。","做好准备","掌握主要概念","两条射线和线段的端点所形成的图形叫做角");
        setmPageNumber("01/06");
    }
}
