package com.example.magspace.Base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;

public class BaseActivity extends AppCompatActivity {

    private FrameLayout content;
    private TextView title;
    private ImageView reback;
    private RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout(R.layout.activity_base);
        background = findViewById(R.id.bg);
        content = findViewById(R.id.content);
        title = findViewById(R.id.title);
        reback = findViewById(R.id.imageView13);
        reback.setOnClickListener(v -> {
            reback.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            if (DataUtil.isvoiceplay)
                DataUtil.soundPool.play(5, 1, 1, 1, 0, 1);
            finish();
        });
    }

    //设置title下面内容区域的内容
    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(contentView, params);
    }

    /**
     * 设置标题
     *
     * @param titleString
     */
    public void setTitle(String titleString) {
        title.setText(titleString);
    }

    /**
     * 设置标题不可见
     */
    public void hidetitle() {
        title.setVisibility(View.GONE);
    }

    public void hideback() {
        reback.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DataUtil.backmusic != null && DataUtil.ismusicplay)
            DataUtil.backmusic.start();
//        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
    }


}
