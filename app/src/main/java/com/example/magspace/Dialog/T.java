package com.example.magspace.Dialog;

import android.content.Context;
import android.widget.Toast;

/**
 * @author lmw
 * @ClassName: T
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年3月28日 下午5:34:58
 */
public class T {
    public static TPrompt tPrompt;
    public static TPromptSuccess tPromptSuccess;
    public static TPromptError tPromptError;

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showAnimToast(Context mContext, String msg) {

            if (tPrompt == null) {
                tPrompt = new TPrompt(mContext);
            }
            tPrompt.showToast(msg);

    }

    public static void showAnimSuccessToast(Context mContext, String msg) {

            if(tPromptError!=null)
                tPromptError.cancel();
            if (tPromptSuccess == null) {
                tPromptSuccess = new TPromptSuccess(mContext);
            }
            tPromptSuccess.showToast(msg);

    }

    public static void showAnimErrorToast(Context mContext, String msg) {

            if (tPromptSuccess!=null)
                tPromptSuccess.cancel();
            if (tPromptError == null) {
                tPromptError = new TPromptError(mContext);
            }
            tPromptError.showToast(msg);

    }


}
