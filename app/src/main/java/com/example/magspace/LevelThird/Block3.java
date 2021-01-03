package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Block3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_block3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","在平面上通过堆叠立方体形成一个三维图形来展示图形");
        setmPageNumber("03/06");
    }
}
