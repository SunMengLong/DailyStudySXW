package com.explem.aidl.dailystudysxw.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by Pooh on 2017/1/11.
 */

public class httpBoolean {
    public static boolean checkNetworkState(Context context) {
        boolean flag = false;
        //得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        Log.i("aaaaa", "checkNetworkState: .......");
        return !flag;
    }
}
