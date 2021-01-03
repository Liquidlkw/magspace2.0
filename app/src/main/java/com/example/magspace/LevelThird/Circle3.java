package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Circle3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_circle3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","利用阿基米德穷举法找到π的值");
        setmPageNumber("03/06");
    }
}
