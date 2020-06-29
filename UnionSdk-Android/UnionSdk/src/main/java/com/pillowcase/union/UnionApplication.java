package com.pillowcase.union;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.pillowcase.union.manager.UnionManager;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:12
 * Description : 聚合SDK Application
 * 注意：
 * 1、如果游戏Application直接继承使用 com.pillowcase.union.UnionApplication，游戏方则
 *    不需要在AndroidManifest.xml application节点上配置 meta-data属性
 * 2、如果游戏Application没有继承使用 com.pillowcase.union.UnionApplication。游戏方在接入时，则需要在
 *    在AndroidManifest.xml application节点上配置 meta-data属性 ，并且在游戏方自身Application内，初始化
 *    UnionSdk , 并且在 onCreate 、 attachBaseContext 、onConfigurationChanged 、onTerminate 调用SDK对应的方法函数
 * 3、上述两点，选其中一点实现即可，不可同时实现，重复调用
 */
public class UnionApplication extends Application {
    private UnionManager manager;

    public UnionApplication() {
        if (manager == null) {
            manager = UnionManager.getInstance();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (manager != null) {
            manager.onApplicationCreate(this);
        }
    }

    /**
     * 注意：这个attachBaseContext方法是在onCreate方法之前调用的
     */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (manager != null) {
            manager.onApplicationAttachBaseContext(this, base);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (manager != null) {
            manager.onApplicationConfigurationChanged(this, newConfig);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (manager != null) {
            manager.onApplicationTerminate(this);
        }
    }

}
