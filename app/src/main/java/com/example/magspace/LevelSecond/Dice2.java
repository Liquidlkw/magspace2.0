package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Dice2 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_dice2);
        ChangSubtileColor(R.color.colorblue);
        init("数学故事","","阅读一个真实生活中与数学相关的故事");
        setmPageNumber("02/06");
    }
}
