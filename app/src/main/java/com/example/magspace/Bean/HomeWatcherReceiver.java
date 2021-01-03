package com.example.magspace.Bean;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.magspace.Utils.DataUtil;

/**
 * 自定义的广播接收者
 */

public class HomeWatcherReceiver extends BroadcastReceiver {

    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    //action内的某些reason
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";//home键旁边的最近程序列表键
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";//按下home键
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";//锁屏键
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";//某些三星手机的程序列表键

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        App app = (App) context.getApplicationContext();
        Log.i("zh", "onReceive: action: " + action);
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            // android.intent.action.CLOSE_SYSTEM_DIALOGS
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            Log.i("zh", "reason: " + reason);

            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) { // 短按Home键
                Log.i("zh", "homekey");
                if(DataUtil.ismusicplay)
                    DataUtil.backmusic.pause();
            } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {//Home键旁边的显示最近的程序的按钮
                Log.i("zh", "long press home key or activity switch");
                if(DataUtil.ismusicplay)
                    DataUtil.backmusic.pause();
            } else if (SYSTEM_DIALOG_REASON_LOCK.equals(reason)) {  // 锁屏
                Log.i("zh", "lock");
                if(DataUtil.ismusicplay)
                    DataUtil.backmusic.pause();
            }
           else if (SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {   // samsung 长按Home键
                Log.i("zh", "assist");
                if(DataUtil.ismusicplay)
                    DataUtil.backmusic.pause();
            }
        }
         else if (action.equals("android.intent.action.SCREEN_ON")) {
            Log.i("zh", "SCREEN_ON");
             if(DataUtil.ismusicplay)
                 DataUtil.backmusic.start();
        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
            Log.i("zh", "SCREEN_OFF");
            if(DataUtil.ismusicplay)
                DataUtil.backmusic.pause();
        }
    }
}
