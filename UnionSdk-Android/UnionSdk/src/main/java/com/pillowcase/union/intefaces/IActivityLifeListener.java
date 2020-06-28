package com.pillowcase.union.intefaces;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:57
 * Description : 生命周期监听接口
 */
public interface IActivityLifeListener extends Application.ActivityLifecycleCallbacks {
    void onNewIntent(Activity gameActivity, Intent intent);

    void onActivityResult(Activity gameActivity, int requestCode, int resultCode, Intent data);

    void onRestart(Activity gameActivity);

    void onRequestPermissionsResult(Activity gameActivity, int requestCode, String[] permissions, int[] grantResults);

    void onConfigurationChanged(Activity gameActivity, Configuration newConfig);
}
