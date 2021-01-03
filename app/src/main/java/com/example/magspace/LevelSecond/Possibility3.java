package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Possibility3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_possibility3);
        ChangSubtileColor(R.color.colorblue);
        init("探险","","用MAGSPACE做一个任务，点击任务中红色的部分");
        setmPageNumber("03/06");
    }
}
