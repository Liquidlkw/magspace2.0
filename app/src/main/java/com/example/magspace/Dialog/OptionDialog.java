package com.example.magspace.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.magspace.PolicyActivity;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;


public class OptionDialog extends Dialog {
    public Context context;
    private ImageView exit;
    private CheckBox music;
    private CheckBox voice;
    private TextView policy;

    public OptionDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.option_dialog, new LinearLayout(context),false);
        setContentView(view);
        exit = findViewById(R.id.exit);
        music = findViewById(R.id.music);
        voice = findViewById(R.id.voice);
        policy = findViewById(R.id.policy);
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
        exit.setOnClickListener(v -> {
            exit.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            dismiss();
        });
        music.setOnClickListener(v -> {
               if(music.isChecked()){
                   DataUtil.ismusicplay=false;
                   DataUtil.backmusic.pause();
               }
               else{
                   DataUtil.ismusicplay=true;
                   DataUtil.backmusic.start();
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


        policy.setOnClickListener(v -> context.startActivity(new Intent(context, PolicyActivity.class)));



    }

    @Override
    protected void onStop() {
        super.onStop();
        exit.clearColorFilter();
    }
}
