package com.explem.aidl.dailystudysxw.http;

import android.util.Log;

import com.explem.aidl.dailystudysxw.cookie.ReadCookiesInterceptor;
import com.explem.aidl.dailystudysxw.cookie.SaveCookiesInterceptor;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhiyuan on 17/1/11.
 */

public class HttpManger {
    public static void getMethod(String baseUrl, String url, final Callback<String> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI = retrofit.create(ProjectAPI.class);

        Call<String> call = projectAPI.getMethod(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public static void postMethod(boolean isReadCookie, boolean isSaveCookie,String baseUrl, String url, Map<String,String> map,final Callback<String> callback) {

//        Log.i("aaaaaaa", "onResponse: ......isReadCookie:"+isReadCookie+"isSaveCookie:"+isSaveCookie+"baseUrl:"+baseUrl+"url:"+url+"map:"+map.size());

        OkHttpClient httpClient = null;
        if (isReadCookie && !isSaveCookie) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new ReadCookiesInterceptor())
                    .build();
            Log.i("AAA","只读不写");
        }
        if (isSaveCookie && !isReadCookie) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SaveCookiesInterceptor())
                    .build();
            Log.i("AAA","只写不读");
        }
        if (isSaveCookie && isReadCookie) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SaveCookiesInterceptor()).addInterceptor(new ReadCookiesInterceptor())
                    .build();
            Log.i("AAA","又写又读");
        } if (!isSaveCookie && !isReadCookie){
            httpClient = new OkHttpClient.Builder()
                    .build();
            Log.i("AAA","不写不读");
        }
        //指定客户端
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(httpClient).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI = retrofit.create(ProjectAPI.class);

        Call<String> call = projectAPI.postMethod(url,map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("aaaaaaa", "onResponse: ......"+response.body());
                callback.onResponse(call, response);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
