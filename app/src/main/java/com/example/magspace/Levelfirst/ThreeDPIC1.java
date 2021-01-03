package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class ThreeDPIC1 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_three_dpic1);
        showtoplayout();
        init("6.立体图形","研究生活中不同图形并掌握立体图形的表面以及特点","做好准备","掌握主要概念","一只小公鸡要找妈妈，要踩着立方体过一条河");
        setmPageNumber("01/06");
    }
}
