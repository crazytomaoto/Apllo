package com.common.project.utils.json;


import com.common.project.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by benny
 * @date on 2018/12/4.
 * @function Json解析工具类
 */
public class GsonUtils {

    private static Gson mGson = new Gson();

    public static <T> T json2Bean(String json, Class<T> clazz) {
        try {
            return mGson.fromJson(json, clazz);
        } catch (Exception e) {
            LogUtils.e("GsonUtils:"+e.toString());
            return null;
        }

    }

    /**
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, clazz));
        }
        return mList;
    }

    /**
     * fromJson
     *
     * @param json json
     * @param c    c
     * @param <T>  <T>
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> c) {
        try{
        return mGson.fromJson(json, c);
    } catch (Exception e) {
        LogUtils.e(e.toString());
        return null;
    }
    }

    /**
     * toJson
     *
     * @param src src
     * @return String
     */
    public static String toJson(Object src) {
        try{
        return mGson.toJson(src);
    } catch (Exception e) {
        LogUtils.e(e.toString());
        return null;
    }
    }
}

