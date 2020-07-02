package com.pillowcase.union.plugin;

import android.app.Activity;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.manager.UnionManager;

/**
 * Author      : PillowCase
 * Create On   : 2020-07-02 17:51
 * Description :
 */
public class PluginFactory implements ILoggerOperation {
    private final static PluginFactory ourInstance = new PluginFactory();
    private LoggerUtils mLoggerUtils;

    public static PluginFactory getInstance() {
        return ourInstance;
    }

    public PluginFactory() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    public Object initPlugin(String pluginName) {
        try {
            log("initPlugin", "plugin Name : " + pluginName);
            Class localClass = Class.forName(pluginName);
            Object o = localClass.getDeclaredConstructor(Activity.class).newInstance(UnionManager.getInstance().getGameActivity());
            return o;
        } catch (Exception e) {
            error(e, "initPlugin");
        }
        return null;
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
