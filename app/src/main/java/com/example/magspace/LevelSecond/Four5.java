package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Four5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_four5);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","用数字1~9和加减乘除，得到同样的数字");
        setmPageNumber("05/06");
    }

}

