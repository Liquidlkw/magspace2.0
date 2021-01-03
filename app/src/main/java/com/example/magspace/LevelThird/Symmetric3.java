package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Symmetric3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_symmetric3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","用给出的MAGSPACE做对称图形");
        setmPageNumber("03/06");
    }
}
