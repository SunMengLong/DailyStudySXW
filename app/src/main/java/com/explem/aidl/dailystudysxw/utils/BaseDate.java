package com.explem.aidl.dailystudysxw.utils;

import android.text.TextUtils;
import android.util.Log;

import com.explem.aidl.dailystudysxw.application.MyApplication;
import com.explem.aidl.dailystudysxw.http.HttpManger;
import com.explem.aidl.dailystudysxw.view.ShowingPage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pooh on 2016/11/29.
 */
public abstract class BaseDate {

    public static int NOTIME=0;

    public static int NOMALTIME=24*3*60*60*1000;

    //三种请求数据的类型
    public static int postData=100;
    public static int getData=200;


    public static File cacheDir;

    public BaseDate(){
        //存到那里去？
        cacheDir = MyApplication.getConText().getCacheDir();
        File file=new File(cacheDir,"dailystudy");
        //判断此文件夹是否存在，不存在就创建
        if(!file.exists()){
            file.mkdirs();
        }
    }
    /**
     * 这个方法供外面调用
     * @param path 路径
     * @param args 参数
     * @param index 索引
     * @param validTime 有效时间
     */
    public void getDate(boolean isReadCookie,boolean isSaveCookie,String path,String args,int index,int validTime,int type,HashMap<String,String> map){
        //判断请求时间
        if(validTime==0){
            //直接请求网络
            getDataFromNet(isReadCookie,isSaveCookie,path,args,index,validTime,type,map);
        }else{
            String data=getDataFromLocal(path,args,index,validTime);
            if(TextUtils.isEmpty(data)){
                getDataFromNet(isReadCookie,isSaveCookie,path,args,index,validTime,type,map);
            }else{
                //请求到数据，作返回
                setResultData(data);
            }
        }
    }

    /**
     * 网络获取----OkHttpClient
     * @param path
     * @param args
     * @param index
     * @param validTime
     */
    private void getDataFromNet(boolean isReadCookie,boolean isSaveCookie,final String path, final String args, final int index, final int validTime,int type,HashMap<String,String> map) {
        if(type==BaseDate.getData){
            HttpManger.getMethod(path,args, new retrofit2.Callback<String>() {
                @Override
                public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                    final String data=response.body();
                    //设置数据
                    CommonUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            setResultData(data);
                        }
                    });
                    //写入本地
                    writeToLocal(path,args,index,validTime,data);
                }

                @Override
                public void onFailure(retrofit2.Call<String> call, Throwable t) {
                    setResultError(ShowingPage.StateType.STATE_LOAD_ERROR);
                }
            });
        }else{
            //post请求
            HttpManger.postMethod(isReadCookie,isSaveCookie,path,args,map,new Callback<String>(){
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    final String data=response.body();
                    Log.i("aaaaa", "onResponse: ...."+data);
                    //设置数据
                    CommonUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            setResultData(data);
                        }
                    });
                        //写入本地
                        writeToLocal(path,args,index,validTime,data);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    setResultError(ShowingPage.StateType.STATE_LOAD_ERROR);
                }
            });
        }
    }

    protected abstract void setResultError(ShowingPage.StateType stateLoadError);

    /**
     * 写入本地
     * @param data
     */
    private void writeToLocal(String path, String args, int index, int validTime, String data) {
        try {
            //每一次请求都创建一个文件
            File file=new File(cacheDir, MD5Encoder.encode(path+args)+index);
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
            //第一行存入时间
            bw.write(System.currentTimeMillis()+validTime+"\r\n");
            bw.write(data);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 本地获取
     * @param path
     * @param args
     * @param index
     * @param validTime
     * @return
     */
    private String getDataFromLocal(String path, String args, int index, int validTime) {
        try {
            //创建文件
            File file=new File(cacheDir,MD5Encoder.encode(path+args)+index);
            BufferedReader br=new BufferedReader(new FileReader(file));
            String time=br.readLine();
            long myTime=Long.parseLong(time);
            if(System.currentTimeMillis()<myTime){
                StringBuilder strbuilder=new StringBuilder();
                String str="";
                while ((str=br.readLine())!=null) {
                    strbuilder.append(str);
                }
                br.close();
                return strbuilder.toString();
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将请求成功的数据作返回
     * @param data
     */
    public abstract void setResultData(String data);
}
