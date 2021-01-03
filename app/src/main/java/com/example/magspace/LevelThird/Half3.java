package com.example.magspace.LevelThird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Half3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_half3);
        ChangSubtileColor(R.color.colorgreen);
        init("探险","","截去正四面体各边的一半得到对应图形");
        setmPageNumber("03/06");
    }
}
