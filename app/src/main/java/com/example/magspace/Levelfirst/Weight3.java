package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Weight3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_weight3);
        init("探险","","利用MAGSPACE制作天平");
        setmPageNumber("03/06");

    }
}
