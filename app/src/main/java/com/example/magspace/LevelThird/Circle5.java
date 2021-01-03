package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Circle5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_circle5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻","","找出轮子直径和它朝固定方向转动时转动次数的关系");
        setmPageNumber("05/06");
    }
}
