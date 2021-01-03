package com.example.magspace.Levelfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.magspace.Base.BasePageActivity;
import com.example.magspace.R;

public class Height5 extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentlayout(R.layout.activity_height5);
        init("轻松一刻","","让我们利用MAGSPCE一起完成游戏");
        setmPageNumber("05/06");
    }
}
