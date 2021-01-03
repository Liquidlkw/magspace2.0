package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Angle5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_angle5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻","","学习如何测量一个规则五边形的方法");
        setmPageNumber("05/06");
    }
}
