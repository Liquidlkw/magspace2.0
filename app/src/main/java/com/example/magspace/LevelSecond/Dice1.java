package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Dice1 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_dice1);
        showtoplayout();
        ChangSubtileColor(R.color.colorblue);
        init("4.立方体和骰子","掌握立方体特征并找一找相反的面","做好准备","掌握主要概念","具有六个面的立体图形被称为立方体，利用MAGSPACE拼出一个立方体");
        setmPageNumber("01/06");
    }
}
