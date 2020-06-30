package com.pillowcase.union.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IActivityLifeListener;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-30 17:23
 * Description ：
 */
public class GameActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks, IActivityLifeListener, ILoggerOperation {
    private LoggerUtils mLoggerUtils;

    public GameActivityLifecycleCallback() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            log("onActivityCreated", "");
        } catch (Exception e) {
            error(e, "onActivityCreated");
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        try {
            log("onActivityStarted", "");
        } catch (Exception e) {
            error(e, "onActivityStarted");
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        try {
            log("onActivityResumed", "");
        } catch (Exception e) {
            error(e, "onActivityResumed");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        try {
            log("onActivityPaused", "");
        } catch (Exception e) {
            error(e, "onActivityPaused");
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        try {
            log("onActivityStopped", "");
        } catch (Exception e) {
            error(e, "onActivityStopped");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        try {
            log("onActivitySaveInstanceState", "");
        } catch (Exception e) {
            error(e, "onActivitySaveInstanceState");
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        try {
            log("onActivityDestroyed", "");
        } catch (Exception e) {
            error(e, "onActivityDestroyed");
        }
    }

    @Override
    public void onNewIntent(Activity gameActivity, Intent intent) {
        try {
            log("onNewIntent", "");
        } catch (Exception e) {
            error(e, "onNewIntent");
        }
    }

    @Override
    public void onActivityResult(Activity gameActivity, int requestCode, int resultCode, Intent data) {
        try {
            log("onActivityResult", "");
        } catch (Exception e) {
            error(e, "onActivityResult");
        }
    }

    @Override
    public void onRestart(Activity gameActivity) {
        try {
            log("onRestart", "");
        } catch (Exception e) {
            error(e, "onRestart");
        }
    }

    @Override
    public void onRequestPermissionsResult(Activity gameActivity, int requestCode, String[] permissions, int[] grantResults) {
        try {
            log("onRequestPermissionsResult", "");
        } catch (Exception e) {
            error(e, "onRequestPermissionsResult");
        }
    }

    @Override
    public void onConfigurationChanged(Activity gameActivity, Configuration newConfig) {
        try {
            log("onConfigurationChanged", "");
        } catch (Exception e) {
            error(e, "onConfigurationChanged");
        }
    }

    @Override
    public void log(String s, Object o) {
        if (mLoggerUtils != null) {
            mLoggerUtils.log(s, o);
        }
    }

    @Override
    public void warn(String s, String s1) {
        if (mLoggerUtils != null) {
            mLoggerUtils.warn(s, s1);
        }
    }

    @Override
    public void error(Throwable throwable, String s) {
        if (mLoggerUtils != null) {
            mLoggerUtils.error(throwable, s);
        }
    }
}
