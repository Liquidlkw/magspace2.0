package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Solid5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_solid5);
        ChangSubtileColor(R.color.colorgreen);
        init("轻松一刻","","利用MAGSPACE制作旋转体");
        setmPageNumber("05/06");
    }
}
