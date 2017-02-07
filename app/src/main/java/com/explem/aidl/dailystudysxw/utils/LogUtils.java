package com.explem.aidl.dailystudysxw.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zhiyuan on 16/8/31.
 */
public class LogUtils {
    public static final boolean isDebug = true;

    public static void i(String TAG, String info) {
        if (isDebug) {
            Log.i(TAG, info);
        }
    }

    public static void d(String TAG, String info) {
        if (isDebug) {
            Log.d(TAG, info);
        }
    }

    public static void e(String TAG, String info) {
        if (isDebug) {
            Log.e(TAG, info);
        }
    }

    public static void showToast(Context context, String s) {
        if (isDebug)
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static Toast toast;

    /**
     * 能够连续弹吐司，不用等上个消失
     *
     * @param context
     * @param string
     */
    public static void showToastA(Context context, String string) {
        if (toast == null) {
            toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
        }
        toast.setText(string);
        if (isDebug)
            toast.show();
    }
}
