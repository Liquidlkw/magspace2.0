package com.example.magspace;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.magspace.Bean.HomeWatcherReceiver;
import com.example.magspace.Utils.DataUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    public static Context myContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //友盟统计初始化
        UMConfigure.init(this, "5fffa46ef1eb4f3f9b5dc880", getChannelName(myContext), UMConfigure.DEVICE_TYPE_PHONE, null);
        //友盟页面统计模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true);

        registerHomeKeyReceiver(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
        Fresco.initialize(this);
        myContext = this.getApplicationContext();
        Toast.makeText(myContext, getChannelName(myContext), Toast.LENGTH_SHORT).show();
    }


    public static Context getMyApplicationContext() {
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
        if (isBackground(this)) {
            if (DataUtil.ismusicplay)
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
                } else {
                    Log.i("zh", "前台");
                    return false;
                }
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

    //key为渠道名的key，对应友盟的 UMENG_CHANNEL_VALUE
    // 获取渠道工具函数
    public static String getChannelName(Context ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.get("UMENG_CHANNEL")+"";
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(channelName)){
            channelName="Unknown";
        }
        return channelName;
    }
}
