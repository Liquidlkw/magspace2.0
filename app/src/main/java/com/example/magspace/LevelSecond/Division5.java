package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Division5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_division5);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","利用MAGSPACE将一个假分数幻化成混合数");
        setmPageNumber("05/06");
    }
}
