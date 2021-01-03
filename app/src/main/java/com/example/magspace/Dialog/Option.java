package com.example.magspace.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;


public class Option extends Dialog {
    public Context context;
    private ImageView exit;
    private CheckBox music;
    private CheckBox voice;

    public Option(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.option_dialog,null);
        setContentView(view);
        exit = findViewById(R.id.exit);
        music = findViewById(R.id.music);
        voice = findViewById(R.id.voice);
        if(DataUtil.ismusicplay){
            music.setChecked(false);
        }
        else{
            music.setChecked(true);
        }
        if(DataUtil.isvoiceplay){
            voice.setChecked(false);
        }
        else{
            voice.setChecked(true);
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                dismiss();
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   if(music.isChecked()){
                       DataUtil.ismusicplay=false;
                       DataUtil.backmusic.pause();
                   }
                   else{
                       DataUtil.ismusicplay=true;
                       DataUtil.backmusic.start();
                   }
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(voice.isChecked()){
                    DataUtil.isvoiceplay=false;
                }
                else{
                    DataUtil.isvoiceplay=true;
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        exit.clearColorFilter();
    }
}
