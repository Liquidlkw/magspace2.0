package com.example.magspace.LevelSecond;


import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Rectangular5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_rectangular5);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","画出一个长方体的容积图");
        setmPageNumber("05/06");
    }
}
