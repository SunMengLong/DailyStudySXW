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
}
