package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Angle3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_angle3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","用MAGSPACE测量图形的内角度数");
        setmPageNumber("03/06");
    }
}
