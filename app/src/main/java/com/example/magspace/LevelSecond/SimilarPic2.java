package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class SimilarPic2 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangSubtileColor(R.color.colorblue);
        setcontentlayout(R.layout.activity_similar_pic2);
        init("数学故事","","阅读一个现实中真实的数学故事");
        setmPageNumber("02/06");
    }
}
