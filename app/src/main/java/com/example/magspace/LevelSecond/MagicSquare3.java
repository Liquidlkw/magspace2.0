package com.example.magspace.LevelSecond;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class MagicSquare3 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_magic_square3);
        ChangSubtileColor(R.color.colorblue);
        init("探险","","使用MAGSPACE数字卡片制作不同的魔方，使每行、每列、每条对角线的三个数字之和相等");
        setmPageNumber("03/06");
    }
}
