package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Shape1 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init("2.形状制作","利用MAGSPACE在一个平面上制作不同的形状","做好准备","掌握主要概念","找出不是从下面分割得到的部分");
        setcontentlayout(R.layout.activity_shape1);
        setmPageNumber("02/06");
        showtoplayout();
    }
}
