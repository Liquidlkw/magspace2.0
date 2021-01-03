package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Chess3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_chess3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","用MAGSPACE完成下列题目");
        setmPageNumber("03/06");
    }
}
