package com.example.magspace.Levelfirst;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Height3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_height3);
        init("探险","","利用MAGSPACE比较塔的高度");
        setmPageNumber("03/06");

    }

}
