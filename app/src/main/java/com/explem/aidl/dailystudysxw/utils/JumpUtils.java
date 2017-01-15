package com.explem.aidl.dailystudysxw.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/1/14 0014.
 */

public class JumpUtils {

    public static void jumpOnly(Context context,Class c){
        Intent intent = new Intent(context,c);
        context.startActivity(intent);
    }
}
