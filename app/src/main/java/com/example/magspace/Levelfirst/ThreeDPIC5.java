package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class ThreeDPIC5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_three_dpic5);
        init("轻松一刻","","用下列给出的10个立体图形制作不同的团图案");
         setmPageNumber("05/06");
    }
}
