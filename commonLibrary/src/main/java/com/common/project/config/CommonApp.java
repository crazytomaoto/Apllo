package com.common.project.config;


import androidx.multidex.MultiDexApplication;

/**
 * @author by.benny
 * @date 2019/2/12
 * @描述 实体类
 */
public class CommonApp extends MultiDexApplication {
    private static CommonApp instance;
    public static CommonApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=(CommonApp)this.getApplicationContext();
    }
}
