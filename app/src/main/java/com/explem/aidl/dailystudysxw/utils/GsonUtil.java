package com.explem.aidl.dailystudysxw.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public class GsonUtil {
    private static Gson gson;

    static {
        if (gson == null)
            gson = new Gson();
    }

    public GsonUtil() {
    }

    /**
     * 转换成bean
     */
    public static <T> T GsonToBean(String json, Class<T> tClass) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, tClass);
        }
        return t;
    }

    /**
     * 转换成list
     */
    public static <T> List<T> GsonToList(String json){
        List<T> list = null;
        if (gson != null){
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转换成list中有map
     */
    public static <T> List<Map<String,T>> GsonToListMap(String json){
        List<Map<String,T>> list = null;
        if (gson != null){
            list = gson.fromJson(json,new TypeToken<List<Map<String,T>>>(){}.getType());
        }
        return list;
    }

    /**
     * 转换成map
     */
    public static <T> Map<String,T> GsonToMap(String json){
        Map<String, T> map = null;
        if (gson != null){
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
