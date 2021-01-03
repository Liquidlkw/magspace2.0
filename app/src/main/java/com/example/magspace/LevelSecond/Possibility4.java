package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Possibility4 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_possibility4);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","找出箭头指向最多的部分。");
        setmPageNumber("04/06");
    }
}
