package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class ThreeDPIC3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_three_dpic3);
        init("探险","","用不同的立体图做一个魔幻城堡");
        setmPageNumber("03/06");
    }
}
