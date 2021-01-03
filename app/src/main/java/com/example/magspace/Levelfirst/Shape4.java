package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Shape4 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidetoplayout();
        setcontentlayout(R.layout.activity_shape4);
        init("找一找","","组织核心概念");
        setmPageNumber("04/06");
    }
}
