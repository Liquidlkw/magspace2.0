package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class SimilarPic5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_similar_pic5);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","利用更少数的MAGSPACE构建类似图形");
        setmPageNumber("05/06");
    }
}
