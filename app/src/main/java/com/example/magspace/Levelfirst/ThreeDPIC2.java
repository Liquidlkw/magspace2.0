package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class ThreeDPIC2 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_three_dpic2);
        init("数学故事","","阅读现实生活中真实的数学故事");
        setmPageNumber("02/06");

    }
}
