package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class SimilarPic3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_similar_pic3);
        ChangSubtileColor(R.color.colorblue);
        init("探险","","使用4片相同的MAGSPACE构建类似图形");
        setmPageNumber("03/06");
    }
}
