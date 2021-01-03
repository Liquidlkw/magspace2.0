package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Cylinder5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_cylinder5);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","通过折叠容积做成三角棱柱和四角椎");
        setmPageNumber("05/06");
    }
}
