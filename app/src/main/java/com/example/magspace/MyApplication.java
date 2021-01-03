package com.example.magspace;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.magspace.Bean.HomeWatcherReceiver;
import com.example.magspace.Utils.DataUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    public static Context myContext;
    @Override
    public void onCreate() {
        super.onCreate();

        registerHomeKeyReceiver(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        Fresco.initialize(this);
        myContext = this.getApplicationContext();
    }



    public static Context getMyApplicationContext(){
        return myContext;
    }
    //自定义的广播接收者
    private HomeWatcherReceiver mHomeKeyReceiver = null;

    //注册广播接收者，监听Home键
    private void registerHomeKeyReceiver(Context context) {
        Log.i("zh", "registerHomeKeyReceiver");
        mHomeKeyReceiver = new HomeWatcherReceiver();
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.registerReceiver(mHomeKeyReceiver, homeFilter);
    }

    //取消监听广播接收者
    private void unregisterHomeKeyReceiver(Context context) {
        Log.i("zh", "unregisterHomeKeyReceiver");
        if (null != mHomeKeyReceiver) {
            context.unregisterReceiver(mHomeKeyReceiver);
        }
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d("zh", "onTerminate");
        super.onTerminate();
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d("zh", "onLowMemory");
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d("zh", "onTrimMemory");
        super.onTrimMemory(level);
        if(isBackground(this)){
            if(DataUtil.ismusicplay)
                DataUtil.backmusic.pause();
        }
    }

    public static boolean isBackground(Context context) {
        Log.i("zh", "判断前后台");
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("zh", "后台");
                    return true;
                }else{
                    Log.i("zh", "前台");
                    return false; }
            }
        }
        return false;
    }


//    @Override
//    public void onTrimMemory(int level) {
//        // 程序在内存清理的时候执行
//        Log.i("zh", "onTrimMemory: ");
//        super.onTrimMemory(level);
//        if(DataUtil.ismusicplay)
//            DataUtil.backmusic.pause();
//    }
}
