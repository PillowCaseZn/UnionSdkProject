package com.pillowcase.demo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.pillowcase.union.UnionSdk;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:11
 * Description : 游戏主Application
 */
public class GameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UnionSdk.getInstance().onApplicationCreate(this);
    }

    /**
     * 注意：这个attachBaseContext方法是在onCreate方法之前调用的
     */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        UnionSdk.getInstance().onApplicationAttachBaseContext(this, base);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        UnionSdk.getInstance().onApplicationConfigurationChanged(this, newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        UnionSdk.getInstance().onApplicationTerminate(this);
    }
}
