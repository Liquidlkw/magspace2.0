package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Solid3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_solid3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","利用给定的MAGSPACE制作圆柱和圆锥");
        setmPageNumber("03/06");
    }
}
