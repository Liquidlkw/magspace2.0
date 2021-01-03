package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Block5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_block5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻","","把四个立方体放到一起拼成一个图形");
        setmPageNumber("05/06");
    }
}
