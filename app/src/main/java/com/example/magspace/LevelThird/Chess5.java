package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Chess5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_chess5);
        init("轻松一刻","","采用两组以上的数字组合在一起");
        ChangSubtileColor(R.color.colorgreen);
        setmPageNumber("05/06");
    }
}
