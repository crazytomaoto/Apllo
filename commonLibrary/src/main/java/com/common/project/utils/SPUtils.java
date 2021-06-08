package com.common.project.utils;
import android.content.Context;
import android.content.SharedPreferences;
import com.common.project.config.CommonApp;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferences工具类
 * @author  binsky on 2016/7/31.
 */

public class SPUtils {

    //保存文件名
    public static final String FILE_NAME = "share_data";

    /**
     * 保存数据（默认异步保存数据）
     *  @param key key值
     * @param object value
     */
    public static String put(String key, Object object) {
        //打开Preferences，名称为FILE_NAME，如果存在则打开它，否则创建新的Preferences
        SharedPreferences sp = CommonApp.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        //让setting处于编辑状态
        SharedPreferences.Editor editor = sp.edit();
        //存放数据
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        //异步完成提交
        SharedPreferencesCompat.apply(editor);
        //同步完成提交
        editor.commit();
        return key;
    }

    /**
     * 获取数据
     *
     * @param key key值
     * @param defaultObject 默认对象
     * @return 对象
     */
    public static Object get(String key, Object defaultObject) {

        SharedPreferences sp = CommonApp.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param key key值
     */
    public static void remove(String key) {
        SharedPreferences sp = CommonApp.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = CommonApp.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key key值
     * @return 是否已经存在
     */
    public static boolean contains(String key) {
        SharedPreferences sp = CommonApp.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return 所有的键值对
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = CommonApp.getInstance().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        //反射查找apply的方法
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor 编辑器
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ignored) {
            }
            editor.commit();
        }
    }
}
