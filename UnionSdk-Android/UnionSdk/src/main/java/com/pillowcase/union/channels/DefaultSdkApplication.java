package com.pillowcase.union.channels;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IApplicationListener;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-29 11:56
 * Description ： 聚合SDK 自带默认SDK Application
 */
public class DefaultSdkApplication implements IApplicationListener, ILoggerOperation {
    private LoggerUtils mLoggerUtils;

    public DefaultSdkApplication() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(true, getClass().getSimpleName());
        }
    }

    @Override
    public void onApplicationCreate(Application application) {
        try {
            log("onApplicationCreate", "");
        } catch (Exception e) {
            error(e, "onApplicationCreate");
        }
    }

    @Override
    public void onApplicationAttachBaseContext(Application application, Context base) {
        try {
            log("onApplicationAttachBaseContext", "");
        } catch (Exception e) {
            error(e, "onApplicationAttachBaseContext");
        }
    }

    @Override
    public void onApplicationConfigurationChanged(Application application, Configuration config) {
        try {
            log("onApplicationConfigurationChanged", "");
        } catch (Exception e) {
            error(e, "onApplicationConfigurationChanged");
        }
    }

    @Override
    public void onApplicationTerminate(Application application) {
        try {
            log("onApplicationTerminate", "");
        } catch (Exception e) {
            error(e, "onApplicationTerminate");
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
