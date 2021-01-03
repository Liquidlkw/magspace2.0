package com.example.magspace.Base;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.magspace.Utils.DataUtil;
import com.example.magspace.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class BasePage extends AppCompatActivity {
    public ViewPager mVp;
    public ArrayList<View> List = new ArrayList<>();
    public MyPagerAdapter mAdapter;
    public LocalActivityManager manager;
    public Intent intentItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);//必须写上这一行代码，不然会报错
    }
    public void addList(int id,Class<?>cls){
        //这个类的第一个参数是上下文，第二个参数是你需要转化的Activity
        intentItem = new Intent(getApplicationContext(),cls);
        //将Activity转化为View然后放入View集合
        List.add(manager.startActivity(id+"", intentItem).getDecorView());

    }
    protected void onResume() {
        super.onResume();
        if(DataUtil.ismusicplay&&DataUtil.backmusic!=null)
            DataUtil.backmusic.start();
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

}
