package com.pillowcase.union;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.pillowcase.union.intefaces.IApplicationListener;
import com.pillowcase.union.intefaces.ISdkCallbacks;
import com.pillowcase.union.intefaces.ISdkMethods;
import com.pillowcase.union.manager.UnionManager;
import com.pillowcase.union.modules.InitParams;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-29 00:00
 * Description : 聚合SDK 主入口
 */
public class UnionSdk implements ISdkMethods, IApplicationListener {
    private static final UnionSdk ourInstance = new UnionSdk();

    private UnionManager manager;

    public static UnionSdk getInstance() {
        return ourInstance;
    }

    private UnionSdk() {
        if (manager == null) {
            manager = UnionManager.getInstance();
        }
    }

    @Override
    public void init(InitParams params, ISdkCallbacks callbacks) {
        if (manager != null) {
            manager.init(params, callbacks);
        }
    }

    @Override
    public void login() {
        if (manager != null) {
            manager.login();
        }
    }

    @Override
    public void switchLogin() {
        if (manager != null) {
            manager.switchLogin();
        }
    }

    @Override
    public void submitRoleInfo() {
        if (manager != null) {
            manager.submitRoleInfo();
        }
    }

    @Override
    public void logout() {
        if (manager != null) {
            manager.logout();
        }
    }

    @Override
    public void pay() {
        if (manager != null) {
            manager.pay();
        }
    }

    @Override
    public void exit() {
        if (manager != null) {
            manager.exit();
        }
    }

    @Override
    public void onNewIntent(Activity gameActivity, Intent intent) {
        if (manager != null) {
            manager.onNewIntent(gameActivity, intent);
        }
    }

    @Override
    public void onActivityResult(Activity gameActivity, int requestCode, int resultCode, Intent data) {
        if (manager != null) {
            manager.onActivityResult(gameActivity, requestCode, resultCode, data);
        }
    }

    @Override
    public void onRestart(Activity gameActivity) {
        if (manager != null) {
            manager.onRestart(gameActivity);
        }
    }

    @Override
    public void onRequestPermissionsResult(Activity gameActivity, int requestCode, String[] permissions, int[] grantResults) {
        if (manager != null) {
            manager.onRequestPermissionsResult(gameActivity, requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onConfigurationChanged(Activity gameActivity, Configuration newConfig) {
        if (manager != null) {
            manager.onConfigurationChanged(gameActivity, newConfig);
        }
    }

    @Override
    public void onApplicationCreate(Application application) {
        if (manager != null) {
            manager.onApplicationCreate(application);
        }
    }

    @Override
    public void onApplicationAttachBaseContext(Application application, Context base) {
        if (manager != null) {
            manager.onApplicationAttachBaseContext(application, base);
        }
    }

    @Override
    public void onApplicationConfigurationChanged(Application application, Configuration config) {
        if (manager != null) {
            manager.onApplicationConfigurationChanged(application, config);
        }
    }

    @Override
    public void onApplicationTerminate(Application application) {
        if (manager != null) {
            manager.onApplicationTerminate(application);
        }
    }
}
