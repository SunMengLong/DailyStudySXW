package com.explem.aidl.dailystudysxw.login_regist;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhiyuan on 17/1/9.
 */

public class SmsContentObserver extends ContentObserver {
    private final Handler handler;
    private final Context context;

    public SmsContentObserver(Handler handler, Context context) {
        super(handler);
        this.context = context;
        this.handler = handler;
    }

    /**
     * 当想要观察的对应的uri发生改变的时候，触发
     *
     * @param selfChange
     */
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        //查询出最新的短信
        Uri smsUri = Uri.parse("content://sms/inbox");
        //查询数据库
        Cursor cursor = context.getContentResolver().query(smsUri, null, null, null, "date desc");
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToNext()) {
//            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            stringBuilder.append(body);
        }
        Pattern pattern = Pattern.compile("(\\d{6})");//正则表达式
        Matcher matcher = pattern.matcher(stringBuilder.toString());
        if (matcher.find()) {
            String code = matcher.group(0);
            handler.obtainMessage(0, code).sendToTarget();
        }
    }
}
