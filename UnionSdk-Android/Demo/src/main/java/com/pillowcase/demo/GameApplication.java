package com.pillowcase.demo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.pillowcase.union.UnionApplication;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:11
 * Description : 游戏主Application
 */
public class GameApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 注意：这个attachBaseContext方法是在onCreate方法之前调用的
     */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
