package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Cylinder2 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_cylinder2);
        setmPageNumber("02/06");
        ChangSubtileColor(R.color.colorblue);
        init("数学故事","阅读真实生活中和数学相关的故事","欧拉定律");
    }
}
