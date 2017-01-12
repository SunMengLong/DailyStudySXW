package com.explem.aidl.dailystudysxw.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by Pooh on 2017/1/10.
 */

public class MyApplication extends Application{
    private static Context applicationContext;
    private static Handler handler;
    private static int mainId;
    private static Thread thread;
    public static boolean quanxuan=true;

    //定义显示隐藏的登陆界面的图片及文字
    public static int showLoginImg=0;
    public static int unShowLoginImg=1;

    //判断要显示的是哪一个
    public static boolean myFlag=false;

    //判断Vp的滑动方向
    public static int direction2=-1;

    @Override
    public void onCreate() {
        super.onCreate();
        //得到上下文
        applicationContext = getApplicationContext();
        //Handler方法
        handler = new Handler();
        //线程号
        mainId = Process.myTid();
        //获取主线程
        thread = Thread.currentThread();
    }
    //上下文的方法
    public static Context getConText(){
        return applicationContext;
    }
    //得到hander的方法
    public static Handler getHandler(){
        return handler;
    }
    //得到线程号的方法
    public static int getMainId(){
        return mainId;
    }
    //得到主线程的方法
    public static Thread getMainThread(){
        return thread;
    }



//加载正常图片
//    Glide.with(this)
//            .load("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg")
//    .into(img);
//加载圆形图片
//    Glide.with(this).load("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg").bitmapTransform(new BlurTransformation(this, 20), new CropCircleTransformation(this)).into(img);
}
