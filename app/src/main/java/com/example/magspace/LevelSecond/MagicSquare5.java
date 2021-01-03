package com.example.magspace.LevelSecond;

import android.os.Bundle;
import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class MagicSquare5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_magic_square5);
        ChangSubtileColor(R.color.colorblue);
        init("轻松一刻","","使用MAGSPACE数字卡片完成魔方");
        setmPageNumber("05/06");

    }
}
