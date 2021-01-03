package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Symmetric4 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_symmetric4);
        ChangSubtileColor(R.color.colorgreen);
        init("找一找","","组织核心概念");
        setmPageNumber("04/06");

    }
}
