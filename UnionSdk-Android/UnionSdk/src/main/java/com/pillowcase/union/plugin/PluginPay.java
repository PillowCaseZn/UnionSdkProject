package com.pillowcase.union.plugin;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IPayPlugin;
import com.pillowcase.union.manager.UnionManager;

/**
 * Author      : PillowCase
 * Create On   : 2020-07-02 21:58
 * Description : 用户支付插件
 */
public class PluginPay implements IPayPlugin, ILoggerOperation {
    private static final PluginPay ourInstance = new PluginPay();

    private LoggerUtils mLoggerUtils;
    private IPayPlugin mPlugin;

    public static PluginPay getInstance() {
        return ourInstance;
    }

    private PluginPay() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    public void init(String pluginName) {
        try {
            log("init", "Plugin Name : " + pluginName);
            if (!pluginName.isEmpty()) {
                mPlugin = (IPayPlugin) PluginFactory.getInstance().initPlugin(pluginName);
            }
        } catch (Exception e) {
            error(e, "init");
        }
    }

    @Override
    public void pay() {
        try {
            log("pay", "");
            if (mPlugin != null) {
                mPlugin.pay();
            }
        } catch (Exception e) {
            error(e, "pay");
        }
    }

    @Override
    public boolean isSupportMethod(String methodName) {
        if (mPlugin != null) {
            return mPlugin.isSupportMethod(methodName);
        }
        return false;
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
